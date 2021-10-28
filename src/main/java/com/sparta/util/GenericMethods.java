package com.sparta.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class GenericMethods {

    public static JsonNode loadCityList() {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = null;
        try {
            jsonNode = objectMapper.readTree(GenericMethods.class.getResource("city.list.json"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonNode;
    }
}
