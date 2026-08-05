package model;

import java.math.BigDecimal;

public class SanPhamChiTiet {

    private int maSPCT;
    private int maSP;
    private String maMau;
    private String maSize;
    private int soLuongTon;

    private BigDecimal giaNhap;
    private BigDecimal giaBan;

    private String tenSP;
    private String tenMau;
    private String tenSize;

    public SanPhamChiTiet() {
    }

    public SanPhamChiTiet(
            int maSPCT,
            int maSP,
            String maMau,
            String maSize,
            int soLuongTon,
            BigDecimal giaNhap,
            BigDecimal giaBan,
            String tenSP,
            String tenMau,
            String tenSize) {

        this.maSPCT = maSPCT;
        this.maSP = maSP;
        this.maMau = maMau;
        this.maSize = maSize;
        this.soLuongTon = soLuongTon;
        this.giaNhap = giaNhap;
        this.giaBan = giaBan;
        this.tenSP = tenSP;
        this.tenMau = tenMau;
        this.tenSize = tenSize;
    }

    public int getMaSPCT() {
        return maSPCT;
    }

    public void setMaSPCT(int maSPCT) {
        this.maSPCT = maSPCT;
    }

    public int getMaSP() {
        return maSP;
    }

    public void setMaSP(int maSP) {
        this.maSP = maSP;
    }

    public String getMaMau() {
        return maMau;
    }

    public void setMaMau(String maMau) {
        this.maMau = maMau;
    }

    public String getMaSize() {
        return maSize;
    }

    public void setMaSize(String maSize) {
        this.maSize = maSize;
    }

    public int getSoLuongTon() {
        return soLuongTon;
    }

    public void setSoLuongTon(int soLuongTon) {
        this.soLuongTon = soLuongTon;
    }

    public BigDecimal getGiaNhap() {
        return giaNhap;
    }

    public void setGiaNhap(BigDecimal giaNhap) {
        this.giaNhap = giaNhap;
    }

    public BigDecimal getGiaBan() {
        return giaBan;
    }

    public void setGiaBan(BigDecimal giaBan) {
        this.giaBan = giaBan;
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
}