package com.sparta.util;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class WeatherCodesTests {
    @BeforeAll
    static void initAll() {
        WeatherCodes.readWeatherCodes();
    }

    @Test
    @DisplayName("Check that file has been read")
    void checkThatFileHasBeenRead() {
        assertTrue(WeatherCodes.hasReadFile());
    }

    @Test
    @DisplayName("Check that correct values are returned for a valid weather code ID")
    void checkThatCorrectValuesAreReturnedForAValidWeatherCodeId() {
        //300,Drizzle,light intensity drizzle,09d,09d
        String[] target = {"Drizzle", "light intensity drizzle", "09d", "09d"};
        String[] result = WeatherCodes.getWeatherCode(300).toArray(new String[4]);

        assertArrayEquals(target, result);
    }

    @Test
    @DisplayName("Check that null is returned for invalid weather code id")
    void checkThatNullIsReturnedForInvalidWeatherCodeId() {
        assertNull(WeatherCodes.getWeatherCode(347385734));
    }
}
