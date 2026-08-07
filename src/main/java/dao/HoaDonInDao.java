package dao;

import model.HoaDonIn;
import service.ConnectService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class HoaDonInDao {

    public List<HoaDonIn> getHoaDon(int maHD) {

        List<HoaDonIn> list = new ArrayList<>();

        String sql =
                "SELECT " +
                        "hd.MaHD, " +
                        "hd.NgayLap, " +
                        "ISNULL(kh.HoTen, N'Khách lẻ') AS TenKH, " +
                        "nv.HoTen AS TenNV, " +

                        "sp.TenSP, " +
                        "ms.TenMau, " +
                        "sz.TenSize, " +

                        "ct.SoLuong, " +
                        "ct.DonGia, " +
                        "ct.ThanhTien, " +

                        "hd.TongTien, " +
                        "hd.TienGiam, " +
                        "hd.TienKhachDua, " +
                        "hd.TienThua " +

                        "FROM HOADON hd " +

                        "LEFT JOIN KHACHHANG kh " +
                        "ON hd.MaKH = kh.MaKH " +

                        "JOIN NHANVIEN nv " +
                        "ON hd.MaNV = nv.MaNV " +

                        "JOIN CHITIETHOADON ct " +
                        "ON hd.MaHD = ct.MaHD " +

                        "JOIN SANPHAMCHITIET spct " +
                        "ON ct.MaSPCT = spct.MaSPCT " +

                        "JOIN SANPHAM sp " +
                        "ON spct.MaSP = sp.MaSP " +

                        "JOIN MauSac ms " +
                        "ON spct.MaMau = ms.MaMau " +

                        "JOIN Size sz " +
                        "ON spct.MaSize = sz.MaSize " +

                        "WHERE hd.MaHD = ?";

        try (
                Connection con =
                        new ConnectService().myConnection();

                PreparedStatement ps =
                        con.prepareStatement(sql)
        ) {

            ps.setInt(1, maHD);

            try (ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {

                    HoaDonIn hd =
                            new HoaDonIn();

                    hd.setMaHD(
                            rs.getInt("MaHD")
                    );

                    hd.setNgayLap(
                            rs.getDate("NgayLap")
                    );

                    hd.setTenKH(
                            rs.getString("TenKH")
                    );

                    hd.setTenNV(
                            rs.getString("TenNV")
                    );

                    hd.setTenSP(
                            rs.getString("TenSP")
                    );

                    hd.setTenMau(
                            rs.getString("TenMau")
                    );

                    hd.setTenSize(
                            rs.getString("TenSize")
                    );

                    hd.setSoLuong(
                            rs.getInt("SoLuong")
                    );

                    hd.setDonGia(
                            rs.getDouble("DonGia")
                    );

                    hd.setThanhTien(
                            rs.getDouble("ThanhTien")
                    );

                    hd.setTongTien(
                            rs.getDouble("TongTien")
                    );

                    hd.setTienGiam(
                            rs.getDouble("TienGiam")
                    );

                    hd.setTienKhachDua(
                            rs.getDouble("TienKhachDua")
                    );

                    hd.setTienThua(
                            rs.getDouble("TienThua")
                    );

                    list.add(hd);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}