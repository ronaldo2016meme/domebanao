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
}