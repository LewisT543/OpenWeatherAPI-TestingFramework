package com.sparta.connection;

import com.sparta.util.Util;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 * @deprecated use ConnectionManager.java this class will be removed soon
 */
@Deprecated(forRemoval = true)
public class ConnectionManager_Alt{

    private static final String BASE_URL = "https://api.openweathermap.org";
    private static final String RESOURCES = "/data/2.5/weather";
    private static final String API_KEY = Util.getAPIKey();

    // use this for testing i dunno
    @Deprecated
    public static String getConnection(String paramsAsString) {
        return BASE_URL + RESOURCES + "?" + paramsAsString + "&appid=" + API_KEY;
    }

    public static String getConnection(String key, String value) {
        return BASE_URL + RESOURCES + "?" + key + "=" + value + "&appid=" + API_KEY;
    }

    public static HttpResponse<String> getResponse() {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL))
                .build();

        HttpResponse<String> response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return response;
    }

    public static int getStatusCode() {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL))
                .build();

        int statusCode = 0;
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            statusCode = response.statusCode();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return statusCode;
    }

    public static int getStatusCode(HttpResponse<String> response) {
        return response.statusCode();
    }

}
