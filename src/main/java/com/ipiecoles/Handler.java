package com.ipiecoles;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.google.gson.Gson;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Handler implements RequestHandler<Object, GatewayResponse> {
    @Override
    public GatewayResponse handleRequest(Object o, Context context) {
        ActuService actuService = new ActuService();
        List<Actu> actusList =  new ArrayList<Actu>();
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        headers.put("Access-Control-Allow-Origin", "https://pjvilloud.github.io");
        try {
            actusList = actuService.getActuOfTheDayXml();
        }catch (Exception e) {
            //Gestion d'erreur
            return new GatewayResponse("{\"error\":\"Problème lors de la récupération des actualités\")", headers, 500);
        }
        String body = new Gson().toJson(actusList);
        return new GatewayResponse(body, headers, 200);
    }
}
