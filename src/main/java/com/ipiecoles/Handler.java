package com.ipiecoles;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import com.google.gson.Gson;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Classe permettant d'envoyer ces données à une API
 */
public class Handler implements RequestHandler<Object, GatewayResponse> {

    /**
     * Fonction permettant d'envoyer les 10 derniers articles du Monde sur une api
     * @param _object Object
     * @param _context Context
     * @return une GatewayResponse
     */
    @Override
    public GatewayResponse handleRequest(Object _object, Context _context) {
        ActuService actuService = new ActuService();
        List<Actu> actusList;
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        headers.put("Access-Control-Allow-Origin", "https://pjvilloud.github.io");
        try {
            actusList = actuService.getActuOfTheDayXml();
        }catch (Exception e) {
            List<String> errors = new ArrayList<>();
            String ANSI_RESET = "\u001B[0m";
            String ANSI_RED = "\u001B[31m";
            //Prend en charge IOException, ParserConfigurationException, NullPointerException, SAXException
            // et XPathExpressionsException qui peuvent être soulevées dans Actuservice
            if (e.getClass().equals(SAXException.class)){
                errors.add("\n"+ANSI_RED+"ERREUR LORS DE L'ANALYSE DU XML (https://www.lemonde.fr/rss/une.xml)\n"
                        +ANSI_RESET+e.getMessage()+"\n");
            }
            if (e.getClass().equals(ParserConfigurationException.class)){
                errors.add("\n"+ANSI_RED+"ERREUR AVEC LA CONFIGURATION DU PARSE\n"
                        +ANSI_RESET+e.getMessage()+"\n");
            }
            if(e.getClass().equals(XPathExpressionException.class)){
                errors.add("\n"+ANSI_RED+"ERREUR DANS UNE EXPRESSION XPath (un champ ne correspond pas à ceux de l'XML)\n"
                        +ANSI_RESET+e.getMessage()+"\n");
            }
            if(e.getClass().equals(NullPointerException.class)){
                errors.add("\n"+ANSI_RED+"ERREUR DANS UNE EXPRESSION XPath (un champ est null)\n"
                        +ANSI_RESET+e.getMessage()+"\n");
            }
            if(e.getClass().equals(IOException.class)){
                errors.add("\n"+ANSI_RED+"ERREUR LECTURE DU XML\n"
                        +ANSI_RESET+e.getMessage()+"\n");
            }
            else{
                errors.add("\n"+ANSI_RED+"ERREUR PROBLEME LORS DE LA RECUPERATION DES ACTUALITES\n"
                        +ANSI_RESET+e.getMessage());
            }
            return new GatewayResponse("LISTE DES ERREURS :"+ errors, headers, 500);
        }
        //On transforme la liste en .json grâce à Gson (car .toString comprend des retours à la ligne "\n"
        // et des tabulations "\t"
        String body = new Gson().toJson(actusList);
        return new GatewayResponse(body, headers, 200);
    }

}
