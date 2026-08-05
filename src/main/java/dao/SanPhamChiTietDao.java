package dao;

import model.SanPhamChiTiet;
import service.ConnectService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

        try (
                Connection con =
                        new ConnectService().myConnection();

                PreparedStatement ps =
                        con.prepareStatement(sql);

                ResultSet rs =
                        ps.executeQuery()
        ) {

            while (rs.next()) {

                list.add(mapSanPhamChiTiet(rs));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public boolean insert(SanPhamChiTiet ct) {

        String sql =
                "INSERT INTO SANPHAMCHITIET " +
                        "(MaSP, MaMau, MaSize, SoLuongTon, GiaNhap, GiaBan) " +
                        "VALUES (?, ?, ?, ?, ?, ?)";

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
            ps.setBigDecimal(6, ct.getGiaBan());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public SanPhamChiTiet getById(int id) {

        String sql =
                "SELECT ct.*, sp.TenSP, ms.TenMau, s.TenSize " +
                        "FROM SANPHAMCHITIET ct " +
                        "JOIN SANPHAM sp ON ct.MaSP = sp.MaSP " +
                        "JOIN MauSac ms ON ct.MaMau = ms.MaMau " +
                        "JOIN Size s ON ct.MaSize = s.MaSize " +
                        "WHERE ct.MaSPCT = ?";

        try (
                Connection con =
                        new ConnectService().myConnection();

                PreparedStatement ps =
                        con.prepareStatement(sql)
        ) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {
                    return mapSanPhamChiTiet(rs);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public boolean update(SanPhamChiTiet ct) {

        String sql =
                "UPDATE SANPHAMCHITIET " +
                        "SET MaSP = ?, " +
                        "MaMau = ?, " +
                        "MaSize = ?, " +
                        "SoLuongTon = ?, " +
                        "GiaNhap = ?, " +
                        "GiaBan = ? " +
                        "WHERE MaSPCT = ?";

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
            ps.setBigDecimal(6, ct.getGiaBan());
            ps.setInt(7, ct.getMaSPCT());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
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
                        "WHERE ct.MaSP = ? " +
                        "ORDER BY ms.TenMau, s.TenSize";

        try (
                Connection con =
                        new ConnectService().myConnection();

                PreparedStatement ps =
                        con.prepareStatement(sql)
        ) {

            ps.setInt(1, maSP);

            try (ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {
                    list.add(mapSanPhamChiTiet(rs));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public SanPhamChiTiet getBySanPhamMauSize(
            int maSP,
            String maMau,
            String maSize) {

        String sql =
                "SELECT ct.*, sp.TenSP, ms.TenMau, s.TenSize " +
                        "FROM SANPHAMCHITIET ct " +
                        "JOIN SANPHAM sp ON ct.MaSP = sp.MaSP " +
                        "JOIN MauSac ms ON ct.MaMau = ms.MaMau " +
                        "JOIN Size s ON ct.MaSize = s.MaSize " +
                        "WHERE ct.MaSP = ? " +
                        "AND ct.MaMau = ? " +
                        "AND ct.MaSize = ?";

        try (
                Connection con =
                        new ConnectService().myConnection();

                PreparedStatement ps =
                        con.prepareStatement(sql)
        ) {

            ps.setInt(1, maSP);
            ps.setString(2, maMau);
            ps.setString(3, maSize);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {
                    return mapSanPhamChiTiet(rs);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    private SanPhamChiTiet mapSanPhamChiTiet(
            ResultSet rs) throws Exception {

        SanPhamChiTiet ct = new SanPhamChiTiet();

        ct.setMaSPCT(rs.getInt("MaSPCT"));
        ct.setMaSP(rs.getInt("MaSP"));
        ct.setMaMau(rs.getString("MaMau"));
        ct.setMaSize(rs.getString("MaSize"));
        ct.setSoLuongTon(rs.getInt("SoLuongTon"));
        ct.setGiaNhap(rs.getBigDecimal("GiaNhap"));
        ct.setGiaBan(rs.getBigDecimal("GiaBan"));

        ct.setTenSP(rs.getString("TenSP"));
        ct.setTenMau(rs.getString("TenMau"));
        ct.setTenSize(rs.getString("TenSize"));

        return ct;
    }
}