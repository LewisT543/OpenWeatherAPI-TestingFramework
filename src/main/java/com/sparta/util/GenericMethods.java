package com.sparta.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class GenericMethods {

    public static JsonNode loadCityList() {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = null;
        try {
            jsonNode = objectMapper.readTree(Paths.get("src/main/resources/city_list.json").toFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonNode;
    }

    public static boolean cityIdIsCorrect(String cityName, int ID) {
        JsonNode cityList = loadCityList();
        for (JsonNode city: cityList) {
            if (city.get("name").textValue().equals(cityName)) {
                if (city.get("id").intValue() == ID) {
                    return true;
                } else {
                    return false;
                }
            }
        }
        return false;
    }

    public static boolean cityLonIsCorrect(String cityName, double lon) {
        JsonNode cityList = loadCityList();
        for (JsonNode city: cityList) {
            if (city.get("name").textValue().equals(cityName)) {
                JsonNode coords = city.get("coord");
                if (coords.get("lon").doubleValue() == lon) {
                    return true;
                } else {
                    return false;
                }
            }
        }
        return false;
    }

    public static boolean cityLatIsCorrect(String cityName, double lat) {
        JsonNode cityList = loadCityList();
        for (JsonNode city: cityList) {
            if (city.get("name").textValue().equals(cityName)) {
                JsonNode coords = city.get("coord");
                if (coords.get("lat").doubleValue() == lat) {
                    return true;
                } else {
                    return false;
                }
            }
        }
        return false;
    }

    public static boolean isGreaterThanZero(Double input){
        return input >= 0;
    }

    public static boolean isGreaterThanZeroOrNull(Double input){
            return input == null|| input >= 0;
    }

}
