package controller.ThongKe;

import dao.ThongKeDao;
import model.DoanhThuNgay;
import model.TopSanPham;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.List;

@WebServlet("/xuatExcel")
public class XuatExcelController extends HttpServlet {

    private final ThongKeDao dao =
            new ThongKeDao();

    @Override
    protected void doGet(
            HttpServletRequest req,
            HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session =
                req.getSession(false);

        if (session == null
                || session.getAttribute("user") == null) {

            resp.sendRedirect(
                    req.getContextPath() + "/login"
            );
            return;
        }

        Workbook workbook = null;

        try {

            String kieu =
                    req.getParameter("kieu");

            if (kieu == null
                    || kieu.trim().isEmpty()) {

                kieu = "thang";
            }

            LocalDate tu;
            LocalDate den;

            /*
             * Thống kê theo tháng.
             */
            if ("thang".equals(kieu)) {

                String thangParam =
                        req.getParameter("thang");

                if (thangParam == null
                        || thangParam.trim().isEmpty()) {

                    LocalDate now =
                            LocalDate.now();

                    tu = now.withDayOfMonth(1);

                    den = now.withDayOfMonth(
                            now.lengthOfMonth()
                    );

                } else {

                    String[] parts =
                            thangParam.split("-");

                    if (parts.length != 2) {
                        throw new IllegalArgumentException(
                                "THANG_KHONG_HOP_LE"
                        );
                    }

                    int nam =
                            Integer.parseInt(parts[0]);

                    int thang =
                            Integer.parseInt(parts[1]);

                    tu = LocalDate.of(
                            nam,
                            thang,
                            1
                    );

                    den = tu.withDayOfMonth(
                            tu.lengthOfMonth()
                    );
                }

            } else {

                /*
                 * Thống kê theo năm.
                 */
                String namParam =
                        req.getParameter("nam");

                int nam;

                if (namParam == null
                        || namParam.trim().isEmpty()) {

                    nam = LocalDate.now()
                            .getYear();

                } else {

                    nam = Integer.parseInt(
                            namParam.trim()
                    );
                }

                tu = LocalDate.of(
                        nam,
                        1,
                        1
                );

                den = LocalDate.of(
                        nam,
                        12,
                        31
                );
            }

            java.sql.Date tuNgay =
                    java.sql.Date.valueOf(tu);

            java.sql.Date denNgay =
                    java.sql.Date.valueOf(den);

            /*
             * Lấy dữ liệu thống kê.
             */
            double tongDoanhThu =
                    dao.getTongDoanhThu(
                            tuNgay,
                            denNgay
                    );

            int tongHoaDon =
                    dao.getTongHoaDon(
                            tuNgay,
                            denNgay
                    );

            int tongSanPham =
                    dao.getTongSanPham(
                            tuNgay,
                            denNgay
                    );

            List<TopSanPham> topSanPham =
                    dao.getTop5SanPham(
                            tuNgay,
                            denNgay
                    );

            List<DoanhThuNgay> doanhThu;

            if ("nam".equals(kieu)) {

                doanhThu =
                        dao.getDoanhThuTheoThang(
                                tuNgay,
                                denNgay
                        );

            } else {

                doanhThu =
                        dao.getDoanhThuTheoNgay(
                                tuNgay,
                                denNgay
                        );
            }

            /*
             * Tạo workbook.
             */
            workbook =
                    new XSSFWorkbook();

            Sheet sheet =
                    workbook.createSheet(
                            suaTiengViet(
                                    "Thống kê doanh thu"
                            )
                    );

            /*
             * Font dữ liệu.
             */
            Font dataFont =
                    workbook.createFont();

            dataFont.setFontName("Arial");
            dataFont.setFontHeightInPoints(
                    (short) 11
            );

            CellStyle dataStyle =
                    workbook.createCellStyle();

            dataStyle.setFont(dataFont);
            dataStyle.setVerticalAlignment(
                    VerticalAlignment.CENTER
            );

            /*
             * Style tiêu đề lớn.
             */
            Font titleFont =
                    workbook.createFont();

            titleFont.setFontName("Arial");
            titleFont.setBold(true);
            titleFont.setFontHeightInPoints(
                    (short) 16
            );

            CellStyle titleStyle =
                    workbook.createCellStyle();

            titleStyle.setFont(titleFont);
            titleStyle.setAlignment(
                    HorizontalAlignment.CENTER
            );

            titleStyle.setVerticalAlignment(
                    VerticalAlignment.CENTER
            );

            /*
             * Style header.
             */
            Font headerFont =
                    workbook.createFont();

            headerFont.setFontName("Arial");
            headerFont.setBold(true);
            headerFont.setColor(
                    IndexedColors.WHITE.getIndex()
            );

            CellStyle headerStyle =
                    workbook.createCellStyle();

            headerStyle.setFont(headerFont);
            headerStyle.setAlignment(
                    HorizontalAlignment.CENTER
            );

            headerStyle.setVerticalAlignment(
                    VerticalAlignment.CENTER
            );

            headerStyle.setFillForegroundColor(
                    IndexedColors.BLUE.getIndex()
            );

            headerStyle.setFillPattern(
                    FillPatternType.SOLID_FOREGROUND
            );

            headerStyle.setBorderTop(
                    BorderStyle.THIN
            );

            headerStyle.setBorderBottom(
                    BorderStyle.THIN
            );

            headerStyle.setBorderLeft(
                    BorderStyle.THIN
            );

            headerStyle.setBorderRight(
                    BorderStyle.THIN
            );

            /*
             * Style tiền.
             */
            CellStyle moneyStyle =
                    workbook.createCellStyle();

            moneyStyle.cloneStyleFrom(
                    dataStyle
            );

            DataFormat dataFormat =
                    workbook.createDataFormat();

            moneyStyle.setDataFormat(
                    dataFormat.getFormat(
                            "#,##0"
                    )
            );

            /*
             * Tiêu đề file.
             */
            Row titleRow =
                    sheet.createRow(0);

            titleRow.setHeightInPoints(28);

            Cell titleCell =
                    titleRow.createCell(0);

            String tieuDe;

            if ("nam".equals(kieu)) {

                tieuDe =
                        "THỐNG KÊ DOANH THU NĂM "
                                + tu.getYear();

            } else {

                tieuDe =
                        "THỐNG KÊ DOANH THU THÁNG "
                                + tu.getMonthValue()
                                + "/"
                                + tu.getYear();
            }

            titleCell.setCellValue(
                    suaTiengViet(tieuDe)
            );

            titleCell.setCellStyle(
                    titleStyle
            );

            sheet.addMergedRegion(
                    new CellRangeAddress(
                            0,
                            0,
                            0,
                            3
                    )
            );

            /*
             * Thông tin tổng quan.
             */
            Row row1 =
                    sheet.createRow(2);

            taoOChu(
                    row1,
                    0,
                    "Tổng doanh thu",
                    dataStyle
            );

            taoOSo(
                    row1,
                    1,
                    tongDoanhThu,
                    moneyStyle
            );

            Row row2 =
                    sheet.createRow(3);

            taoOChu(
                    row2,
                    0,
                    "Tổng hóa đơn",
                    dataStyle
            );

            taoOSo(
                    row2,
                    1,
                    tongHoaDon,
                    dataStyle
            );

            Row row3 =
                    sheet.createRow(4);

            taoOChu(
                    row3,
                    0,
                    "Tổng sản phẩm bán",
                    dataStyle
            );

            taoOSo(
                    row3,
                    1,
                    tongSanPham,
                    dataStyle
            );

            /*
             * Bảng doanh thu.
             */
            Row doanhThuTitle =
                    sheet.createRow(6);

            Cell doanhThuTitleCell =
                    doanhThuTitle.createCell(0);

            doanhThuTitleCell.setCellValue(
                    suaTiengViet(
                            "DOANH THU"
                    )
            );

            doanhThuTitleCell.setCellStyle(
                    headerStyle
            );

            Row doanhThuHeader =
                    sheet.createRow(7);

            taoOChu(
                    doanhThuHeader,
                    0,
                    "nam".equals(kieu)
                            ? "Tháng"
                            : "Ngày",
                    headerStyle
            );

            taoOChu(
                    doanhThuHeader,
                    1,
                    "Doanh thu",
                    headerStyle
            );

            int rowIndex = 8;

            if (doanhThu != null) {

                for (DoanhThuNgay d : doanhThu) {

                    Row row =
                            sheet.createRow(
                                    rowIndex++
                            );

                    if (d.getNgayLap() != null) {

                        String ngay;

                        if ("nam".equals(kieu)) {

                            ngay = String.format(
                                    "%02d/%d",
                                    d.getNgayLap()
                                            .toLocalDate()
                                            .getMonthValue(),
                                    d.getNgayLap()
                                            .toLocalDate()
                                            .getYear()
                            );

                        } else {

                            ngay = String.format(
                                    "%02d/%02d/%d",
                                    d.getNgayLap()
                                            .toLocalDate()
                                            .getDayOfMonth(),
                                    d.getNgayLap()
                                            .toLocalDate()
                                            .getMonthValue(),
                                    d.getNgayLap()
                                            .toLocalDate()
                                            .getYear()
                            );
                        }

                        taoOChu(
                                row,
                                0,
                                ngay,
                                dataStyle
                        );
                    }

                    taoOSo(
                            row,
                            1,
                            d.getDoanhThu(),
                            moneyStyle
                    );
                }
            }

            /*
             * Top 5 sản phẩm.
             */
            int topStart =
                    rowIndex + 2;

            Row topTitle =
                    sheet.createRow(topStart);

            Cell topTitleCell =
                    topTitle.createCell(0);

            topTitleCell.setCellValue(
                    suaTiengViet(
                            "TOP 5 SẢN PHẨM BÁN CHẠY"
                    )
            );

            topTitleCell.setCellStyle(
                    headerStyle
            );

            Row topHeader =
                    sheet.createRow(
                            topStart + 1
                    );

            taoOChu(
                    topHeader,
                    0,
                    "Mã SP",
                    headerStyle
            );

            taoOChu(
                    topHeader,
                    1,
                    "Tên sản phẩm",
                    headerStyle
            );

            taoOChu(
                    topHeader,
                    2,
                    "Số lượng",
                    headerStyle
            );

            taoOChu(
                    topHeader,
                    3,
                    "Doanh thu",
                    headerStyle
            );

            int topRowIndex =
                    topStart + 2;

            if (topSanPham != null) {

                for (TopSanPham sp : topSanPham) {

                    Row row =
                            sheet.createRow(
                                    topRowIndex++
                            );

                    taoOSo(
                            row,
                            0,
                            sp.getMaSP(),
                            dataStyle
                    );

                    /*
                     * suaTiengViet cũng xử lý được
                     * tên sản phẩm bị lỗi encoding từ SQL.
                     */
                    taoOChu(
                            row,
                            1,
                            sp.getTenSP(),
                            dataStyle
                    );

                    taoOSo(
                            row,
                            2,
                            sp.getSoLuongBan(),
                            dataStyle
                    );

                    taoOSo(
                            row,
                            3,
                            sp.getDoanhThu(),
                            moneyStyle
                    );
                }
            }

            /*
             * Độ rộng cột.
             */
            for (int i = 0; i < 4; i++) {

                sheet.autoSizeColumn(i);

                int currentWidth =
                        sheet.getColumnWidth(i);

                sheet.setColumnWidth(
                        i,
                        Math.min(
                                currentWidth + 1000,
                                255 * 256
                        )
                );
            }

            /*
             * Tên file.
             */
            String fileName;

            if ("nam".equals(kieu)) {

                fileName =
                        "ThongKeDoanhThu_"
                                + tu.getYear()
                                + ".xlsx";

            } else {

                fileName =
                        "ThongKeDoanhThu_"
                                + tu.getMonthValue()
                                + "_"
                                + tu.getYear()
                                + ".xlsx";
            }

            resp.reset();

            resp.setContentType(
                    "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"
            );

            resp.setHeader(
                    "Content-Disposition",
                    "attachment; filename=\""
                            + fileName
                            + "\""
            );

            resp.setHeader(
                    "Cache-Control",
                    "no-cache, no-store, must-revalidate"
            );

            workbook.write(
                    resp.getOutputStream()
            );

            resp.getOutputStream().flush();

        } catch (NumberFormatException e) {

            session.setAttribute(
                    "errorCode",
                    "DU_LIEU_THONG_KE_KHONG_HOP_LE"
            );

            resp.sendRedirect(
                    req.getContextPath() + "/thongke"
            );

        } catch (Exception e) {

            e.printStackTrace();

            session.setAttribute(
                    "errorCode",
                    "LOI_XUAT_EXCEL"
            );

            if (!resp.isCommitted()) {

                resp.sendRedirect(
                        req.getContextPath() + "/thongke"
                );
            }

        } finally {

            if (workbook != null) {

                try {
                    workbook.close();
                } catch (IOException ignored) {
                }
            }
        }
    }

    private void taoOChu(
            Row row,
            int column,
            String value,
            CellStyle style) {

        Cell cell =
                row.createCell(column);

        cell.setCellValue(
                suaTiengViet(value)
        );

        if (style != null) {
            cell.setCellStyle(style);
        }
    }

    private void taoOSo(
            Row row,
            int column,
            double value,
            CellStyle style) {

        Cell cell =
                row.createCell(column);

        cell.setCellValue(value);

        if (style != null) {
            cell.setCellStyle(style);
        }
    }

    /*
     * Sửa chuỗi UTF-8 bị đọc nhầm thành Windows-1252.
     *
     * Ví dụ:
     * THá»‘NG KÃŠ
     * thành:
     * THỐNG KÊ
     */
    private String suaTiengViet(
            String value) {

        if (value == null
                || value.isEmpty()) {

            return "";
        }

        /*
         * Chuỗi không có dấu hiệu lỗi thì giữ nguyên.
         */
        boolean biLoi =
                value.contains("Ã")
                        || value.contains("Â")
                        || value.contains("Ä")
                        || value.contains("áº")
                        || value.contains("á»")
                        || value.contains("Æ")
                        || value.contains("â");

        if (!biLoi) {
            return value;
        }

        try {

            byte[] bytes =
                    value.getBytes(
                            Charset.forName(
                                    "Windows-1252"
                            )
                    );

            return new String(
                    bytes,
                    StandardCharsets.UTF_8
            );

        } catch (Exception e) {

            return value;
        }
    }
}