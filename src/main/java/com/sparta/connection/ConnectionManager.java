package com.sparta.connection;

import com.sparta.util.Util;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Connection manager for building a URL for the OpenWeather API
 * @author edmund
 * @version 2.3
 */
public class ConnectionManager {
    private static final String BASE_URL = "https://api.openweathermap.org/data/2.5";
    private static HashMap<String, String> searchParams = new HashMap<>();
    private static StringBuilder stringBuilder;
    public enum ENDPOINTS {FIND, WEATHER_Q, WEATHER_CITY_ID, WEATHER_ZIP, BOX}

    /**
     * @author Edmund
     * @version 1.2
     * Main method for the connection manager class. Takes an enum for the desired endpoint.
     * Returns a hashmap with the url set to key url and the original query parameters
     * @param endpoints
     * @param params
     * @return Hashmap<String,String>
     * @throws IllegalArgumentException when required parameters for the selected endpoint are missing
     */
    public static HashMap getConnection(ENDPOINTS endpoints, HashMap<String, String> params) throws IllegalArgumentException {
        searchParams = params;

        //call getHttpResponse to add http status code?

        return createConnection(endpoints, searchParams);
    }

    /**
     * @author Edmund
     * @version 1.0
     * Alternate main method for the connection manager class. Takes an enum for the desired endpoint.
     * Returns just the url
     * @param endpoints
     * @param params
     * @return Hashmap<String,String>
     * @throws IllegalArgumentException when required parameters for the selected endpoint are missing
     */
    public static String getConnectionString(ENDPOINTS endpoints, HashMap<String, String> params) throws IllegalArgumentException {
        return createConnection(endpoints, params).get("url");
    }

    private static void getHttpResponse() throws IOException, InterruptedException {

        try {
            HttpClient client = HttpClient.newHttpClient();

            HttpRequest req = HttpRequest.newBuilder(URI.create(searchParams.get("url"))).GET().build();
            HttpResponse<String> res = client.send(req, HttpResponse.BodyHandlers.ofString());

            //get response code
            searchParams.put("status_code", String.valueOf(res.statusCode()));

            //get context from header - why? we already have status code, no?


        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static HashMap<String, String> createConnection(ENDPOINTS endpoints, HashMap<String, String> params) throws IllegalArgumentException {
        String url = null;

        //set unit to standard if not specified
        if (!params.containsKey("units"))
            params.put("units", "standard");

        if (!isValidUnit(params.get("units"))) {
            throw new IllegalArgumentException("units should be standard, metric or imperial");
        }

        switch (endpoints) {
            case BOX -> {url = buildBoxUrl(params);}
            case FIND -> {url =  buildFindUrl(params);}
            case WEATHER_CITY_ID -> {url = buildWeatherUrl("city", params);}
            case WEATHER_Q -> {url = buildWeatherUrl("q", params);}
            case WEATHER_ZIP -> {url = buildWeatherUrl("zip", params);}
        }

        params.put("url", url);
        return params;
    }

    /**
     * Helper method that can be used to reset the parameter Hashmap
     * @author Edmund
     * @version 1.0
     */
    public void resetParams() {
        searchParams.clear();
    }

    /***
     * @author Edmund
     * @version 2.0
     * Builds the /box endpoint of the API. bbox is a required parameter for this endpoint.
     * lang and units are optional
     * @throws IllegalArgumentException if bbox param is missing or not in format n1,n2,n3,n4,n5 or units is wrong
     * @param params
     * @return a URL String
     */
    private static String buildBoxUrl(HashMap<String,String> params) throws IllegalArgumentException {
        params.put("mode", "json");
        stringBuilder = new StringBuilder();
        stringBuilder.append(BASE_URL).append("/box/city?");

        if ((params.get("bbox") != null && !params.get("bbox").isEmpty()) && checkBBox(params.get("bbox"))) {

            params.forEach((k,v) -> {
                if (!v.isEmpty())
                    stringBuilder.append(k + "=" + v + "&");
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
     * @version 2.0
     * Builds url for the /find endpoint. Takes in a hashmap of params.
     * Expected params are lat, lon are required; following are optional
     * cnt, mode, units, lang
     * lat and lon in response will be different to query params
     * @return a URL String
     * @throws IllegalArgumentException if lat and lon are missing
     * @param params
     */
    private static String buildFindUrl(HashMap<String, String> params) throws IllegalArgumentException {
        params.put("mode", "json");
        stringBuilder = new StringBuilder();
        stringBuilder.append(BASE_URL).append("/find?");

        //check to see if required params are in hashmap
        if ((params.get("lat") != null && !params.get("lat").isEmpty())
                && (params.get("lon") != null && !params.get("lon").isEmpty())) {

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
     * @version 2.0
     * Builds the /weather endpoint url - q is a required parameter for this endpoint
     * mode is optional - should be set to json for this test
     * @param params
     * @return a URL String
     * @throws IllegalArgumentException if q is missing
     */
    private static String buildWeatherUrl(String enforcedParam,
                                          HashMap<String, String> params) throws IllegalArgumentException {
        params.put("mode", "json");
        stringBuilder = new StringBuilder();
        stringBuilder.append(BASE_URL).append("/weather?");

        //check to see if required params are in hashmap
        if (params.get(enforcedParam) != null && !params.get(enforcedParam).isEmpty()) {
            params.forEach((k,v) -> {
                if (!v.isEmpty()) {
                    stringBuilder.append(k + "=" + v + "&");
                }
            });

            stringBuilder.append("appid=" + Util.getAPIKey());
        } else throw new IllegalArgumentException(enforcedParam + " is required");
        return stringBuilder.toString();
    }

    private static boolean isValidUnit(String unit) {
        List<String> units = new ArrayList<>(List.of("standard", "metric", "imperial"));
        return units.contains(unit);
    }

}
