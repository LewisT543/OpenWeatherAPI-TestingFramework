package com.sparta.connection;

import com.sparta.util.Util;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ConnectionManager {
    private static final String BASE_URL = "https://api.openweathermap.org/data/2.5";
    private static StringBuilder stringBuilder;
    public enum ENDPOINTS {FIND, WEATHER, BOX}

    public static HashMap getConnection(ENDPOINTS endpoints, HashMap<String, String> params) throws IllegalArgumentException {
        String url = null;
        switch (endpoints) {
            case BOX -> {url = buildBoxUrl(params);}
            case FIND -> {url =  buildFindUrl(params);}
            case WEATHER -> {url = buildWeatherUrl(params);}
        }
        
        params.put("url", url);
        return params;
    }

    /***
     * Builds the /box endpoint of the API. bbox is a required parameter for this endpoint.
     * lang and units are optional
     * @throws IllegalArgumentException if bbox param is missing or not in format n1,n2,n3,n4,n5
     * @param params
     * @return a URL String
     */
    private static String buildBoxUrl(HashMap<String,String> params) throws IllegalArgumentException {
        stringBuilder = new StringBuilder();
        stringBuilder.append(BASE_URL).append("/box/city?");

        if (!params.get("bbox").isEmpty() && checkBBox(params.get("bbox"))) {
            params.forEach((k,v) -> {
                if (!v.isEmpty()) {
                    stringBuilder.append(k + "=" + v + "&");
                }
            });
            stringBuilder.append("appid=" + Util.getAPIKey());
        } else throw new IllegalArgumentException("bbox is required and should formatted: \n\tn1,n2,n3,n4,n5");
        return stringBuilder.toString();
    }

    private static boolean checkBBox(String bbox) {
        if (bbox != null) {
            Pattern pattern = Pattern.compile("[0-9]+(,[0-9]+)+");
            Matcher matcher = pattern.matcher(bbox);
            return (matcher.find() && bbox.split(",").length == 5);
        } else return false;
    }

    /**
     * @author Edmund
     * @version 1.0
     * Builds url for the /find endpoint. Takes in a hashmap of params.
     * Expected params are lat, lon are required; following are optional
     * cnt, mode, units, lang
     * lat and lon in response will be different to query params
     * @return a URL String
     * @throws IllegalArgumentException if lat and long are missing
     * @param params
     */
    private static String buildFindUrl(HashMap<String, String> params) throws IllegalArgumentException {
        stringBuilder = new StringBuilder();
        stringBuilder.append(BASE_URL).append("/find?");

        //check to see if required params are in hashmap
        if (!params.get("lat").isEmpty() && !params.get("lon").isEmpty()) {

            params.forEach((k, v) -> {
                if (!v.isEmpty()) {
                    stringBuilder.append(k + "=" + v + "&");
                }
            });

            stringBuilder.append("appid=" + Util.getAPIKey());
        } else throw new IllegalArgumentException("lat and lon are required");
        return stringBuilder.toString();
    }

    /**
     * @author Edmund
     * @version 1.0
     * Builds the /weather endpoint url - q is a required parameter for this endpoint
     * mode is optional - should be set to json for this test
     * @param params
     * @return a URL String
     * @throws IllegalArgumentException if q is missing
     */
    private static String buildWeatherUrl(HashMap<String, String> params) throws IllegalArgumentException {
        params.put("mode", "json");
        stringBuilder = new StringBuilder();
        stringBuilder.append(BASE_URL).append("/weather?");

        //check to see if required params are in hashmap
        if (!params.get("q").isEmpty()) {
            params.forEach((k,v) -> {
                if (!v.isEmpty()) {
                    stringBuilder.append(k + "=" + v + "&");
                }
            });

            stringBuilder.append("appid=" + Util.getAPIKey());
        } else throw new IllegalArgumentException("q is required");
        return stringBuilder.toString();
    }


}
