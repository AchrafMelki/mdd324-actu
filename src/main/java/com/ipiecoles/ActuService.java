package com.ipiecoles;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class ActuService {

    public List<Actu> getActuOfTheDayXml() throws Exception{
        //String contenu = "<quotes> <quote> <author>Moi</author> <quote>Coucou</quote> </quote> </quotes>";
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse("https://www.lemonde.fr/rss/une.xml");
        int i;
        List<Actu> actusList = new ArrayList<Actu>();
        for ( i=1; i<11; i++)
        {
            XPath xPath = XPathFactory.newInstance().newXPath();
            String expressionLink = "/rss/channel/item[i]/link";
            String expressionTitle = "/rss/channel/item[i]/title";
            String expressionDescription = "/rss/channel/item[i]/description";
            String expressionPubDate = "/rss/channel/item[i]/pubDate";
            String Link = ((Node) xPath.compile(expressionLink).evaluate(doc, XPathConstants.NODE)).getTextContent();
            String Title = ((Node) xPath.compile(expressionTitle).evaluate(doc, XPathConstants.NODE)).getTextContent();
            String Description = ((Node) xPath.compile(expressionDescription).evaluate(doc, XPathConstants.NODE)).getTextContent();
            String PubDate = ((Node) xPath.compile(expressionPubDate).evaluate(doc, XPathConstants.NODE)).getTextContent();
            Actu actu = new Actu(Link,Title,Description,PubDate);
            actusList.add(actu);
        }
        return actusList;
    }


    private String getPageContents(String address) throws IOException {
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
    }
}