package model;

import java.sql.Date;

public class DoanhThuNgay {
    private Date ngayLap;
    private double doanhThu;

    public DoanhThuNgay() {
    }

    public DoanhThuNgay(Date ngayLap, double doanhThu) {
        this.ngayLap = ngayLap;
        this.doanhThu = doanhThu;
    }

    public Date getNgayLap() {
        return ngayLap;
    }

    public void setNgayLap(Date ngayLap) {
        this.ngayLap = ngayLap;
    }

    public double getDoanhThu() {
        return doanhThu;
    }

    public void setDoanhThu(double doanhThu) {
        this.doanhThu = doanhThu;
    }
}
