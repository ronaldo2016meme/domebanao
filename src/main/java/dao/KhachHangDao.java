package dao;

import model.KhachHang;
import service.ConnectService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class KhachHangDao {

    // =====================================================
    // LẤY DANH SÁCH KHÁCH HÀNG
    // =====================================================
    public List<KhachHang> getAll() {

        List<KhachHang> list = new ArrayList<>();

        String sql =
                "SELECT * FROM KHACHHANG " +
                        "ORDER BY MaKH ASC";

        try (
                Connection con =
                        new ConnectService().myConnection();

                PreparedStatement ps =
                        con.prepareStatement(sql);

                ResultSet rs =
                        ps.executeQuery()
        ) {

            while (rs.next()) {

                KhachHang kh =
                        new KhachHang();

                kh.setMaKH(
                        rs.getInt("MaKH")
                );

                kh.setHoTen(
                        rs.getString("HoTen")
                );

                kh.setSdt(
                        rs.getString("SDT")
                );

                kh.setDiaChi(
                        rs.getString("DiaChi")
                );

                kh.setDiemTichLuy(
                        rs.getInt("DiemTichLuy")
                );

                list.add(kh);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }


    // =====================================================
    // THÊM KHÁCH HÀNG
    // =====================================================
    public void insert(KhachHang kh) {

        String sql =
                "INSERT INTO KHACHHANG " +
                        "(HoTen, SDT, DiaChi, DiemTichLuy) " +
                        "VALUES (?, ?, ?, ?)";

        try (
                Connection con =
                        new ConnectService().myConnection();

                PreparedStatement ps =
                        con.prepareStatement(sql)
        ) {

            ps.setString(
                    1,
                    kh.getHoTen()
            );

            ps.setString(
                    2,
                    kh.getSdt()
            );

            ps.setString(
                    3,
                    kh.getDiaChi()
            );

            // Khách mới mặc định 0 điểm
            ps.setInt(
                    4,
                    0
            );

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // =====================================================
    // LẤY KHÁCH HÀNG THEO ID
    // =====================================================
    public KhachHang getById(int id) {

        String sql =
                "SELECT * FROM KHACHHANG " +
                        "WHERE MaKH = ?";

        try (
                Connection con =
                        new ConnectService().myConnection();

                PreparedStatement ps =
                        con.prepareStatement(sql)
        ) {

            ps.setInt(
                    1,
                    id
            );

            try (
                    ResultSet rs =
                            ps.executeQuery()
            ) {

                if (rs.next()) {

                    KhachHang kh =
                            new KhachHang();

                    kh.setMaKH(
                            rs.getInt("MaKH")
                    );

                    kh.setHoTen(
                            rs.getString("HoTen")
                    );

                    kh.setSdt(
                            rs.getString("SDT")
                    );

                    kh.setDiaChi(
                            rs.getString("DiaChi")
                    );

                    kh.setDiemTichLuy(
                            rs.getInt("DiemTichLuy")
                    );

                    return kh;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }


    // =====================================================
    // CẬP NHẬT KHÁCH HÀNG
    // Không cập nhật điểm tích lũy
    // =====================================================
    public void update(KhachHang kh) {

        String sql =
                "UPDATE KHACHHANG SET " +
                        "HoTen = ?, " +
                        "SDT = ?, " +
                        "DiaChi = ? " +
                        "WHERE MaKH = ?";

        try (
                Connection con =
                        new ConnectService().myConnection();

                PreparedStatement ps =
                        con.prepareStatement(sql)
        ) {

            ps.setString(
                    1,
                    kh.getHoTen()
            );

            ps.setString(
                    2,
                    kh.getSdt()
            );

            ps.setString(
                    3,
                    kh.getDiaChi()
            );

            ps.setInt(
                    4,
                    kh.getMaKH()
            );

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // =====================================================
    // XÓA KHÁCH HÀNG
    // =====================================================
    public void delete(int id) {

        String sql =
                "DELETE FROM KHACHHANG " +
                        "WHERE MaKH = ?";

        try (
                Connection con =
                        new ConnectService().myConnection();

                PreparedStatement ps =
                        con.prepareStatement(sql)
        ) {

            ps.setInt(
                    1,
                    id
            );

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // =====================================================
    // TÌM KIẾM KHÁCH HÀNG
    // =====================================================
    public List<KhachHang> search(
            String keyword) {

        List<KhachHang> list =
                new ArrayList<>();

        String sql =
                "SELECT * FROM KHACHHANG " +
                        "WHERE HoTen LIKE ? " +
                        "OR SDT LIKE ? " +
                        "OR DiaChi LIKE ? " +
                        "ORDER BY MaKH ASC";

        try (
                Connection con =
                        new ConnectService().myConnection();

                PreparedStatement ps =
                        con.prepareStatement(sql)
        ) {

            String key =
                    "%" + keyword + "%";

            ps.setString(
                    1,
                    key
            );

            ps.setString(
                    2,
                    key
            );

            ps.setString(
                    3,
                    key
            );

            try (
                    ResultSet rs =
                            ps.executeQuery()
            ) {

                while (rs.next()) {

                    KhachHang kh =
                            new KhachHang();

                    kh.setMaKH(
                            rs.getInt("MaKH")
                    );

                    kh.setHoTen(
                            rs.getString("HoTen")
                    );

                    kh.setSdt(
                            rs.getString("SDT")
                    );

                    kh.setDiaChi(
                            rs.getString("DiaChi")
                    );

                    kh.setDiemTichLuy(
                            rs.getInt("DiemTichLuy")
                    );

                    list.add(kh);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }


    // =====================================================
    // KIỂM TRA SỐ ĐIỆN THOẠI ĐÃ TỒN TẠI
    // =====================================================
    public boolean isPhoneExists(
            String sdt) {

        String sql =
                "SELECT COUNT(*) " +
                        "FROM KHACHHANG " +
                        "WHERE SDT = ?";

        try (
                Connection con =
                        new ConnectService().myConnection();

                PreparedStatement ps =
                        con.prepareStatement(sql)
        ) {

            ps.setString(
                    1,
                    sdt
            );

            try (
                    ResultSet rs =
                            ps.executeQuery()
            ) {

                if (rs.next()) {

                    return rs.getInt(1)
                            > 0;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }


    // =====================================================
    // KIỂM TRA SĐT KHI SỬA
    // =====================================================
    public boolean isPhoneExistsForUpdate(
            String sdt,
            int maKH) {

        String sql =
                "SELECT COUNT(*) " +
                        "FROM KHACHHANG " +
                        "WHERE SDT = ? " +
                        "AND MaKH <> ?";

        try (
                Connection con =
                        new ConnectService().myConnection();

                PreparedStatement ps =
                        con.prepareStatement(sql)
        ) {

            ps.setString(
                    1,
                    sdt
            );

            ps.setInt(
                    2,
                    maKH
            );

            try (
                    ResultSet rs =
                            ps.executeQuery()
            ) {

                if (rs.next()) {

                    return rs.getInt(1)
                            > 0;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }


    // =====================================================
    // LẤY KHÁCH HÀNG THEO SĐT
    // =====================================================
    public KhachHang getBySoDienThoai(
            String sdt) {

        String sql =
                "SELECT * FROM KHACHHANG " +
                        "WHERE SDT = ?";

        try (
                Connection con =
                        new ConnectService().myConnection();

                PreparedStatement ps =
                        con.prepareStatement(sql)
        ) {

            ps.setString(
                    1,
                    sdt
            );

            try (
                    ResultSet rs =
                            ps.executeQuery()
            ) {

                if (rs.next()) {

                    KhachHang kh =
                            new KhachHang();

                    kh.setMaKH(
                            rs.getInt("MaKH")
                    );

                    kh.setHoTen(
                            rs.getString("HoTen")
                    );

                    kh.setSdt(
                            rs.getString("SDT")
                    );

                    kh.setDiaChi(
                            rs.getString("DiaChi")
                    );

                    kh.setDiemTichLuy(
                            rs.getInt("DiemTichLuy")
                    );

                    return kh;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }


    // =====================================================
    // CỘNG ĐIỂM
    // =====================================================
    public boolean congDiem(
            int maKH,
            int diem) {

        if (diem <= 0) {
            return false;
        }

        String sql =
                "UPDATE KHACHHANG " +
                        "SET DiemTichLuy = DiemTichLuy + ? " +
                        "WHERE MaKH = ?";

        try (
                Connection con =
                        new ConnectService().myConnection();

                PreparedStatement ps =
                        con.prepareStatement(sql)
        ) {

            ps.setInt(
                    1,
                    diem
            );

            ps.setInt(
                    2,
                    maKH
            );

            return ps.executeUpdate()
                    > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }


    // =====================================================
    // TRỪ ĐIỂM
    // =====================================================
    public boolean truDiem(
            int maKH,
            int diem) {

        if (diem <= 0) {
            return false;
        }

        String sql =
                "UPDATE KHACHHANG " +
                        "SET DiemTichLuy = DiemTichLuy - ? " +
                        "WHERE MaKH = ? " +
                        "AND DiemTichLuy >= ?";

        try (
                Connection con =
                        new ConnectService().myConnection();

                PreparedStatement ps =
                        con.prepareStatement(sql)
        ) {

            ps.setInt(
                    1,
                    diem
            );

            ps.setInt(
                    2,
                    maKH
            );

            ps.setInt(
                    3,
                    diem
            );

            return ps.executeUpdate()
                    > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }


    // =====================================================
    // LẤY ĐIỂM TÍCH LŨY
    // =====================================================
    public int getDiemTichLuy(
            int maKH) {

        String sql =
                "SELECT DiemTichLuy " +
                        "FROM KHACHHANG " +
                        "WHERE MaKH = ?";

        try (
                Connection con =
                        new ConnectService().myConnection();

                PreparedStatement ps =
                        con.prepareStatement(sql)
        ) {

            ps.setInt(
                    1,
                    maKH
            );

            try (
                    ResultSet rs =
                            ps.executeQuery()
            ) {

                if (rs.next()) {

                    return rs.getInt(
                            "DiemTichLuy"
                    );
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }
}