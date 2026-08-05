package dao;

import model.sanpham;
import service.ConnectService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SanPhamDao {

    /*
     * Lấy toàn bộ sản phẩm.
     *
     * Giá bán lấy từ SANPHAMCHITIET.
     * MIN(ct.GiaBan) là giá bán thấp nhất của các biến thể.
     */
    public List<sanpham> getAll() {

        List<sanpham> list = new ArrayList<>();

        String sql =
                "SELECT " +
                        "sp.MaSP, " +
                        "sp.TenSP, " +
                        "sp.MoTa, " +
                        "sp.NgayTao, " +
                        "sp.NgayCapNhat, " +
                        "sp.Anh, " +
                        "sp.MaDanhMuc, " +
                        "sp.MaNCC, " +
                        "sp.MaTrangThaiSP, " +
                        "dm.TenDanhMuc, " +
                        "ncc.TenNCC, " +
                        "tt.TenTrangThai, " +

                        "COALESCE(SUM(ct.SoLuongTon), 0) AS SoLuongTon, " +

                        "COALESCE(MIN(ct.GiaBan), 0) AS GiaBan " +

                        "FROM SANPHAM sp " +

                        "INNER JOIN DanhMucSanPham dm " +
                        "ON sp.MaDanhMuc = dm.MaDanhMuc " +

                        "INNER JOIN NhaCungCap ncc " +
                        "ON sp.MaNCC = ncc.MaNCC " +

                        "INNER JOIN TrangThaiSanPham tt " +
                        "ON sp.MaTrangThaiSP = tt.MaTrangThaiSP " +

                        "LEFT JOIN SANPHAMCHITIET ct " +
                        "ON sp.MaSP = ct.MaSP " +

                        "GROUP BY " +
                        "sp.MaSP, " +
                        "sp.TenSP, " +
                        "sp.MoTa, " +
                        "sp.NgayTao, " +
                        "sp.NgayCapNhat, " +
                        "sp.Anh, " +
                        "sp.MaDanhMuc, " +
                        "sp.MaNCC, " +
                        "sp.MaTrangThaiSP, " +
                        "dm.TenDanhMuc, " +
                        "ncc.TenNCC, " +
                        "tt.TenTrangThai " +

                        "ORDER BY sp.MaSP ASC";

        try (
                Connection con =
                        new ConnectService().myConnection();

                PreparedStatement ps =
                        con.prepareStatement(sql);

                ResultSet rs =
                        ps.executeQuery()
        ) {

            while (rs.next()) {

                sanpham sp =
                        mapSanPham(rs);

                list.add(sp);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    /*
     * Thêm sản phẩm.
     *
     * Không thêm GiaBan vào SANPHAM.
     */
    public boolean insert(sanpham sp) {

        String sql =
                "INSERT INTO SANPHAM " +
                        "(" +
                        "TenSP, " +
                        "MoTa, " +
                        "NgayTao, " +
                        "NgayCapNhat, " +
                        "Anh, " +
                        "MaTrangThaiSP, " +
                        "MaDanhMuc, " +
                        "MaNCC" +
                        ") " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (
                Connection con =
                        new ConnectService().myConnection();

                PreparedStatement ps =
                        con.prepareStatement(sql)
        ) {

            ps.setString(1, sp.getTenSP());
            ps.setString(2, sp.getMoTa());
            ps.setString(3, sp.getNgayTao());
            ps.setString(4, sp.getNgayCapNhat());
            ps.setString(5, sp.getAnh());
            ps.setString(6, sp.getMaTrangThaiSP());
            ps.setString(7, sp.getMaDanhMuc());
            ps.setString(8, sp.getMaNCC());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /*
     * Lấy sản phẩm theo mã.
     *
     * Giá bán cũng lấy từ SANPHAMCHITIET.
     */
    public sanpham getById(int id) {

        String sql =
                "SELECT " +
                        "sp.MaSP, " +
                        "sp.TenSP, " +
                        "sp.MoTa, " +
                        "sp.NgayTao, " +
                        "sp.NgayCapNhat, " +
                        "sp.Anh, " +
                        "sp.MaDanhMuc, " +
                        "sp.MaNCC, " +
                        "sp.MaTrangThaiSP, " +
                        "dm.TenDanhMuc, " +
                        "ncc.TenNCC, " +
                        "tt.TenTrangThai, " +

                        "COALESCE(SUM(ct.SoLuongTon), 0) AS SoLuongTon, " +

                        "COALESCE(MIN(ct.GiaBan), 0) AS GiaBan " +

                        "FROM SANPHAM sp " +

                        "INNER JOIN DanhMucSanPham dm " +
                        "ON sp.MaDanhMuc = dm.MaDanhMuc " +

                        "INNER JOIN NhaCungCap ncc " +
                        "ON sp.MaNCC = ncc.MaNCC " +

                        "INNER JOIN TrangThaiSanPham tt " +
                        "ON sp.MaTrangThaiSP = tt.MaTrangThaiSP " +

                        "LEFT JOIN SANPHAMCHITIET ct " +
                        "ON sp.MaSP = ct.MaSP " +

                        "WHERE sp.MaSP = ? " +

                        "GROUP BY " +
                        "sp.MaSP, " +
                        "sp.TenSP, " +
                        "sp.MoTa, " +
                        "sp.NgayTao, " +
                        "sp.NgayCapNhat, " +
                        "sp.Anh, " +
                        "sp.MaDanhMuc, " +
                        "sp.MaNCC, " +
                        "sp.MaTrangThaiSP, " +
                        "dm.TenDanhMuc, " +
                        "ncc.TenNCC, " +
                        "tt.TenTrangThai";

        try (
                Connection con =
                        new ConnectService().myConnection();

                PreparedStatement ps =
                        con.prepareStatement(sql)
        ) {

            ps.setInt(1, id);

            try (
                    ResultSet rs =
                            ps.executeQuery()
            ) {

                if (rs.next()) {
                    return mapSanPham(rs);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /*
     * Cập nhật thông tin sản phẩm.
     *
     * Không cập nhật GiaBan ở đây.
     */
    public boolean update(sanpham sp) {

        String sql =
                "UPDATE SANPHAM SET " +
                        "TenSP = ?, " +
                        "MoTa = ?, " +
                        "NgayTao = ?, " +
                        "NgayCapNhat = ?, " +
                        "Anh = ?, " +
                        "MaTrangThaiSP = ?, " +
                        "MaDanhMuc = ?, " +
                        "MaNCC = ? " +
                        "WHERE MaSP = ?";

        try (
                Connection con =
                        new ConnectService().myConnection();

                PreparedStatement ps =
                        con.prepareStatement(sql)
        ) {

            ps.setString(1, sp.getTenSP());
            ps.setString(2, sp.getMoTa());
            ps.setString(3, sp.getNgayTao());
            ps.setString(4, sp.getNgayCapNhat());
            ps.setString(5, sp.getAnh());
            ps.setString(6, sp.getMaTrangThaiSP());
            ps.setString(7, sp.getMaDanhMuc());
            ps.setString(8, sp.getMaNCC());
            ps.setInt(9, sp.getMaSP());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /*
     * Chuyển dữ liệu ResultSet thành model sanpham.
     */
    private sanpham mapSanPham(
            ResultSet rs) throws Exception {

        sanpham sp =
                new sanpham();

        sp.setMaSP(
                rs.getInt("MaSP")
        );

        sp.setTenSP(
                rs.getString("TenSP")
        );

        sp.setTenDanhMuc(
                rs.getString("TenDanhMuc")
        );

        sp.setTenNCC(
                rs.getString("TenNCC")
        );

        sp.setTenTrangThai(
                rs.getString("TenTrangThai")
        );

        /*
         * Giá bán lấy từ MIN(ct.GiaBan).
         */
        sp.setGiaBan(
                rs.getDouble("GiaBan")
        );

        sp.setMoTa(
                rs.getString("MoTa")
        );

        sp.setNgayTao(
                rs.getString("NgayTao")
        );

        sp.setNgayCapNhat(
                rs.getString("NgayCapNhat")
        );

        sp.setAnh(
                rs.getString("Anh")
        );

        sp.setMaDanhMuc(
                rs.getString("MaDanhMuc")
        );

        sp.setMaNCC(
                rs.getString("MaNCC")
        );

        sp.setMaTrangThaiSP(
                rs.getString("MaTrangThaiSP")
        );

        sp.setSoLuongTon(
                rs.getInt("SoLuongTon")
        );

        return sp;
    }
}