package com.sparta.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.dto.ResponseDTO;

import java.io.IOException;
import java.nio.file.Paths;

public class TestJson {

    public static ResponseDTO loadJson(String name) {
        ObjectMapper objectMapper = new ObjectMapper();
        ResponseDTO responseDTO = null;
        try {
            responseDTO = objectMapper.readValue(Paths.get("src/main/resources/weathercodesjson/",name + ".json").toFile(), ResponseDTO.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return responseDTO;
    }
}
