package com.ptit.trongthien.vnexpress_v1.model.entity;

/**
 * Created by TrongThien on 7/23/2017.
 */

public class ItemNews {
    private String linkDetail;
    private String image;
    private String title;
    private String content;

    public ItemNews(String image, String title, String content) {
        this.image = image;
        this.title = title;
        this.content = content;
    }

    public ItemNews(String image, String title, String linkDetail, String content) {
        this.linkDetail = linkDetail;
        this.image = image;
        this.title = title;
        this.content = content;
    }

    public String getImage() {
        return image;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getLinkDetail() {
        return linkDetail;
    }
}
