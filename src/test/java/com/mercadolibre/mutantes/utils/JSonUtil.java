package com.mercadolibre.mutantes.utils;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;

public final class JSonUtil {
    public static JSONObject readAndValidateJSON(){
        JSONParser parser = new JSONParser();
        JSONObject jsonObject = null;
        try{
            jsonObject = (JSONObject) parser.parse(new FileReader("src/temp/test.json"));
            JSONArray jsonArray= (JSONArray) jsonObject.get("dnaSequencing");
            if (jsonArray == null){
                Logger.printError("The dnaSequencing array is not found. ");
            }
        }catch(Exception e){
            Logger.printError("Invalid JSON format. ".concat(e.getMessage()));
        }
        return jsonObject;
    }
}
