package com.ptit.trongthien.vnexpress_v1.model.entity;

/**
 * Created by TrongThien on 7/31/2017.
 */

public class NewsChannel {
    private String pubDate;

    private String title;

    private String description;

    private String link;

    private NewsItem[] item;

    private NewsImage image;

    private String generator;

    public String getPubDate ()
    {
        return pubDate;
    }

    public void setPubDate (String pubDate)
    {
        this.pubDate = pubDate;
    }

    public String getTitle ()
    {
        return title;
    }

    public void setTitle (String title)
    {
        this.title = title;
    }

    public String getDescription ()
    {
        return description;
    }

    public void setDescription (String description)
    {
        this.description = description;
    }

    public String getLink ()
    {
        return link;
    }

    public void setLink (String link)
    {
        this.link = link;
    }

    public NewsItem[] getItem ()
    {
        return item;
    }

    public void setItem (NewsItem[] item)
    {
        this.item = item;
    }

    public NewsImage getImage ()
    {
        return image;
    }

    public void setImage (NewsImage image)
    {
        this.image = image;
    }

    public String getGenerator ()
    {
        return generator;
    }

    public void setGenerator (String generator)
    {
        this.generator = generator;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [pubDate = "+pubDate+", title = "+title+", description = "+description+", link = "+link+", item = "+item+", image = "+image+", generator = "+generator+"]";
    }
}
