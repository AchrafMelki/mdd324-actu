package com.ipiecoles;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

/**
 * Service Actu
 */
public class ActuService {

    /**
     * Fonction pour récupérer les 10 dernières actu du jour
     * @return actusList la liste des 10 dernières actualités
     *
     */
    public List<Actu> getActuOfTheDayXml(){

        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder;
        Document doc = null;
    
        try {
            dBuilder = dbFactory.newDocumentBuilder();
            doc = dBuilder.parse("https://www.lemonde.fr/rss/une.xml");
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
    

        int i;
        List<Actu> actusList = new ArrayList<Actu>();
        for( i=1; i<11; i++){
    
            XPath xPath = XPathFactory.newInstance().newXPath();
            String expressionLink = "/rss/channel/item["+i+"]/link";
            String expressionTitle = "/rss/channel/item["+i+"]/title";
            String expressionDescription = "/rss/channel/item["+i+"]/description";
            String expressionPubDate = "/rss/channel/item["+i+"]/pubDate";
            String link = null;
            String title = null;
            String description = null;
            String pubDate = null;
            try {
                link = ((Node) xPath.compile(expressionLink).evaluate(doc, XPathConstants.NODE)).getTextContent();
                title = ((Node) xPath.compile(expressionTitle).evaluate(doc, XPathConstants.NODE)).getTextContent();
                description = ((Node) xPath.compile(expressionDescription).evaluate(doc, XPathConstants.NODE)).getTextContent();
                pubDate = ((Node) xPath.compile(expressionPubDate).evaluate(doc, XPathConstants.NODE)).getTextContent();
            } catch (XPathExpressionException e) {
                e.printStackTrace();
            }
    
            Actu actu = new Actu(link,title,description,pubDate);
                actusList.add(actu);
    
            }
        return actusList;
    }


   /* private String getPageContents(String address) throws IOException {
        BufferedReader br = null;
        StringJoiner lines = new StringJoiner(System.lineSeparator());
        try {
            URL url = new URL(address);
            br = new BufferedReader(new InputStreamReader(url.openStream()));
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
        } finally {
            if (br != null) {
                br.close();
            }
        }
        return lines.toString();
    }*/
}