package dao;

import model.MaGiamGia;
import service.ConnectService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MaGiamGiaDao {

    // =====================================================
    // LẤY TẤT CẢ MÃ GIẢM GIÁ
    // =====================================================
    public List<MaGiamGia> getAll() {

        List<MaGiamGia> list = new ArrayList<>();

        String sql = "SELECT * FROM MAGIAMGIA ORDER BY MaMGG ASC";

        try (Connection con = new ConnectService().myConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                MaGiamGia m = new MaGiamGia();

                m.setMaMGG(rs.getInt("MaMGG"));
                m.setMaCode(rs.getString("MaCode"));
                m.setTenMGG(rs.getString("TenMGG"));

                // ĐÚNG TÊN CỘT TRONG DATABASE
                m.setPhanTramGiam(
                        rs.getInt("PhanTramGiam")
                );

                m.setDiemCan(
                        rs.getInt("DiemDoi")
                );

                m.setNgayBatDau(
                        rs.getDate("NgayBatDau")
                );

                m.setNgayKetThuc(
                        rs.getDate("NgayKetThuc")
                );

                m.setSoLuong(
                        rs.getInt("SoLuong")
                );

                // BIT -> boolean
                m.setTrangThai(
                        rs.getBoolean("TrangThai")
                );

                list.add(m);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }


    // =====================================================
    // LẤY MÃ GIẢM GIÁ THEO ID
    // =====================================================
    public MaGiamGia getById(int maMGG) {

        String sql =
                "SELECT * FROM MAGIAMGIA WHERE MaMGG = ?";

        try (Connection con = new ConnectService().myConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, maMGG);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {

                    MaGiamGia m = new MaGiamGia();

                    m.setMaMGG(
                            rs.getInt("MaMGG")
                    );

                    m.setMaCode(
                            rs.getString("MaCode")
                    );

                    m.setTenMGG(
                            rs.getString("TenMGG")
                    );

                    m.setPhanTramGiam(
                            rs.getInt("PhanTramGiam")
                    );

                    m.setDiemCan(
                            rs.getInt("DiemDoi")
                    );

                    m.setNgayBatDau(
                            rs.getDate("NgayBatDau")
                    );

                    m.setNgayKetThuc(
                            rs.getDate("NgayKetThuc")
                    );

                    m.setSoLuong(
                            rs.getInt("SoLuong")
                    );

                    m.setTrangThai(
                            rs.getBoolean("TrangThai")
                    );

                    return m;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    // =====================================================
// LẤY MÃ GIẢM GIÁ ĐANG HOẠT ĐỘNG
// =====================================================
    public List<MaGiamGia> getAllDangHoatDong() {

        List<MaGiamGia> list = new ArrayList<>();

        String sql =
                "SELECT * FROM MAGIAMGIA " +
                        "WHERE TrangThai = 1 " +
                        "AND SoLuong > 0 " +
                        "AND CAST(GETDATE() AS DATE) >= NgayBatDau " +
                        "AND CAST(GETDATE() AS DATE) <= NgayKetThuc " +
                        "ORDER BY MaMGG DESC";

        try (Connection con = new ConnectService().myConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                MaGiamGia m = new MaGiamGia();

                m.setMaMGG(
                        rs.getInt("MaMGG")
                );

                m.setMaCode(
                        rs.getString("MaCode")
                );

                m.setTenMGG(
                        rs.getString("TenMGG")
                );

                m.setPhanTramGiam(
                        rs.getInt("PhanTramGiam")
                );

                m.setDiemCan(
                        rs.getInt("DiemDoi")
                );

                m.setNgayBatDau(
                        rs.getDate("NgayBatDau")
                );

                m.setNgayKetThuc(
                        rs.getDate("NgayKetThuc")
                );

                m.setSoLuong(
                        rs.getInt("SoLuong")
                );

                m.setTrangThai(
                        rs.getBoolean("TrangThai")
                );

                list.add(m);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }


    // =====================================================
    // THÊM MÃ GIẢM GIÁ
    // =====================================================
    public boolean insert(MaGiamGia m) {

        String sql =
                "INSERT INTO MAGIAMGIA " +
                        "(MaCode, TenMGG, PhanTramGiam, DiemDoi, " +
                        "NgayBatDau, NgayKetThuc, SoLuong, TrangThai) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = new ConnectService().myConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(
                    1,
                    m.getMaCode()
            );

            ps.setString(
                    2,
                    m.getTenMGG()
            );

            ps.setInt(
                    3,
                    m.getPhanTramGiam()
            );

            ps.setInt(
                    4,
                    m.getDiemCan()
            );

            ps.setDate(
                    5,
                    m.getNgayBatDau()
            );

            ps.setDate(
                    6,
                    m.getNgayKetThuc()
            );

            ps.setInt(
                    7,
                    m.getSoLuong()
            );

            // boolean -> BIT
            ps.setBoolean(
                    8,
                    m.isTrangThai()
            );

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }


    // =====================================================
    // SỬA MÃ GIẢM GIÁ
    // =====================================================
    public boolean update(MaGiamGia m) {

        String sql =
                "UPDATE MAGIAMGIA SET " +
                        "MaCode = ?, " +
                        "TenMGG = ?, " +
                        "PhanTramGiam = ?, " +
                        "DiemDoi = ?, " +
                        "NgayBatDau = ?, " +
                        "NgayKetThuc = ?, " +
                        "SoLuong = ?, " +
                        "TrangThai = ? " +
                        "WHERE MaMGG = ?";

        try (Connection con = new ConnectService().myConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(
                    1,
                    m.getMaCode()
            );

            ps.setString(
                    2,
                    m.getTenMGG()
            );

            ps.setInt(
                    3,
                    m.getPhanTramGiam()
            );

            ps.setInt(
                    4,
                    m.getDiemCan()
            );

            ps.setDate(
                    5,
                    m.getNgayBatDau()
            );

            ps.setDate(
                    6,
                    m.getNgayKetThuc()
            );

            ps.setInt(
                    7,
                    m.getSoLuong()
            );

            // boolean -> BIT
            ps.setBoolean(
                    8,
                    m.isTrangThai()
            );

            ps.setInt(
                    9,
                    m.getMaMGG()
            );

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
    public void giamSoLuong(int maMGG){

        String sql =
                "UPDATE MAGIAMGIA " +
                        "SET SoLuong = SoLuong - 1 " +
                        "WHERE MaMGG = ?";

        try(Connection con = new ConnectService().myConnection();
            PreparedStatement ps = con.prepareStatement(sql)){

            ps.setInt(1, maMGG);

            ps.executeUpdate();

        }catch(Exception e){
            e.printStackTrace();
        }

    }
    public MaGiamGia getByCodeDangHoatDong(String maCode) {

        String sql =
                "SELECT * FROM MAGIAMGIA " +
                        "WHERE MaCode = ? " +
                        "AND TrangThai = 1 " +
                        "AND SoLuong > 0 " +
                        "AND CAST(GETDATE() AS DATE) >= NgayBatDau " +
                        "AND CAST(GETDATE() AS DATE) <= NgayKetThuc";

        try (Connection con =
                     new ConnectService().myConnection();

             PreparedStatement ps =
                     con.prepareStatement(sql)) {

            ps.setString(1, maCode);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {

                    MaGiamGia m =
                            new MaGiamGia();

                    m.setMaMGG(
                            rs.getInt("MaMGG")
                    );

                    m.setMaCode(
                            rs.getString("MaCode")
                    );

                    m.setTenMGG(
                            rs.getString("TenMGG")
                    );

                    m.setPhanTramGiam(
                            rs.getInt("PhanTramGiam")
                    );

                    m.setDiemCan(
                            rs.getInt("DiemDoi")
                    );

                    m.setNgayBatDau(
                            rs.getDate("NgayBatDau")
                    );

                    m.setNgayKetThuc(
                            rs.getDate("NgayKetThuc")
                    );

                    m.setSoLuong(
                            rs.getInt("SoLuong")
                    );

                    m.setTrangThai(
                            rs.getBoolean("TrangThai")
                    );

                    return m;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public void capNhatTrangThai(int maMGG){

        String sql =
                "UPDATE MAGIAMGIA " +
                        "SET TrangThai = 0 " +
                        "WHERE MaMGG = ? " +
                        "AND SoLuong <= 0";

        try(Connection con = new ConnectService().myConnection();
            PreparedStatement ps = con.prepareStatement(sql)){

            ps.setInt(1, maMGG);

            ps.executeUpdate();

        }catch(Exception e){
            e.printStackTrace();
        }

    }
}