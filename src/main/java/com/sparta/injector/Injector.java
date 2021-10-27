package com.sparta.injector;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.connection.ConnectionManager;
import com.sparta.dto.MainDTO;

import java.io.IOException;
import java.net.URL;

public class Injector {
    private static final ObjectMapper objectMapper = new ObjectMapper();


    public static MainDTO injectMainDTO(String url) {
        MainDTO mainDTO =  new MainDTO();
        try {
            mainDTO = objectMapper.readValue(new URL(url), MainDTO.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mainDTO;
    }

}
