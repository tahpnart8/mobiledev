package com.example.lab7;

import java.io.Serializable;

public class Article implements Serializable {

    private static final long serialVersionUID = 1L;

    private String title;
    private String content;
    private int imgCover; // id anh trong res/drawable
    private int view;

    public Article() {
    }

    public Article(String title, String content, int imgCover) {
        this.title = title;
        this.content = content;
        this.imgCover = imgCover;
        this.view = 0;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getImgCover() {
        return imgCover;
    }

    public void setImgCover(int imgCover) {
        this.imgCover = imgCover;
    }

    public int getView() {
        return view;
    }

    public void setView(int view) {
        this.view = view;
    }
}
