package com.ipiecoles;

/**
 * Model permettant de mapper une actualité récupéré via xml
 */
public class Actu {

    /**
     * Le lien de l'actualité
     */
    private String link;
    /**
     * Le titre de l'actualité
     */
    private String title;
    /**
     * La description de l'actualité
     */
    private String description;
    /**
     * la date de publication de l'actualité
     */
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
     * @param link lien de l'article
     * @param title titre de l'article
     * @param description description de l'article
     * @param pubDate date de publication de l'article
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