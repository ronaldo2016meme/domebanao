package model;

import java.sql.Date;

public class HoaDon {

    private int maHD;
    private Date ngayLap;

    // Mã dùng khi lưu hóa đơn
    private int maNV;
    private Integer maKH;
    private String maTrangThaiHD;

    // Tên dùng khi hiển thị danh sách hóa đơn
    private String tenKH;
    private String tenNV;
    private String tenTrangThai;

    private double tongTien;
    private double tienKhachDua;
    private double tienThua;
    private String phuongThucThanhToan;
    private double tienGiam;

    public HoaDon() {
    }

    public HoaDon(int maHD, Date ngayLap, int maNV, Integer maKH, String maTrangThaiHD, String tenKH, String tenNV, String tenTrangThai, double tongTien, double tienKhachDua, double tienThua, String phuongThucThanhToan, double tienGiam) {
        this.maHD = maHD;
        this.ngayLap = ngayLap;
        this.maNV = maNV;
        this.maKH = maKH;
        this.maTrangThaiHD = maTrangThaiHD;
        this.tenKH = tenKH;
        this.tenNV = tenNV;
        this.tenTrangThai = tenTrangThai;
        this.tongTien = tongTien;
        this.tienKhachDua = tienKhachDua;
        this.tienThua = tienThua;
        this.phuongThucThanhToan = phuongThucThanhToan;
        this.tienGiam = tienGiam;
    }

    public int getMaHD() {
        return maHD;
    }

    public void setMaHD(int maHD) {
        this.maHD = maHD;
    }

    public Date getNgayLap() {
        return ngayLap;
    }

    public void setNgayLap(Date ngayLap) {
        this.ngayLap = ngayLap;
    }

    public int getMaNV() {
        return maNV;
    }

    public void setMaNV(int maNV) {
        this.maNV = maNV;
    }

    public Integer getMaKH() {
        return maKH;
    }

    public void setMaKH(Integer maKH) {
        this.maKH = maKH;
    }

    public String getMaTrangThaiHD() {
        return maTrangThaiHD;
    }

    public void setMaTrangThaiHD(String maTrangThaiHD) {
        this.maTrangThaiHD = maTrangThaiHD;
    }

    public String getTenKH() {
        return tenKH;
    }

    public void setTenKH(String tenKH) {
        this.tenKH = tenKH;
    }

    public String getTenNV() {
        return tenNV;
    }

    public void setTenNV(String tenNV) {
        this.tenNV = tenNV;
    }

    public String getTenTrangThai() {
        return tenTrangThai;
    }

    public void setTenTrangThai(String tenTrangThai) {
        this.tenTrangThai = tenTrangThai;
    }

    public double getTongTien() {
        return tongTien;
    }

    public void setTongTien(double tongTien) {
        this.tongTien = tongTien;
    }

    public double getTienKhachDua() {
        return tienKhachDua;
    }

    public void setTienKhachDua(double tienKhachDua) {
        this.tienKhachDua = tienKhachDua;
    }

    public double getTienThua() {
        return tienThua;
    }

    public void setTienThua(double tienThua) {
        this.tienThua = tienThua;
    }

    public String getPhuongThucThanhToan() {
        return phuongThucThanhToan;
    }

    public void setPhuongThucThanhToan(String phuongThucThanhToan) {
        this.phuongThucThanhToan = phuongThucThanhToan;
    }

    public double getTienGiam() {
        return tienGiam;
    }

    public void setTienGiam(double tienGiam) {
        this.tienGiam = tienGiam;
    }
}