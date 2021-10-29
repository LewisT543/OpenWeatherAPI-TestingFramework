package com.sparta.connection;

import com.sparta.util.Util;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Connection manager for building a URL for the OpenWeather API
 * @author edmund, denis, lewis, bilal
 * @version 3.1
 */
public class ConnectionManager {
    private static final String BASE_URL = "https://api.openweathermap.org/data/2.5";
    private static HashMap<String, String> searchParams = new HashMap<>();
    private static StringBuilder stringBuilder;
    public enum ENDPOINTS {FIND, WEATHER_Q, WEATHER_CITY_ID, WEATHER_ZIP, BOX}

    private static HashMap<String, String> headersAndStatusCode = new HashMap<>();

    public static HashMap<String, String> getHeadersAndStatusCode() {
        return headersAndStatusCode;
    }

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
    public static HashMap<String, String> getConnection(ENDPOINTS endpoints, HashMap<String, String> params) throws IllegalArgumentException {
        searchParams = params;
        createConnection(endpoints, searchParams);

        try {
            getHttpResponse();
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
        return searchParams;
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

    /**
     * Uses the HttpClient class to query the api url. This method then stores the returned status code
     * and http response headers in a hashmap.
     * @author Edmund, Denis, Lewis, Bilal
     * @version 1.3
     * @throws IOException if http client can't connect to the server
     * @throws InterruptedException if connection is terminated unexpectedly
     */
    private static void getHttpResponse() throws IOException, InterruptedException {
        try {
            HttpClient client = HttpClient.newHttpClient();

            HttpRequest req = HttpRequest.newBuilder(URI.create(searchParams.get("url"))).GET().build();
            HttpResponse<String> res = client.send(req, HttpResponse.BodyHandlers.ofString());

            // This works, but it isn't tested yet - Might write them later, might not...
            headersAndStatusCode.put("status_code", String.valueOf(res.statusCode()));

            for (Map.Entry<String, List<String>> entry : res.headers().map().entrySet()) {
                for (String item : entry.getValue()) {
                    String numericName = entry.getKey();
                    headersAndStatusCode.put(numericName, item);
                }
            }
            for (Map.Entry<String, String> entry : headersAndStatusCode.entrySet()) {
                System.out.println(entry.getKey() + " : " + entry.getValue());
            }
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
            case FIND -> {url =  buildFindUrl();}
            case WEATHER_CITY_ID -> {url = buildWeatherUrl("city");}
            case WEATHER_Q -> {url = buildWeatherUrl("q");}
            case WEATHER_ZIP -> {url = buildWeatherUrl("zip");}
        }

        params.put("url", url);
        return params;
    }

    /**
     * Helper method that can be used to reset the parameter Hashmap and headers hashmap
     * @author Edmund
     * @version 1.1
     */
    public static void resetParams() {
        searchParams.clear();
        headersAndStatusCode.clear();
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
     * @version 2.1
     * Builds url for the /find endpoint. Takes in a hashmap of params.
     * Expected params are lat, lon are required; following are optional
     * cnt, mode, units, lang
     * lat and lon in response will be different to query params
     * @return a URL String
     * @throws IllegalArgumentException if lat and lon are missing or parameter is the wrong format
     */
    private static String buildFindUrl() throws IllegalArgumentException {
        searchParams.put("mode", "json");
        stringBuilder = new StringBuilder().append(BASE_URL).append("/find?");

        //if cnt is provided check to see that it meets the rules
        if (searchParams.containsKey("cnt"))
            if (!isValidCNT(searchParams.get("cnt")))
                throw new IllegalArgumentException("cnt should be > 1 and =< 50");

        //check to see if required params are in hashmap
        if ((searchParams.get("lat") != null && !searchParams.get("lat").isEmpty())
                && (searchParams.get("lon") != null && !searchParams.get("lon").isEmpty())) {

            searchParams.forEach((k, v) -> {
                if (!v.isEmpty()) {
                    stringBuilder.append(k + "=" + v + "&");
                }
            });

            stringBuilder.append("appid=" + Util.getAPIKey());
        } else throw new IllegalArgumentException("lat and lon are required");
        return stringBuilder.toString();
    }

    /**
     * Internal helper method to check that cnt if provided is between 1-50
     * @author Edmund
     * @version 1.0
     * @param cnt
     * @return boolean
     */
    private static boolean isValidCNT(String cnt) {
        if (cnt != null && !cnt.isEmpty()) {
            try {
                int num = Integer.parseInt(cnt);
                if ((num > 1) && (num <= 50))
                    return true;
            } catch (NumberFormatException e) {
                return false;
            }
        }
        return false;
    }

    /**
     * @author Edmund
     * @version 2.1
     * Builds the /weather endpoint url - q is a required parameter for this endpoint
     * mode is optional - should be set to json for this test
     * @param enforcedParam - used to specify the required parameter
     * @return a URL String
     * @throws IllegalArgumentException if q is missing
     */
    private static String buildWeatherUrl(String enforcedParam) throws IllegalArgumentException {
        searchParams.put("mode", "json");
        stringBuilder = new StringBuilder();
        stringBuilder.append(BASE_URL).append("/weather?");

        //check to see if required params are in hashmap
        if (searchParams.get(enforcedParam) != null && !searchParams.get(enforcedParam).isEmpty()) {
            searchParams.forEach((k,v) -> {
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
