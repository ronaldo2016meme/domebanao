package dao;

import model.ChiTietHoaDon;
import model.HoaDon;
import service.ConnectService;

import java.sql.*;
import java.util.List;

public class HoaDonDao {

    public int thanhToan(HoaDon hd, List<ChiTietHoaDon> list) {

        Connection con = null;

        try {

            con = new ConnectService().myConnection();

            con.setAutoCommit(false);

            // ======================
            // Thêm hóa đơn
            // ======================

            String sqlHD =
                    "INSERT INTO HOADON(" +
                            "NgayLap," +
                            "TongTien," +
                            "TienKhachDua," +
                            "TienThua," +
                            "PhuongThucThanhToan," +
                            "MaNV," +
                            "MaKH," +
                            "MaTrangThaiHD)" +
                            " VALUES(?,?,?,?,?,?,?,?)";

            PreparedStatement psHD =
                    con.prepareStatement(sqlHD,
                            Statement.RETURN_GENERATED_KEYS);

            psHD.setDate(1, hd.getNgayLap());
            psHD.setDouble(2, hd.getTongTien());
            psHD.setDouble(3, hd.getTienKhachDua());
            psHD.setDouble(4, hd.getTienThua());
            psHD.setString(5, hd.getPhuongThucThanhToan());
            psHD.setInt(6, hd.getMaNV());
            if (hd.getMaKH() == null) {
                psHD.setNull(7, java.sql.Types.INTEGER);
            } else {
                psHD.setInt(7, hd.getMaKH());
            }
            psHD.setString(8, hd.getMaTrangThaiHD());

            psHD.executeUpdate();

            ResultSet rs = psHD.getGeneratedKeys();

            int maHD = 0;

            if (rs.next()) {

                maHD = rs.getInt(1);

            } else {

                con.rollback();
                return -1;

            }

            // ======================
            // Thêm chi tiết hóa đơn
            // ======================

            String sqlCT =
                    "INSERT INTO CHITIETHOADON(" +
                            "MaHD," +
                            "MaSPCT," +
                            "SoLuong," +
                            "DonGia," +
                            "ThanhTien)" +
                            " VALUES(?,?,?,?,?)";

            PreparedStatement psCT =
                    con.prepareStatement(sqlCT);

            // ======================
            // Trừ tồn kho
            // ======================

            String sqlTon =
                    "UPDATE SANPHAMCHITIET " +
                            "SET SoLuongTon = SoLuongTon - ? " +
                            "WHERE MaSPCT=?";

            PreparedStatement psTon =
                    con.prepareStatement(sqlTon);

            for (ChiTietHoaDon ct : list) {

                // Kiểm tra tồn kho

                String sqlCheck =
                        "SELECT SoLuongTon " +
                                "FROM SANPHAMCHITIET " +
                                "WHERE MaSPCT=?";

                PreparedStatement psCheck =
                        con.prepareStatement(sqlCheck);

                psCheck.setInt(1, ct.getMaSPCT());

                ResultSet rsTon =
                        psCheck.executeQuery();

                if (rsTon.next()) {

                    int ton = rsTon.getInt("SoLuongTon");

                    if (ct.getSoLuong() > ton) {

                        con.rollback();

                        return -1;

                    }

                }

                // Lưu chi tiết hóa đơn

                psCT.setInt(1, maHD);
                psCT.setInt(2, ct.getMaSPCT());
                psCT.setInt(3, ct.getSoLuong());
                psCT.setDouble(4, ct.getDonGia());
                psCT.setDouble(5, ct.getThanhTien());

                psCT.executeUpdate();

                // Trừ tồn

                psTon.setInt(1, ct.getSoLuong());
                psTon.setInt(2, ct.getMaSPCT());

                psTon.executeUpdate();

            }

            con.commit();

            return maHD;

        } catch (Exception e) {

            e.printStackTrace();

            try {

                if (con != null)

                    con.rollback();

            } catch (Exception ex) {

                ex.printStackTrace();

            }

        } finally {

            try {

                if (con != null) {

                    con.setAutoCommit(true);

                    con.close();

                }

            } catch (Exception e) {

                e.printStackTrace();

            }

        }

        return -1;

    }

}