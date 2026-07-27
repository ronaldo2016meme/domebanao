package model;

public class Size {
    private String maSize;
    private String tenSize;

    public Size() {
    }

    public Size(String maSize, String tenSize) {
        this.maSize = maSize;
        this.tenSize = tenSize;
    }

    public String getMaSize() {
        return maSize;
    }

    public void setMaSize(String maSize) {
        this.maSize = maSize;
    }

    public String getTenSize() {
        return tenSize;
    }

    public void setTenSize(String tenSize) {
        this.tenSize = tenSize;
    }
}
