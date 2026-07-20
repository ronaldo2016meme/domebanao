package dao;

import model.DoanhThuNgay;
import model.TopSanPham;
import service.ConnectService;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ThongKeDao {

    // Tổng doanh thu
    public double getTongDoanhThu(Date tuNgay, Date denNgay) {

        double tong = 0;

        String sql = "SELECT ISNULL(SUM(TongTien),0) FROM HOADON WHERE NgayLap BETWEEN ? AND ? AND MaTrangThaiHD='TTHD01' ";

        try (Connection con = new ConnectService().myConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setDate(1, tuNgay);
            ps.setDate(2, denNgay);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                tong = rs.getDouble(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return tong;
    }

    // Tổng hóa đơn
    public int getTongHoaDon(Date tuNgay, Date denNgay) {

        int tong = 0;

        String sql = "SELECT COUNT(*)FROM HOADON WHERE NgayLap BETWEEN ? AND ?AND MaTrangThaiHD='TTHD01' ";

        try (Connection con = new ConnectService().myConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setDate(1, tuNgay);
            ps.setDate(2, denNgay);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                tong = rs.getInt(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return tong;
    }

    // Tổng sản phẩm bán
    public int getTongSanPham(Date tuNgay, Date denNgay) {

        int tong = 0;

        String sql = " SELECT ISNULL(SUM(ct.SoLuong),0) FROM CHITIETHOADON ct JOIN HOADON hd ON ct.MaHD = hd.MaHD WHERE hd.NgayLap BETWEEN ? AND ? AND hd.MaTrangThaiHD='TTHD01' ";

        try (Connection con = new ConnectService().myConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setDate(1, tuNgay);
            ps.setDate(2, denNgay);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                tong = rs.getInt(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return tong;
    }

    // Doanh thu theo ngày
    public List<DoanhThuNgay> getDoanhThuTheoNgay(Date tuNgay, Date denNgay) {

        List<DoanhThuNgay> list = new ArrayList<>();

        String sql = " SELECT NgayLap, SUM(TongTien) AS DoanhThu FROM HOADON WHERE NgayLap BETWEEN ? AND ? AND MaTrangThaiHD='TTHD01' GROUP BY NgayLap ORDER BY NgayLap ";

        try (Connection con = new ConnectService().myConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setDate(1, tuNgay);
            ps.setDate(2, denNgay);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                DoanhThuNgay dt = new DoanhThuNgay();

                dt.setNgayLap(rs.getDate("NgayLap"));
                dt.setDoanhThu(rs.getDouble("DoanhThu"));

                list.add(dt);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    // Top 5 sản phẩm bán chạy
    public List<TopSanPham> getTop5SanPham(Date tuNgay, Date denNgay) {

        List<TopSanPham> list = new ArrayList<>();

        String sql = " SELECT TOP 5 sp.MaSP, sp.TenSP, SUM(ct.SoLuong) AS SoLuongBan, SUM(ct.ThanhTien) AS DoanhThu FROM CHITIETHOADON ct JOIN SANPHAMCHITIET spct ON ct.MaSPCT = spct.MaSPCT JOIN SANPHAM sp ON spct.MaSP = sp.MaSP JOIN HOADON hd ON ct.MaHD = hd.MaHD WHERE hd.NgayLap BETWEEN ? AND ? AND hd.MaTrangThaiHD='TTHD01' GROUP BY sp.MaSP, sp.TenSP ORDER BY SoLuongBan DESC ";

        try (Connection con = new ConnectService().myConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setDate(1, tuNgay);
            ps.setDate(2, denNgay);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                TopSanPham sp = new TopSanPham();

                sp.setMaSP(rs.getInt("MaSP"));
                sp.setTenSP(rs.getString("TenSP"));
                sp.setSoLuongBan(rs.getInt("SoLuongBan"));
                sp.setDoanhThu(rs.getDouble("DoanhThu"));

                list.add(sp);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

}