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
import java.util.StringJoiner;

public class ActuService {

    public Actu getActuOfTheDayXml() throws Exception{
        //String contenu = "<quotes> <quote> <author>Moi</author> <quote>Coucou</quote> </quote> </quotes>";
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse("https://www.lemonde.fr/rss/une.xml");
        XPath xPath = XPathFactory.newInstance().newXPath();
        String expressionLink = "/actu/link";
        String expressionTitle = "/actu/title";
        String expressionDescription = "/actu/description";
        String expressionPubDate = "/actu/pubdate";
        String Link = ((Node) xPath.compile(expressionLink).evaluate(doc, XPathConstants.NODE)).getNodeValue();
        String Title = ((Node) xPath.compile(expressionTitle).evaluate(doc, XPathConstants.NODE)).getNodeValue();
        String Description = ((Node) xPath.compile(expressionDescription).evaluate(doc, XPathConstants.NODE)).getNodeValue();
        String PubDate = ((Node) xPath.compile(expressionPubDate).evaluate(doc, XPathConstants.NODE)).getNodeValue();
        return new Actu(Link, Title, Description, PubDate);
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