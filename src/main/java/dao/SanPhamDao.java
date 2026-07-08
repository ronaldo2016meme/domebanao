
package dao;

import model.sanpham;
import service.ConnectService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SanPhamDao {

    // Hiển thị danh sách
    public List<sanpham> getAll() {

        List<sanpham> list = new ArrayList<>();

        String sql = "SELECT * FROM SANPHAM";

        try {

            Connection con = new ConnectService().myConnection();

            PreparedStatement ps = con.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                sanpham sp = new sanpham();

                sp.setMaSP(rs.getInt("MaSP"));
                sp.setTenSP(rs.getString("TenSP"));
                sp.setDanhMuc(rs.getString("MaDanhMuc"));
                sp.setNhaCungCap(rs.getString("MaNCC"));
                sp.setGiaBan(rs.getDouble("GiaBan"));
                sp.setMoTa(rs.getString("MoTa"));
                sp.setNgayTao(rs.getString("NgayTao"));
                sp.setNgayCapNhat(rs.getString("NgayCapNhat"));
                sp.setAnh(rs.getString("Anh"));

                list.add(sp);
            }

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    // Thêm sản phẩm
    public void insert(sanpham sp) {

        String sql = "INSERT INTO SANPHAM(TenSP,DanhMuc,NhaCungCap,GiaBan,MoTa,NgayTao,NgayCapNhat,Anh) "
                + "VALUES(?,?,?,?,?,?,?,?)";

        try {

            Connection con = new ConnectService().myConnection();

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, sp.getTenSP());
            ps.setString(2, sp.getDanhMuc());
            ps.setString(3, sp.getNhaCungCap());
            ps.setDouble(4, sp.getGiaBan());
            ps.setString(5, sp.getMoTa());
            ps.setString(6, sp.getNgayTao());
            ps.setString(7, sp.getNgayCapNhat());
            ps.setString(8, sp.getAnh());

            ps.executeUpdate();

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Lấy theo mã
    public sanpham getById(int id) {

        sanpham sp = null;

        String sql = "SELECT * FROM SANPHAM WHERE MaSP=?";

        try {

            Connection con = new ConnectService().myConnection();

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                sp = new sanpham();

                sp.setMaSP(rs.getInt("MaSP"));
                sp.setTenSP(rs.getString("TenSP"));
                sp.setDanhMuc(rs.getString("DanhMuc"));
                sp.setNhaCungCap(rs.getString("NhaCungCap"));
                sp.setGiaBan(rs.getDouble("GiaBan"));
                sp.setMoTa(rs.getString("MoTa"));
                sp.setNgayTao(rs.getString("NgayTao"));
                sp.setNgayCapNhat(rs.getString("NgayCapNhat"));
                sp.setAnh(rs.getString("Anh"));
            }

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return sp;
    }

    // Cập nhật
    public void update(sanpham sp) {

        String sql = "UPDATE SANPHAM SET TenSP=?,DanhMuc=?,NhaCungCap=?,GiaBan=?,MoTa=?,NgayTao=?,NgayCapNhat=?, Anh=? WHERE MaSP=?";

        try {

            Connection con = new ConnectService().myConnection();

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, sp.getTenSP());
            ps.setString(2, sp.getDanhMuc());
            ps.setString(3, sp.getNhaCungCap());
            ps.setDouble(4, sp.getGiaBan());
            ps.setString(5, sp.getMoTa());
            ps.setString(6, sp.getNgayTao());
            ps.setString(7, sp.getNgayCapNhat());
            ps.setInt(9, sp.getMaSP());
            ps.setString(10, sp.getAnh());

            ps.executeUpdate();

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Xóa
    public void delete(int id) {

        String sql = "DELETE FROM SANPHAM WHERE MaSP=?";

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
}