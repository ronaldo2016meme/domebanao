package dao;

import model.SanPhamChiTiet;
import service.ConnectService;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SanPhamChiTietDao {

    public List<SanPhamChiTiet> getAll() {

        List<SanPhamChiTiet> list = new ArrayList<>();

        String sql =
                "SELECT ct.*, sp.TenSP, ms.TenMau, s.TenSize " +
                        "FROM SANPHAMCHITIET ct " +
                        "JOIN SANPHAM sp ON ct.MaSP = sp.MaSP " +
                        "JOIN MauSac ms ON ct.MaMau = ms.MaMau " +
                        "JOIN Size s ON ct.MaSize = s.MaSize";

        try {
            Connection con = new ConnectService().myConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                SanPhamChiTiet ct = new SanPhamChiTiet();

                ct.setMaSPCT(rs.getInt("MaSPCT"));
                ct.setMaSP(rs.getInt("MaSP"));
                ct.setMaMau(rs.getString("MaMau"));
                ct.setMaSize(rs.getString("MaSize"));
                ct.setSoLuongTon(rs.getInt("SoLuongTon"));
                ct.setGiaNhap(rs.getBigDecimal("GiaNhap"));

                ct.setTenSP(rs.getString("TenSP"));
                ct.setTenMau(rs.getString("TenMau"));
                ct.setTenSize(rs.getString("TenSize"));

                list.add(ct);
            }

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public void insert(SanPhamChiTiet ct) {

        String sql = "INSERT INTO SANPHAMCHITIET(MaSP,MaMau,MaSize,SoLuongTon,GiaNhap) VALUES(?,?,?,?,?)";

        try {
            Connection con = new ConnectService().myConnection();
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, ct.getMaSP());
            ps.setString(2, ct.getMaMau());
            ps.setString(3, ct.getMaSize());
            ps.setInt(4, ct.getSoLuongTon());
            ps.setBigDecimal(5, ct.getGiaNhap());

            ps.executeUpdate();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public SanPhamChiTiet getById(int id) {

        SanPhamChiTiet ct = null;

        String sql = "SELECT * FROM SANPHAMCHITIET WHERE MaSPCT=?";

        try {
            Connection con = new ConnectService().myConnection();
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                ct = new SanPhamChiTiet();

                ct.setMaSPCT(rs.getInt("MaSPCT"));
                ct.setMaSP(rs.getInt("MaSP"));
                ct.setMaMau(rs.getString("MaMau"));
                ct.setMaSize(rs.getString("MaSize"));
                ct.setSoLuongTon(rs.getInt("SoLuongTon"));
                ct.setGiaNhap(rs.getBigDecimal("GiaNhap"));
            }

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return ct;
    }

    public boolean update(SanPhamChiTiet ct) {

        String sql =
                "UPDATE SANPHAMCHITIET " +
                        "SET MaSP=?, MaMau=?, MaSize=?, SoLuongTon=?, GiaNhap=? " +
                        "WHERE MaSPCT=?";

        try (
                Connection con =
                        new ConnectService().myConnection();

                PreparedStatement ps =
                        con.prepareStatement(sql)
        ) {

            ps.setInt(1, ct.getMaSP());
            ps.setString(2, ct.getMaMau());
            ps.setString(3, ct.getMaSize());
            ps.setInt(4, ct.getSoLuongTon());
            ps.setBigDecimal(5, ct.getGiaNhap());
            ps.setInt(6, ct.getMaSPCT());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public void delete(int id) {

        String sql = "DELETE FROM SANPHAMCHITIET WHERE MaSPCT=?";

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
    public List<SanPhamChiTiet> getByMaSP(int maSP) {

        List<SanPhamChiTiet> list = new ArrayList<>();

        String sql =
                "SELECT ct.*, sp.TenSP, ms.TenMau, s.TenSize " +
                        "FROM SANPHAMCHITIET ct " +
                        "JOIN SANPHAM sp ON ct.MaSP = sp.MaSP " +
                        "JOIN MauSac ms ON ct.MaMau = ms.MaMau " +
                        "JOIN Size s ON ct.MaSize = s.MaSize " +
                        "WHERE ct.MaSP=?";

        try {
            Connection con = new ConnectService().myConnection();

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, maSP);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                SanPhamChiTiet ct = new SanPhamChiTiet();

                ct.setMaSPCT(rs.getInt("MaSPCT"));
                ct.setMaSP(rs.getInt("MaSP"));
                ct.setMaMau(rs.getString("MaMau"));
                ct.setMaSize(rs.getString("MaSize"));
                ct.setSoLuongTon(rs.getInt("SoLuongTon"));
                ct.setGiaNhap(rs.getBigDecimal("GiaNhap"));

                ct.setTenSP(rs.getString("TenSP"));
                ct.setTenMau(rs.getString("TenMau"));
                ct.setTenSize(rs.getString("TenSize"));

                list.add(ct);
            }

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
    public SanPhamChiTiet getBySanPhamMauSize(
            int maSP,
            String maMau,
            String maSize
    ) {
        String sql =
                "SELECT ct.MaSPCT, " +
                        "ct.MaSP, " +
                        "ct.MaMau, " +
                        "ct.MaSize, " +
                        "ct.SoLuongTon, " +
                        "ct.GiaNhap, " +
                        "sp.TenSP, " +
                        "ms.TenMau, " +
                        "s.TenSize " +
                        "FROM SANPHAMCHITIET ct " +
                        "JOIN SANPHAM sp ON ct.MaSP = sp.MaSP " +
                        "JOIN MauSac ms ON ct.MaMau = ms.MaMau " +
                        "JOIN Size s ON ct.MaSize = s.MaSize " +
                        "WHERE ct.MaSP = ? " +
                        "AND ct.MaMau = ? " +
                        "AND ct.MaSize = ?";

        try (
                Connection connection =
                        new ConnectService().myConnection();

                PreparedStatement ps =
                        connection.prepareStatement(sql)
        ) {
            ps.setInt(1, maSP);
            ps.setString(2, maMau);
            ps.setString(3, maSize);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                SanPhamChiTiet sp = new SanPhamChiTiet();

                sp.setMaSPCT(rs.getInt("MaSPCT"));
                sp.setMaSP(rs.getInt("MaSP"));
                sp.setMaMau(rs.getString("MaMau"));
                sp.setMaSize(rs.getString("MaSize"));
                sp.setSoLuongTon(rs.getInt("SoLuongTon"));
                sp.setGiaNhap(rs.getBigDecimal("GiaNhap"));
                sp.setTenSP(rs.getString("TenSP"));
                sp.setTenMau(rs.getString("TenMau"));
                sp.setTenSize(rs.getString("TenSize"));

                return sp;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}