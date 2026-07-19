package model;

public class ChiTietHoaDon {
    private int maCTHD;
    private int maHD;
    private int maSPCT;

    private int soLuong;
    private double donGia;
    private double thanhTien;

    public ChiTietHoaDon() {
    }

    public ChiTietHoaDon(int maCTHD, int maHD, int maSPCT, int soLuong, double donGia, double thanhTien) {
        this.maCTHD = maCTHD;
        this.maHD = maHD;
        this.maSPCT = maSPCT;
        this.soLuong = soLuong;
        this.donGia = donGia;
        this.thanhTien = thanhTien;
    }

    public int getMaCTHD() {
        return maCTHD;
    }

    public void setMaCTHD(int maCTHD) {
        this.maCTHD = maCTHD;
    }

    public int getMaHD() {
        return maHD;
    }

    public void setMaHD(int maHD) {
        this.maHD = maHD;
    }

    public int getMaSPCT() {
        return maSPCT;
    }

    public void setMaSPCT(int maSPCT) {
        this.maSPCT = maSPCT;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public double getDonGia() {
        return donGia;
    }

    public void setDonGia(double donGia) {
        this.donGia = donGia;
    }

    public double getThanhTien() {
        return thanhTien;
    }

    public void setThanhTien(double thanhTien) {
        this.thanhTien = thanhTien;
    }
}
