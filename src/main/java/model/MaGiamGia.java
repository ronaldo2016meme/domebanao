package model;

import java.sql.Date;

public class MaGiamGia {
    private int maMGG;
    private String maCode;
    private String tenMGG;
    private int phanTramGiam;
    private int diemCan;
    private Date ngayBatDau;
    private Date ngayKetThuc;
    private int soLuong;
    private boolean trangThai;

    public MaGiamGia() {
    }

    public MaGiamGia(int maMGG, String maCode, String tenMGG, int phanTramGiam, int diemCan, Date ngayBatDau, Date ngayKetThuc, int soLuong, boolean trangThai) {
        this.maMGG = maMGG;
        this.maCode = maCode;
        this.tenMGG = tenMGG;
        this.phanTramGiam = phanTramGiam;
        this.diemCan = diemCan;
        this.ngayBatDau = ngayBatDau;
        this.ngayKetThuc = ngayKetThuc;
        this.soLuong = soLuong;
        this.trangThai = trangThai;
    }

    public int getMaMGG() {
        return maMGG;
    }

    public void setMaMGG(int maMGG) {
        this.maMGG = maMGG;
    }

    public String getMaCode() {
        return maCode;
    }

    public void setMaCode(String maCode) {
        this.maCode = maCode;
    }

    public String getTenMGG() {
        return tenMGG;
    }

    public void setTenMGG(String tenMGG) {
        this.tenMGG = tenMGG;
    }

    public int getPhanTramGiam() {
        return phanTramGiam;
    }

    public void setPhanTramGiam(int phanTramGiam) {
        this.phanTramGiam = phanTramGiam;
    }

    public int getDiemCan() {
        return diemCan;
    }

    public void setDiemCan(int diemCan) {
        this.diemCan = diemCan;
    }

    public Date getNgayBatDau() {
        return ngayBatDau;
    }

    public void setNgayBatDau(Date ngayBatDau) {
        this.ngayBatDau = ngayBatDau;
    }

    public Date getNgayKetThuc() {
        return ngayKetThuc;
    }

    public void setNgayKetThuc(Date ngayKetThuc) {
        this.ngayKetThuc = ngayKetThuc;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public boolean isTrangThai() {
        return trangThai;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }
}
