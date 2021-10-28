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
            if(city.get("name").textValue().equals(cityName)) {
                return city.get("id").intValue() == ID;
            }
        }
        return false;
    }

    public static boolean cityLonIsCorrect(String cityName, double lon) {
        JsonNode cityList = loadCityList();
        for (JsonNode city: cityList) {
            if (city.get("name").textValue().equals(cityName)) {
                JsonNode coords = city.get("coord");
                return coords.get("lon").doubleValue() == lon;
            }
        }
        return false;
    }

    public static boolean cityLatIsCorrect(String cityName, double lat) {
        JsonNode cityList = loadCityList();
        for (JsonNode city: cityList) {
            if (city.get("name").textValue().equals(cityName)) {
                JsonNode coords = city.get("coord");
                return coords.get("lat").doubleValue() == lat;
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

    /**
     * @deprecated dan rewrote his method to implement this method's functionality
     */
    @Deprecated
    public static boolean isGreaterThan(Double num, Double field) {
        if (num == null || field == null) {
            throw new NullPointerException("bro why'd you give me a null value. why'd you do this to me man. " +
                    "i trusted you bro. how could you");
        }
        return field > num;
    }

    public static boolean isDouble(Object field) {
        return field instanceof Double;
    }

    public static boolean isInteger(Object object) {
        return object instanceof Integer;
    }

    public static boolean isString(Object object){
        return object instanceof String;
    }

    public static boolean isLong(Object object){
        return object instanceof Long;
    }

    public static boolean isNotNull(Object o){
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
