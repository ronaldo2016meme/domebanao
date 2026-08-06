package model;

public class TaiKhoan {

    private int maTK;
    private String tenDangNhap;
    private String matKhau;
    private boolean trangThai;
    private String maRole;
    private int maNV;
    private String maTrangThaiNV;

    public TaiKhoan() {
    }

    public TaiKhoan(int maTK, String tenDangNhap, String matKhau, boolean trangThai, String maRole, int maNV, String maTrangThaiNV) {
        this.maTK = maTK;
        this.tenDangNhap = tenDangNhap;
        this.matKhau = matKhau;
        this.trangThai = trangThai;
        this.maRole = maRole;
        this.maNV = maNV;
        this.maTrangThaiNV = maTrangThaiNV;
    }

    public int getMaTK() {
        return maTK;
    }

    public void setMaTK(int maTK) {
        this.maTK = maTK;
    }

    public String getTenDangNhap() {
        return tenDangNhap;
    }

    public void setTenDangNhap(String tenDangNhap) {
        this.tenDangNhap = tenDangNhap;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public boolean isTrangThai() {
        return trangThai;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }

    public String getMaRole() {
        return maRole;
    }

    public void setMaRole(String maRole) {
        this.maRole = maRole;
    }

    public int getMaNV() {
        return maNV;
    }

    public void setMaNV(int maNV) {
        this.maNV = maNV;
    }

    public String getMaTrangThaiNV() {
        return maTrangThaiNV;
    }

    public void setMaTrangThaiNV(String maTrangThaiNV) {
        this.maTrangThaiNV = maTrangThaiNV;
    }
}