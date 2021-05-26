package com.ipiecoles;


import java.util.Date;

/**
 * Model Actu
 */
public class Actu {

    private String link;
    private String title;
    private String description;
    private String pubDate;

    @Override
    public String toString() {
        return '\t'+"{"+'\n'+
                    '\t'+'\t'+"link = " + link + '\n' +
                    '\t'+'\t'+"title = " + title + '\n' +
                    '\t'+'\t'+"description = " + description + '\n' +
                    '\t'+'\t'+"pubDate = " + pubDate + '\n' +
                '\t'+"}"+'\n';
    }

    /**
     *  Constructor
     * @param link
     * @param title
     * @param description
     * @param pubDate
     */
    public Actu(String link, String title, String description, String pubDate) {
        this.link = link;
        this.title = title;
        this.description = description;
        this.pubDate = pubDate;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

}