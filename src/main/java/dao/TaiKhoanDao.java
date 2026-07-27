package dao;

import model.TaiKhoan;
import service.ConnectService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class TaiKhoanDao {

    public TaiKhoan login(String user, String pass) {

        TaiKhoan tk = null;

        String sql = "SELECT * FROM TaiKhoan "
                + "WHERE TenDangNhap=? AND MatKhau=? AND TrangThai=1";

        try {

            ConnectService service = new ConnectService();
            Connection con = service.myConnection();

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, user);
            ps.setString(2, pass);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                tk = new TaiKhoan();

                tk.setMaTK(rs.getInt("MaTK"));
                tk.setTenDangNhap(rs.getString("TenDangNhap"));
                tk.setMaRole(rs.getString("MaRole"));
                tk.setMaNV(rs.getInt("MaNV"));
            }

            rs.close();
            ps.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return tk;
    }
    // Kiểm tra tên đăng nhập đã tồn tại
    public boolean checkUsername(String username) {

        String sql = "SELECT * FROM TaiKhoan WHERE TenDangNhap=?";

        try {

            ConnectService service = new ConnectService();
            Connection con = service.myConnection();

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, username);

            ResultSet rs = ps.executeQuery();

            boolean exists = rs.next();

            rs.close();
            ps.close();
            con.close();

            return exists;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
    // Kiểm tra nhân viên đã có tài khoản chưa
    public boolean checkNhanVien(int maNV) {

        String sql = "SELECT * FROM TaiKhoan WHERE MaNV=?";

        try {

            ConnectService service = new ConnectService();
            Connection con = service.myConnection();

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, maNV);

            ResultSet rs = ps.executeQuery();

            boolean exists = rs.next();

            rs.close();
            ps.close();
            con.close();

            return exists;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
    // Thêm tài khoản
    public boolean insert(TaiKhoan tk) {

        String sql = "INSERT INTO TaiKhoan "
                + "(TenDangNhap, MatKhau, TrangThai, MaRole, MaNV) "
                + "VALUES (?,?,?,?,?)";

        try {

            ConnectService service = new ConnectService();
            Connection con = service.myConnection();

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, tk.getTenDangNhap());
            ps.setString(2, tk.getMatKhau());
            ps.setBoolean(3, tk.isTrangThai());
            ps.setString(4, tk.getMaRole());
            ps.setInt(5, tk.getMaNV());

            boolean ok = ps.executeUpdate() > 0;

            ps.close();
            con.close();

            return ok;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
}