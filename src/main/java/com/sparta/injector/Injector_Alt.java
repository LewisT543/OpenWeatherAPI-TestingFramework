package com.sparta.injector;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.dto.ResponseDTO;

import java.io.IOException;
import java.net.URL;

public class Injector_Alt {
    private static ObjectMapper objectMapper = new ObjectMapper();

    public static ResponseDTO injectResponseDTO(String url) {
        System.out.println(url);
        ResponseDTO responseDTO = null;
        try {
            responseDTO = objectMapper.readValue(new URL(url), ResponseDTO.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return responseDTO;
    }
}
