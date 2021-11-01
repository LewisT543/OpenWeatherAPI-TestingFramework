package com.sparta.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

import static org.apache.commons.lang3.time.DateUtils.isSameDay;

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

    /**
    * Modified version of the is greater than or equal to method with the base comparison number removed, this only checks against 0.
    * @author Dan
    * @version 1.0
    * @param input
    * @return boolean
    **/
    public static boolean isGreaterThanOrEqualToZero(Object input){
        return isGreaterThanOrEqualTo(input,0.0);
    }

    /**
    * Takes in an object which ideally wants to be a double however can be an integer as well, and compares this to a double.
    * If the first number is larger than or equal to the second number then this method will return true.
    * This method will return false if the input number is a string, or if the input is null.
    * @author Dan
    * @version 2.0
    * @param inputNumber
    * @param baseNumberForComparison
    * @return boolean
    **/
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
    /**
     * Modified version of the is greater than or equal to method with the base comparison number removed, only checks against 0.
     * The check for null has been changed so that this will return true if null is passed.
     * @author Dan
     * @version 1.0
     * @param input
     * @return boolean
     **/
    public static boolean isGreaterThanOrEqualToZeroOrNull(Object input){
        if(input == null) return true;
        return isGreaterThanOrEqualToZero(input);
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
