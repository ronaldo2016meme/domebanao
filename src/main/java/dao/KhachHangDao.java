package dao;

import model.KhachHang;
import service.ConnectService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class KhachHangDao {

    // Lấy danh sách khách hàng
    public List<KhachHang> getAll() {

        List<KhachHang> list = new ArrayList<>();

        String sql = "SELECT * FROM KHACHHANG";

        try {
            Connection con = new ConnectService().myConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                KhachHang kh = new KhachHang();

                kh.setMaKH(rs.getInt("MaKH"));
                kh.setHoTen(rs.getString("HoTen"));
                kh.setSdt(rs.getString("SDT"));
                kh.setDiaChi(rs.getString("DiaChi"));

                list.add(kh);
            }

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    // Thêm khách hàng
    public void insert(KhachHang kh) {

        String sql = "INSERT INTO KHACHHANG(HoTen,SDT,DiaChi) VALUES(?,?,?)";

        try {

            Connection con = new ConnectService().myConnection();
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, kh.getHoTen());
            ps.setString(2, kh.getSdt());
            ps.setString(3, kh.getDiaChi());

            ps.executeUpdate();

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Lấy khách hàng theo id
    public KhachHang getById(int id) {

        KhachHang kh = null;

        String sql = "SELECT * FROM KHACHHANG WHERE MaKH=?";

        try {

            Connection con = new ConnectService().myConnection();
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                kh = new KhachHang();

                kh.setMaKH(rs.getInt("MaKH"));
                kh.setHoTen(rs.getString("HoTen"));
                kh.setSdt(rs.getString("SDT"));
                kh.setDiaChi(rs.getString("DiaChi"));
            }

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return kh;
    }

    // Cập nhật khách hàng
    public void update(KhachHang kh) {

        String sql = "UPDATE KHACHHANG SET HoTen=?, SDT=?, DiaChi=? WHERE MaKH=?";

        try {

            Connection con = new ConnectService().myConnection();
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, kh.getHoTen());
            ps.setString(2, kh.getSdt());
            ps.setString(3, kh.getDiaChi());
            ps.setInt(4, kh.getMaKH());

            ps.executeUpdate();

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Xóa khách hàng
    public void delete(int id) {

        String sql = "DELETE FROM KHACHHANG WHERE MaKH=?";

        try {

            Connection con = new ConnectService().myConnection();
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, id);

            ps.executeUpdate();

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Tìm kiếm theo tên
    public List<KhachHang> search(String keyword) {

        List<KhachHang> list = new ArrayList<>();

        String sql = "SELECT * FROM KHACHHANG WHERE HoTen LIKE ?";

        try {

            Connection con = new ConnectService().myConnection();
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, "%" + keyword + "%");

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                KhachHang kh = new KhachHang();

                kh.setMaKH(rs.getInt("MaKH"));
                kh.setHoTen(rs.getString("HoTen"));
                kh.setSdt(rs.getString("SDT"));
                kh.setDiaChi(rs.getString("DiaChi"));

                list.add(kh);
            }

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}