package model;

import java.sql.Date;

public class HoaDon {
    private int maHD;
    private Date ngayLap;
    private double tongTien;
    private double tienKhachDua;
    private double tienThua;
    private String phuongThucThanhToan;
    private int maNV;
    private Integer maKH;
    private String maTrangThaiHD;

    public HoaDon() {
    }

    public HoaDon(int maHD, Date ngayLap, double tongTien, double tienKhachDua, double tienThua, String phuongThucThanhToan, int maNV, Integer maKH, String maTrangThaiHD) {
        this.maHD = maHD;
        this.ngayLap = ngayLap;
        this.tongTien = tongTien;
        this.tienKhachDua = tienKhachDua;
        this.tienThua = tienThua;
        this.phuongThucThanhToan = phuongThucThanhToan;
        this.maNV = maNV;
        this.maKH = maKH;
        this.maTrangThaiHD = maTrangThaiHD;
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
}
