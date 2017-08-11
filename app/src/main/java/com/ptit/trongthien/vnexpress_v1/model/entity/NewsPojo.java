package com.ptit.trongthien.vnexpress_v1.model.entity;

/**
 * Created by TrongThien on 7/31/2017.
 */

public class NewsPojo {
    private NewsRss rss;

    public NewsRss getRss ()
    {
        return rss;
    }

    public void setRss (NewsRss rss)
    {
        this.rss = rss;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [rss = "+rss+"]";
    }
}
