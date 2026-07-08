package model;

public class sanpham {
    private int maSP;
    private String tenSP;
    private String danhMuc;
    private String nhaCungCap;
    private double giaBan;
    private String moTa;
    private String ngayTao;
    private String ngayCapNhat;
    private String anh;

    public sanpham() {
    }

    public sanpham(int maSP, String tenSP, String danhMuc, String nhaCungCap, double giaBan, String moTa, String ngayTao, String ngayCapNhat, String anh) {
        this.maSP = maSP;
        this.tenSP = tenSP;
        this.danhMuc = danhMuc;
        this.nhaCungCap = nhaCungCap;
        this.giaBan = giaBan;
        this.moTa = moTa;
        this.ngayTao = ngayTao;
        this.ngayCapNhat = ngayCapNhat;
        this.anh = anh;
    }

    public int getMaSP() {
        return maSP;
    }

    public void setMaSP(int maSP) {
        this.maSP = maSP;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public String getDanhMuc() {
        return danhMuc;
    }

    public void setDanhMuc(String danhMuc) {
        this.danhMuc = danhMuc;
    }

    public String getNhaCungCap() {
        return nhaCungCap;
    }

    public void setNhaCungCap(String nhaCungCap) {
        this.nhaCungCap = nhaCungCap;
    }

    public double getGiaBan() {
        return giaBan;
    }

    public void setGiaBan(double giaBan) {
        this.giaBan = giaBan;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public String getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(String ngayTao) {
        this.ngayTao = ngayTao;
    }

    public String getNgayCapNhat() {
        return ngayCapNhat;
    }

    public void setNgayCapNhat(String ngayCapNhat) {
        this.ngayCapNhat = ngayCapNhat;
    }

    public String getAnh() {
        return anh;
    }

    public void setAnh(String anh) {
        this.anh = anh;
    }
}