package model;

import java.sql.Date;

public class NhanVien {

    private int maNV;
    private String hoTen;
    private String sdt;
    private Date ngaySinh;
    private String gioiTinh;
    private String quocTich;
    private String queQuan;
    private String noiThuongTru;
    private String email;
    private String cccd;
    private String chucVu;
    private String maRole;
    private String maTrangThai;
    private boolean coTaiKhoan;

    public NhanVien() {
    }

    public NhanVien(int maNV, String hoTen, String sdt, Date ngaySinh, String gioiTinh, String quocTich, String queQuan, String noiThuongTru, String email, String cccd, String chucVu, String maRole, String maTrangThai, boolean coTaiKhoan) {
        this.maNV = maNV;
        this.hoTen = hoTen;
        this.sdt = sdt;
        this.ngaySinh = ngaySinh;
        this.gioiTinh = gioiTinh;
        this.quocTich = quocTich;
        this.queQuan = queQuan;
        this.noiThuongTru = noiThuongTru;
        this.email = email;
        this.cccd = cccd;
        this.chucVu = chucVu;
        this.maRole = maRole;
        this.maTrangThai = maTrangThai;
        this.coTaiKhoan = coTaiKhoan;
    }

    public int getMaNV() {
        return maNV;
    }

    public void setMaNV(int maNV) {
        this.maNV = maNV;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public Date getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getQuocTich() {
        return quocTich;
    }

    public void setQuocTich(String quocTich) {
        this.quocTich = quocTich;
    }

    public String getQueQuan() {
        return queQuan;
    }

    public void setQueQuan(String queQuan) {
        this.queQuan = queQuan;
    }

    public String getNoiThuongTru() {
        return noiThuongTru;
    }

    public void setNoiThuongTru(String noiThuongTru) {
        this.noiThuongTru = noiThuongTru;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCccd() {
        return cccd;
    }

    public void setCccd(String cccd) {
        this.cccd = cccd;
    }

    public String getChucVu() {
        return chucVu;
    }

    public void setChucVu(String chucVu) {
        this.chucVu = chucVu;
    }

    public String getMaRole() {
        return maRole;
    }

    public void setMaRole(String maRole) {
        this.maRole = maRole;
    }

    public String getMaTrangThai() {
        return maTrangThai;
    }

    public void setMaTrangThai(String maTrangThai) {
        this.maTrangThai = maTrangThai;
    }

    public boolean isCoTaiKhoan() {
        return coTaiKhoan;
    }

    public void setCoTaiKhoan(boolean coTaiKhoan) {
        this.coTaiKhoan = coTaiKhoan;
    }
}