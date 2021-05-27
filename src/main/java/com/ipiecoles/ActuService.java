package com.ipiecoles;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import java.util.ArrayList;
import java.util.List;

/**
 * Service gérant les fonctions relatives à la récupération des actualités
 */
public class ActuService {

    /**
     * Fonction pour récupérer les 10 dernières actu du jour via le flux rss de lemonde.fr
     *
     *@return actusList la liste des 10 dernières actualités
     */
    public List<Actu> getActuOfTheDayXml() throws Exception{

        //On récupère le xml
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse("https://www.lemonde.fr/rss/une.xml");

        //On initialise la list et on boucle
        List<Actu> actusList = new ArrayList<>();
        for(int compteur=1; compteur<11; compteur++){

            //On récupère les champs souhaités pour chaque actualité
            XPath xPath = XPathFactory.newInstance().newXPath();
            String expressionLink = "/rss/channel/item["+compteur+"]/link";
            String expressionTitle = "/rss/channel/item["+compteur+"]/title";
            String expressionDescription = "/rss/channel/item["+compteur+"]/description";
            String expressionPubDate = "/rss/channel/item["+compteur+"]/pubDate";
            String link = ((Node) xPath.compile(expressionLink).evaluate(doc, XPathConstants.NODE)).getTextContent();
            String title = ((Node) xPath.compile(expressionTitle).evaluate(doc, XPathConstants.NODE)).getTextContent();
            String description = ((Node) xPath.compile(expressionDescription).evaluate(doc, XPathConstants.NODE)).getTextContent();
            String pubDate = ((Node) xPath.compile(expressionPubDate).evaluate(doc, XPathConstants.NODE)).getTextContent();

            //On ajoute l'actualité à la liste
            Actu actu = new Actu(link,title,description,pubDate);
                actusList.add(actu);
        }
        return actusList;
    }
}