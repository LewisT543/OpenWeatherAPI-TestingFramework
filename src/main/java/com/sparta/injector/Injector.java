package com.sparta.injector;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.dto.*;

import java.io.IOException;
import java.net.URL;

public class Injector {
    private static final ObjectMapper objectMapper = new ObjectMapper();
    @Deprecated
    public static CloudsDTO injectCloudsDTO(String url) {
        CloudsDTO cloudsDTO =  new CloudsDTO();
        try {
            cloudsDTO = objectMapper.readValue(new URL(url), CloudsDTO.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return cloudsDTO;
    }
    @Deprecated
    public static CoordDTO injectCoordDTO(String url) {
        CoordDTO coordDTO =  new CoordDTO();
        try {
            coordDTO = objectMapper.readValue(new URL(url), CoordDTO.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return coordDTO;
    }
    @Deprecated
    public static MainDTO injectMainDTO(String url) {
        MainDTO mainDTO =  new MainDTO();
        try {
            mainDTO = objectMapper.readValue(new URL(url), MainDTO.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mainDTO;
    }
    @Deprecated
    public static RainDTO injectRainDTO(String url) {
        RainDTO rainDTO =  new RainDTO();
        try {
            rainDTO = objectMapper.readValue(new URL(url), RainDTO.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rainDTO;
    }

    public static ResponseDTO injectResponseDTO(String url) {
        ResponseDTO responseDTO =  new ResponseDTO();
        try {
            responseDTO = objectMapper.readValue(new URL(url), ResponseDTO.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return responseDTO;
    }
    @Deprecated
    public static SnowDTO injectSnowDTO(String url) {
        SnowDTO snowDTO = new SnowDTO();
        try {
            snowDTO = objectMapper.readValue(new URL(url), SnowDTO.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return snowDTO;
    }
    @Deprecated
    public static SysDTO injectSysDTO(String url) {
        SysDTO sysDTO = new SysDTO();
        try {
            sysDTO = objectMapper.readValue(new URL(url), SysDTO.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sysDTO;
    }
    @Deprecated
    public static WeatherItemDTO injectWeatherItemDTO(String url) {
        WeatherItemDTO weatherItemDTO = new WeatherItemDTO();
        try {
            weatherItemDTO = objectMapper.readValue(new URL(url), WeatherItemDTO.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return weatherItemDTO;
    }
    @Deprecated
    public static WindDTO injectWindDTO(String url) {
        WindDTO windDTO = new WindDTO();
        try {
            windDTO = objectMapper.readValue(new URL(url), WindDTO.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return windDTO;
    }
}
