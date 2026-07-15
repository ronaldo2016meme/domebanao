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

        String sql = "SELECT sp.*, dm.TenDanhMuc, ncc.TenNCC, tt.TenTrangThai " +
                "FROM SANPHAM sp " +
                "INNER JOIN DanhMucSanPham dm ON sp.MaDanhMuc = dm.MaDanhMuc " +
                "INNER JOIN NhaCungCap ncc ON sp.MaNCC = ncc.MaNCC " +
                "INNER JOIN TrangThaiSanPham tt ON sp.MaTrangThaiSP = tt.MaTrangThaiSP";

        try {

            Connection con = new ConnectService().myConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                sanpham sp = new sanpham();

                sp.setMaSP(rs.getInt("MaSP"));
                sp.setTenSP(rs.getString("TenSP"));
                sp.setTenDanhMuc(rs.getString("TenDanhMuc"));
                sp.setTenNCC(rs.getString("TenNCC"));
                sp.setTenTrangThai(rs.getString("TenTrangThai"));
                sp.setGiaBan(rs.getDouble("GiaBan"));
                sp.setMoTa(rs.getString("MoTa"));
                sp.setNgayTao(rs.getString("NgayTao"));
                sp.setNgayCapNhat(rs.getString("NgayCapNhat"));
                sp.setAnh(rs.getString("Anh"));
                sp.setMaDanhMuc(rs.getString("MaDanhMuc"));
                sp.setMaNCC(rs.getString("MaNCC"));
                sp.setMaTrangThaiSP(rs.getString("MaTrangThaiSP"));

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

        String sql = "INSERT INTO SANPHAM(TenSP,GiaBan,MoTa,NgayTao,NgayCapNhat,Anh,MaTrangThaiSP,MaDanhMuc,MaNCC)"
                + " VALUES(?,?,?,?,?,?,?,?,?)";

        try {

            Connection con = new ConnectService().myConnection();
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, sp.getTenSP());
            ps.setDouble(2, sp.getGiaBan());
            ps.setString(3, sp.getMoTa());
            ps.setString(4, sp.getNgayTao());
            ps.setString(5, sp.getNgayCapNhat());
            ps.setString(6, sp.getAnh());
            ps.setString(7, sp.getMaTrangThaiSP());
            ps.setString(8, sp.getMaDanhMuc());
            ps.setString(9, sp.getMaNCC());

            ps.executeUpdate();

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Lấy theo mã
    public sanpham getById(int id) {

        sanpham sp = null;

        String sql = "SELECT sp.*, dm.TenDanhMuc, ncc.TenNCC, tt.TenTrangThai "
                + "FROM SANPHAM sp "
                + "INNER JOIN DanhMucSanPham dm ON sp.MaDanhMuc = dm.MaDanhMuc "
                + "INNER JOIN NhaCungCap ncc ON sp.MaNCC = ncc.MaNCC "
                + "INNER JOIN TrangThaiSanPham tt ON sp.MaTrangThaiSP = tt.MaTrangThaiSP "
                + "WHERE sp.MaSP = ?";

        try {

            Connection con = new ConnectService().myConnection();
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                sp = new sanpham();

                sp.setMaSP(rs.getInt("MaSP"));
                sp.setTenSP(rs.getString("TenSP"));
                sp.setTenDanhMuc(rs.getString("TenDanhMuc"));
                sp.setTenNCC(rs.getString("TenNCC"));
                sp.setTenTrangThai(rs.getString("TenTrangThai"));
                sp.setGiaBan(rs.getDouble("GiaBan"));
                sp.setMoTa(rs.getString("MoTa"));
                sp.setNgayTao(rs.getString("NgayTao"));
                sp.setNgayCapNhat(rs.getString("NgayCapNhat"));
                sp.setAnh(rs.getString("Anh"));
                sp.setMaDanhMuc(rs.getString("MaDanhMuc"));
                sp.setMaNCC(rs.getString("MaNCC"));
                sp.setMaTrangThaiSP(rs.getString("MaTrangThaiSP"));
            }

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return sp;
    }

    // Cập nhật
    public void update(sanpham sp) {

        String sql = "UPDATE SANPHAM SET "
                + "TenSP=?,GiaBan=?,MoTa=?,NgayTao=?,NgayCapNhat=?,Anh=?,"
                + "MaTrangThaiSP=?,MaDanhMuc=?,MaNCC=? "
                + "WHERE MaSP=?";

        try {

            Connection con = new ConnectService().myConnection();
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, sp.getTenSP());
            ps.setDouble(2, sp.getGiaBan());
            ps.setString(3, sp.getMoTa());
            ps.setString(4, sp.getNgayTao());
            ps.setString(5, sp.getNgayCapNhat());
            ps.setString(6, sp.getAnh());
            ps.setString(7, sp.getMaTrangThaiSP());
            ps.setString(8, sp.getMaDanhMuc());
            ps.setString(9, sp.getMaNCC());
            ps.setInt(10, sp.getMaSP());

            ps.executeUpdate();

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}