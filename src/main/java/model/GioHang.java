package model;

public class GioHang {

    private static final long serialVersionUID = 1L;

    private int maSPCT;
    private String tenSP;
    private String tenMau;
    private String tenSize;
    private double donGia;
    private int soLuong;

    public GioHang() {
    }

    public GioHang(int maSPCT, String tenSP, String tenMau, String tenSize, double donGia, int soLuong) {
        this.maSPCT = maSPCT;
        this.tenSP = tenSP;
        this.tenMau = tenMau;
        this.tenSize = tenSize;
        this.donGia = donGia;
        this.soLuong = soLuong;
    }

    public int getMaSPCT() {
        return maSPCT;
    }

    public void setMaSPCT(int maSPCT) {
        this.maSPCT = maSPCT;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public String getTenMau() {
        return tenMau;
    }

    public void setTenMau(String tenMau) {
        this.tenMau = tenMau;
    }

    public String getTenSize() {
        return tenSize;
    }

    public void setTenSize(String tenSize) {
        this.tenSize = tenSize;
    }

    public double getDonGia() {
        return donGia;
    }

    public void setDonGia(double donGia) {
        this.donGia = donGia;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public double getThanhTien() {
        return donGia * soLuong;
    }
}
