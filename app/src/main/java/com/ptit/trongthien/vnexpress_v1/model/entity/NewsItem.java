package com.ptit.trongthien.vnexpress_v1.model.entity;

//import javax.xml.bind.annotation.XmlElement;
//import javax.xml.bind.annotation.XmlRootElement;


/**
 * Created by TrongThien on 7/31/2017.
 */

//@XmlRootElement
public class NewsItem {
    private String guid;

    private String pubDate;

    private String title;

    private String description;

    private String link;
//    @XmlElement
    public String getGuid ()
    {
        return guid;
    }

    public void setGuid (String guid)
    {
        this.guid = guid;
    }
//    @XmlElement
    public String getPubDate ()
    {
        return pubDate;
    }

    public void setPubDate (String pubDate)
    {
        this.pubDate = pubDate;
    }
//    @XmlElement
    public String getTitle ()
    {
        return title;
    }

    public void setTitle (String title)
    {
        this.title = title;
    }
//    @XmlElement
    public String getDescription ()
    {
        return description;
    }

    public void setDescription (String description)
    {
        this.description = description;
    }
//    @XmlElement
    public String getLink ()
    {
        return link;
    }

    public void setLink (String link)
    {
        this.link = link;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [guid = "+guid+", pubDate = "+pubDate+", title = "+title+", description = "+description+", link = "+link+"]";
    }
}
