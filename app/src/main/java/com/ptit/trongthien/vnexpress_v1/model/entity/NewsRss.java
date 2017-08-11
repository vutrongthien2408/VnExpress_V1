package com.ptit.trongthien.vnexpress_v1.model.entity;

/**
 * Created by TrongThien on 7/31/2017.
 */

public class NewsRss {
    private NewsChannel channel;

    private String version;

    public NewsChannel getChannel ()
    {
        return channel;
    }

    public void setChannel (NewsChannel channel)
    {
        this.channel = channel;
    }

    public String getVersion ()
    {
        return version;
    }

    public void setVersion (String version)
    {
        this.version = version;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [channel = "+channel+", version = "+version+"]";
    }
}
