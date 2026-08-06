package dao;

import model.TaiKhoan;
import service.ConnectService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class TaiKhoanDao {

    public TaiKhoan login(String user, String pass) {

        TaiKhoan tk = null;

        String sql =
                "SELECT tk.* " +
                        "FROM TaiKhoan tk " +
                        "INNER JOIN NHANVIEN nv " +
                        "ON tk.MaNV = nv.MaNV " +
                        "WHERE tk.TenDangNhap = ? " +
                        "AND tk.MatKhau = ? " +
                        "AND tk.TrangThai = 1 " +
                        "AND nv.MaTrangThai = 'TTNV01'";

        try (
                Connection con =
                        new ConnectService().myConnection();

                PreparedStatement ps =
                        con.prepareStatement(sql)
        ) {

            ps.setString(1, user);
            ps.setString(2, pass);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {

                    tk = new TaiKhoan();

                    tk.setMaTK(
                            rs.getInt("MaTK")
                    );

                    tk.setTenDangNhap(
                            rs.getString("TenDangNhap")
                    );

                    tk.setMatKhau(
                            rs.getString("MatKhau")
                    );

                    tk.setTrangThai(
                            rs.getBoolean("TrangThai")
                    );

                    tk.setMaRole(
                            rs.getString("MaRole")
                    );

                    tk.setMaNV(
                            rs.getInt("MaNV")
                    );
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return tk;
    }

    // Kiểm tra tên đăng nhập đã tồn tại
    public boolean checkUsername(String username) {

        String sql =
                "SELECT 1 " +
                        "FROM TaiKhoan " +
                        "WHERE TenDangNhap = ?";

        try (
                Connection con =
                        new ConnectService().myConnection();

                PreparedStatement ps =
                        con.prepareStatement(sql)
        ) {

            ps.setString(1, username);

            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    // Kiểm tra nhân viên đã có tài khoản chưa
    public boolean checkNhanVien(int maNV) {

        String sql =
                "SELECT 1 " +
                        "FROM TaiKhoan " +
                        "WHERE MaNV = ?";

        try (
                Connection con =
                        new ConnectService().myConnection();

                PreparedStatement ps =
                        con.prepareStatement(sql)
        ) {

            ps.setInt(1, maNV);

            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    // Thêm tài khoản
    public boolean insert(TaiKhoan tk) {

        String sql =
                "INSERT INTO TaiKhoan " +
                        "(TenDangNhap, MatKhau, TrangThai, MaRole, MaNV) " +
                        "VALUES (?, ?, ?, ?, ?)";

        try (
                Connection con =
                        new ConnectService().myConnection();

                PreparedStatement ps =
                        con.prepareStatement(sql)
        ) {

            ps.setString(
                    1,
                    tk.getTenDangNhap()
            );

            ps.setString(
                    2,
                    tk.getMatKhau()
            );

            ps.setBoolean(
                    3,
                    tk.isTrangThai()
            );

            ps.setString(
                    4,
                    tk.getMaRole()
            );

            ps.setInt(
                    5,
                    tk.getMaNV()
            );

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
}