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


    public static boolean isGreaterThanOrEqualToZero(Object input){
        return isGreaterThanOrEqualTo(input,0.0);
    }

    public static boolean isGreaterThanOrEqualTo(Object inputNumber, Double baseNumberForComparison){
        if(inputNumber == null || baseNumberForComparison == null) return false;
        if(inputNumber.getClass() == String.class){
            return false;
        }
        try{
            return (Double) inputNumber >= baseNumberForComparison;
        }catch(ClassCastException ce){
            return(Integer) inputNumber >= baseNumberForComparison;
        }

    }


    public static boolean isGreaterThanOrEqualToZeroOrNull(Object input){
        if(input == null) return true;
        return isGreaterThanOrEqualToZero(input);
    }


    public static boolean isADouble(Object field) {
        if(field instanceof Double) {
        //if (field.getClass() == Double.class) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isInteger(Object object) {
        return object instanceof Integer;
    }

    public static boolean isString(Object object){
        return object instanceof String;
    }

    public static boolean IsNotNull(Object o){
        return o != null;
    }

    public static boolean isBetweenXAndY(Double value, Double x, Double y){
        try {
            return value>= x && value<=y;
        } catch (NullPointerException npe){
            return false;
        }
    }

}
