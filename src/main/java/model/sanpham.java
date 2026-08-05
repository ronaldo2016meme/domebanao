package model;

public class sanpham {
    private int maSP;
    private String maDanhMuc;
    private String maNCC;
    private String tenSP;
    private String maTrangThaiSP;
    private String tenDanhMuc;
    private String tenNCC;
    private String tenTrangThai;
    private double giaBan;
    private String moTa;
    private String ngayTao;
    private String ngayCapNhat;
    private String anh;
    private int soLuongTon;

    public sanpham() {
    }

    public sanpham(int maSP, String maDanhMuc, String maNCC, String tenSP, String maTrangThaiSP, String tenDanhMuc, String tenNCC, String tenTrangThai, double giaBan, String moTa, String ngayTao, String ngayCapNhat, String anh, int soLuongTon) {
        this.maSP = maSP;
        this.maDanhMuc = maDanhMuc;
        this.maNCC = maNCC;
        this.tenSP = tenSP;
        this.maTrangThaiSP = maTrangThaiSP;
        this.tenDanhMuc = tenDanhMuc;
        this.tenNCC = tenNCC;
        this.tenTrangThai = tenTrangThai;
        this.giaBan = giaBan;
        this.moTa = moTa;
        this.ngayTao = ngayTao;
        this.ngayCapNhat = ngayCapNhat;
        this.anh = anh;
        this.soLuongTon = soLuongTon;
    }

    public int getMaSP() {
        return maSP;
    }

    public void setMaSP(int maSP) {
        this.maSP = maSP;
    }

    public String getMaDanhMuc() {
        return maDanhMuc;
    }

    public void setMaDanhMuc(String maDanhMuc) {
        this.maDanhMuc = maDanhMuc;
    }

    public String getMaNCC() {
        return maNCC;
    }

    public void setMaNCC(String maNCC) {
        this.maNCC = maNCC;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public String getMaTrangThaiSP() {
        return maTrangThaiSP;
    }

    public void setMaTrangThaiSP(String maTrangThaiSP) {
        this.maTrangThaiSP = maTrangThaiSP;
    }

    public String getTenDanhMuc() {
        return tenDanhMuc;
    }

    public void setTenDanhMuc(String tenDanhMuc) {
        this.tenDanhMuc = tenDanhMuc;
    }

    public String getTenNCC() {
        return tenNCC;
    }

    public void setTenNCC(String tenNCC) {
        this.tenNCC = tenNCC;
    }

    public String getTenTrangThai() {
        return tenTrangThai;
    }

    public void setTenTrangThai(String tenTrangThai) {
        this.tenTrangThai = tenTrangThai;
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

    public int getSoLuongTon() {
        return soLuongTon;
    }

    public void setSoLuongTon(int soLuongTon) {
        this.soLuongTon = soLuongTon;
    }
}