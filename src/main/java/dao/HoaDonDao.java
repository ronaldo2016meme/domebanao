package dao;

import model.ChiTietHoaDon;
import model.HoaDon;
import service.ConnectService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HoaDonDao {

    public int thanhToan(HoaDon hd, List<ChiTietHoaDon> list) {

        Connection con = null;

        try {

            con = new ConnectService().myConnection();

            con.setAutoCommit(false);

            // ======================
            // THÊM HÓA ĐƠN
            // ======================

            String sqlHD =
                    "INSERT INTO HOADON(" +
                            "NgayLap," +
                            "TongTien," +
                            "TienGiam," +
                            "TienKhachDua," +
                            "TienThua," +
                            "PhuongThucThanhToan," +
                            "MaNV," +
                            "MaKH," +
                            "MaTrangThaiHD)" +
                            " VALUES(?,?,?,?,?,?,?,?,?)";

            PreparedStatement psHD =
                    con.prepareStatement(
                            sqlHD,
                            Statement.RETURN_GENERATED_KEYS
                    );

            psHD.setDate(1, hd.getNgayLap());

            // Tổng tiền sau giảm
            psHD.setDouble(2, hd.getTongTien());

            // Số tiền được giảm
            psHD.setDouble(3, hd.getTienGiam());

            psHD.setDouble(4, hd.getTienKhachDua());

            psHD.setDouble(5, hd.getTienThua());

            psHD.setString(
                    6,
                    hd.getPhuongThucThanhToan()
            );

            psHD.setInt(
                    7,
                    hd.getMaNV()
            );

            // Khách hàng có thể NULL
            if (hd.getMaKH() == null) {

                psHD.setNull(
                        8,
                        Types.INTEGER
                );

            } else {

                psHD.setInt(
                        8,
                        hd.getMaKH()
                );
            }

            psHD.setString(
                    9,
                    hd.getMaTrangThaiHD()
            );

            psHD.executeUpdate();

            ResultSet rs =
                    psHD.getGeneratedKeys();

            int maHD = 0;

            if (rs.next()) {

                maHD = rs.getInt(1);

            } else {

                con.rollback();

                return -1;
            }

            // ======================
            // THÊM CHI TIẾT HÓA ĐƠN
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
            // TRỪ TỒN KHO
            // ======================

            String sqlTon =
                    "UPDATE SANPHAMCHITIET " +
                            "SET SoLuongTon = SoLuongTon - ? " +
                            "WHERE MaSPCT=?";

            PreparedStatement psTon =
                    con.prepareStatement(sqlTon);

            for (ChiTietHoaDon ct : list) {

                // ======================
                // KIỂM TRA TỒN KHO
                // ======================

                String sqlCheck =
                        "SELECT SoLuongTon " +
                                "FROM SANPHAMCHITIET " +
                                "WHERE MaSPCT=?";

                PreparedStatement psCheck =
                        con.prepareStatement(sqlCheck);

                psCheck.setInt(
                        1,
                        ct.getMaSPCT()
                );

                ResultSet rsTon =
                        psCheck.executeQuery();

                if (rsTon.next()) {

                    int ton =
                            rsTon.getInt(
                                    "SoLuongTon"
                            );

                    if (ct.getSoLuong() > ton) {

                        con.rollback();

                        return -1;
                    }
                }

                // ======================
                // LƯU CHI TIẾT HÓA ĐƠN
                // ======================

                psCT.setInt(
                        1,
                        maHD
                );

                psCT.setInt(
                        2,
                        ct.getMaSPCT()
                );

                psCT.setInt(
                        3,
                        ct.getSoLuong()
                );

                // Giữ nguyên đơn giá gốc
                psCT.setDouble(
                        4,
                        ct.getDonGia()
                );

                // Giữ nguyên thành tiền gốc
                psCT.setDouble(
                        5,
                        ct.getThanhTien()
                );

                psCT.executeUpdate();

                // ======================
                // TRỪ TỒN
                // ======================

                psTon.setInt(
                        1,
                        ct.getSoLuong()
                );

                psTon.setInt(
                        2,
                        ct.getMaSPCT()
                );

                psTon.executeUpdate();
            }

            con.commit();

            return maHD;

        } catch (Exception e) {

            e.printStackTrace();

            try {

                if (con != null) {
                    con.rollback();
                }

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


    // =====================================================
    // MAP HÓA ĐƠN
    // =====================================================

    private HoaDon mapHoaDon(
            ResultSet rs)
            throws SQLException {

        HoaDon hd =
                new HoaDon();

        hd.setMaHD(
                rs.getInt("MaHD")
        );

        hd.setNgayLap(
                rs.getDate("NgayLap")
        );

        hd.setTongTien(
                rs.getDouble("TongTien")
        );

        // THÊM TIỀN GIẢM
        hd.setTienGiam(
                rs.getDouble("TienGiam")
        );

        hd.setTienKhachDua(
                rs.getDouble("TienKhachDua")
        );

        hd.setTienThua(
                rs.getDouble("TienThua")
        );

        hd.setPhuongThucThanhToan(
                rs.getString(
                        "PhuongThucThanhToan"
                )
        );

        hd.setMaNV(
                rs.getInt("MaNV")
        );

        int maKH =
                rs.getInt("MaKH");

        if (rs.wasNull()) {

            hd.setMaKH(null);

        } else {

            hd.setMaKH(maKH);
        }

        hd.setMaTrangThaiHD(
                rs.getString(
                        "MaTrangThaiHD"
                )
        );

        hd.setTenKH(
                rs.getString("TenKH")
        );

        hd.setTenNV(
                rs.getString("TenNV")
        );

        hd.setTenTrangThai(
                rs.getString("TenTrangThai")
        );

        return hd;
    }


    // =====================================================
    // LẤY TẤT CẢ HÓA ĐƠN
    // =====================================================

    public List<HoaDon> getAllHoaDon() {

        List<HoaDon> list =
                new ArrayList<>();

        String sql =
                "SELECT " +
                        "hd.MaHD, " +
                        "hd.NgayLap, " +
                        "hd.TongTien, " +
                        "hd.TienGiam, " +
                        "hd.TienKhachDua, " +
                        "hd.TienThua, " +
                        "hd.PhuongThucThanhToan, " +
                        "hd.MaNV, " +
                        "hd.MaKH, " +
                        "hd.MaTrangThaiHD, " +

                        "ISNULL(kh.HoTen, '') AS TenKH, " +

                        "ISNULL(" +
                        "nv.HoTen, " +
                        "N'Không xác định'" +
                        ") AS TenNV, " +

                        "ISNULL(" +
                        "tt.TenTrangThai, " +
                        "hd.MaTrangThaiHD" +
                        ") AS TenTrangThai " +

                        "FROM HOADON hd " +

                        "LEFT JOIN KHACHHANG kh " +
                        "ON hd.MaKH = kh.MaKH " +

                        "LEFT JOIN NHANVIEN nv " +
                        "ON hd.MaNV = nv.MaNV " +

                        "LEFT JOIN TrangThaiHoaDon tt " +
                        "ON hd.MaTrangThaiHD = tt.MaTrangThaiHD " +

                        "ORDER BY hd.MaHD DESC";

        try (
                Connection con =
                        new ConnectService()
                                .myConnection();

                PreparedStatement ps =
                        con.prepareStatement(sql);

                ResultSet rs =
                        ps.executeQuery()
        ) {

            while (rs.next()) {

                HoaDon hd =
                        mapHoaDon(rs);

                list.add(hd);
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return list;
    }


    // =====================================================
    // LẤY HÓA ĐƠN THEO ID
    // =====================================================

    public HoaDon getHoaDonById(
            int maHD) {

        String sql =
                "SELECT " +
                        "hd.MaHD, " +
                        "hd.NgayLap, " +
                        "hd.TongTien, " +
                        "hd.TienGiam, " +
                        "hd.TienKhachDua, " +
                        "hd.TienThua, " +
                        "hd.PhuongThucThanhToan, " +
                        "hd.MaNV, " +
                        "hd.MaKH, " +
                        "hd.MaTrangThaiHD, " +

                        "ISNULL(kh.HoTen, '') AS TenKH, " +

                        "ISNULL(" +
                        "nv.HoTen, " +
                        "N'Không xác định'" +
                        ") AS TenNV, " +

                        "ISNULL(" +
                        "tt.TenTrangThai, " +
                        "hd.MaTrangThaiHD" +
                        ") AS TenTrangThai " +

                        "FROM HOADON hd " +

                        "LEFT JOIN KHACHHANG kh " +
                        "ON hd.MaKH = kh.MaKH " +

                        "LEFT JOIN NHANVIEN nv " +
                        "ON hd.MaNV = nv.MaNV " +

                        "LEFT JOIN TrangThaiHoaDon tt " +
                        "ON hd.MaTrangThaiHD = tt.MaTrangThaiHD " +

                        "WHERE hd.MaHD = ?";

        try (
                Connection con =
                        new ConnectService()
                                .myConnection();

                PreparedStatement ps =
                        con.prepareStatement(sql)
        ) {

            ps.setInt(
                    1,
                    maHD
            );

            try (
                    ResultSet rs =
                            ps.executeQuery()
            ) {

                if (rs.next()) {

                    return mapHoaDon(rs);
                }
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return null;
    }


    // =====================================================
    // LẤY CHI TIẾT HÓA ĐƠN
    // =====================================================

    public List<ChiTietHoaDon>
    getChiTietHoaDon(int maHD) {

        List<ChiTietHoaDon> list =
                new ArrayList<>();

        String sql =
                "SELECT " +
                        "ct.MaCTHD, " +
                        "ct.MaHD, " +
                        "ct.MaSPCT, " +
                        "ct.SoLuong, " +
                        "ct.DonGia, " +
                        "ct.ThanhTien, " +

                        "sp.TenSP, " +
                        "ms.TenMau, " +
                        "s.TenSize " +

                        "FROM CHITIETHOADON ct " +

                        "INNER JOIN SANPHAMCHITIET spct " +
                        "ON ct.MaSPCT = spct.MaSPCT " +

                        "INNER JOIN SANPHAM sp " +
                        "ON spct.MaSP = sp.MaSP " +

                        "INNER JOIN MauSac ms " +
                        "ON spct.MaMau = ms.MaMau " +

                        "INNER JOIN Size s " +
                        "ON spct.MaSize = s.MaSize " +

                        "WHERE ct.MaHD = ? " +

                        "ORDER BY ct.MaCTHD ASC";

        try (
                Connection con =
                        new ConnectService()
                                .myConnection();

                PreparedStatement ps =
                        con.prepareStatement(sql)
        ) {

            ps.setInt(
                    1,
                    maHD
            );

            try (
                    ResultSet rs =
                            ps.executeQuery()
            ) {

                while (rs.next()) {

                    ChiTietHoaDon ct =
                            new ChiTietHoaDon();

                    ct.setMaCTHD(
                            rs.getInt("MaCTHD")
                    );

                    ct.setMaHD(
                            rs.getInt("MaHD")
                    );

                    ct.setMaSPCT(
                            rs.getInt("MaSPCT")
                    );

                    ct.setSoLuong(
                            rs.getInt("SoLuong")
                    );

                    ct.setDonGia(
                            rs.getDouble("DonGia")
                    );

                    ct.setThanhTien(
                            rs.getDouble("ThanhTien")
                    );

                    ct.setTenSP(
                            rs.getString("TenSP")
                    );

                    ct.setTenMau(
                            rs.getString("TenMau")
                    );

                    ct.setTenSize(
                            rs.getString("TenSize")
                    );

                    list.add(ct);
                }
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return list;
    }
}