package com.example.xiaojingshiping_app.imagepicker;

public class ImageItem {

    private String path;
    private String tite;
    private long data;

    public ImageItem(String path, String tite, long data) {
        this.path = path;
        this.tite = tite;
        this.data = data;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getTite() {
        return tite;
    }

    public void setTite(String tite) {
        this.tite = tite;
    }

    public long getData() {
        return data;
    }

    public void setData(long data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ImageItem{" +
                "path='" + path + '\'' +
                ", tite='" + tite + '\'' +
                ", data=" + data +
                '}';
    }
}
