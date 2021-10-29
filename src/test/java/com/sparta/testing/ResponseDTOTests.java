package com.sparta.testing;

import com.sparta.dto.ResponseDTO;
import com.sparta.dto.WeatherItemDTO;
import com.sparta.util.TestJson;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ResponseDTOTests {

    @Nested
    class ValidWeatherTests {

        private static ResponseDTO responseDTO;

        @BeforeAll
        static void initAll() {
            responseDTO = TestJson.loadJson("valid");
        }

        @Test
        @DisplayName("Check that helper method returns true when valid DTO is passed")
        void checkThatHelperMethodReturnsTrueWhenValidDtoIsPassed() {
            WeatherItemDTO weatherItemDTO = responseDTO.getWeather().get(0);
            assertTrue(responseDTO.isWeatherItemDTOValid(weatherItemDTO));
        }
    }

    @Nested
    class InvalidFieldsWeatherTests {
        private static ResponseDTO responseDTO;

        @BeforeAll
        static void initAll() {
            responseDTO = TestJson.loadJson("incorrectfields");
        }

        @Test
        @DisplayName("Check that helper method returns false when fields are mismatched")
        void checkThatHelperMethodReturnsFalseWhenFieldsAreMismatched() {
            WeatherItemDTO weatherItemDTO = responseDTO.getWeather().get(0);
            assertFalse(responseDTO.isWeatherItemDTOValid(weatherItemDTO));
        }

    }

    @Nested
    class InvalidWeatherIDTests {
        private static ResponseDTO responseDTO;

        @BeforeAll
        static void initAll() {
            responseDTO = TestJson.loadJson("invalidweatherid");
        }

        @Test
        @DisplayName("Check that helper method returns false when invalid id is passed")
        void checkThatHelperMethodReturnsFalseWhenInvalidIdIsPassed() {
            WeatherItemDTO weatherItemDTO = responseDTO.getWeather().get(0);
            assertFalse(responseDTO.isWeatherItemDTOValid(weatherItemDTO));
        }


    }



    @Nested
    class MissingFieldWeatherTests {
        private static ResponseDTO responseDTO;

        @BeforeAll
        static void initAll() {
            responseDTO = TestJson.loadJson("missingfield");
        }

        @Test
        @DisplayName("Check that helper method returns false when missing fields are passed")
        void checkThatHelperMethodReturnsFalseWhenMissingFieldsArePassed() {
            WeatherItemDTO weatherItemDTO = responseDTO.getWeather().get(0);
            assertFalse(responseDTO.isWeatherItemDTOValid(weatherItemDTO));
        }

    }

    @Nested
    class MissingIdWeatherTests {
        private static ResponseDTO responseDTO;

        @BeforeAll
        static void initAll() {
            responseDTO = TestJson.loadJson("missingid");
        }

        @Test
        @DisplayName("Check that helper method returns false when missing id is passed")
        void checkThatHelperMethodReturnsFalseWhenMissingIdIsPassed() {
            WeatherItemDTO weatherItemDTO = responseDTO.getWeather().get(0);
            assertFalse(responseDTO.isWeatherItemDTOValid(weatherItemDTO));
        }

    }
}
