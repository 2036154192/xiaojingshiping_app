package com.example.xiaojingshiping_app.shouye;

import java.io.Serializable;

public class VideoEntiy implements Serializable {
    private int id;
    private String name;
    private String text;
    private int images;

    public VideoEntiy(int id, String name, String text) {
        this.id = id;
        this.name = name;
        this.text = text;
    }

    public int getImages() {
        return images;
    }

    public void setImages(int images) {
        this.images = images;
    }

    public VideoEntiy() { }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
