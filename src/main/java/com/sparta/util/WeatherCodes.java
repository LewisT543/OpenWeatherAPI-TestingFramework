package com.sparta.util;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class WeatherCodes {
    private static HashMap<Integer, ArrayList<String>> weatherCodes = new HashMap<>();
    private static boolean hasReadFile = false;

    /**
     * Reads a CSV file containing the weather codes and associated data, and adds them to a HashMap
     */
    public static void readWeatherCodes() {
        String line;
        try (BufferedReader in = new BufferedReader(new FileReader("src/main/resources/weather_codes.csv"))) {

            while ((line = in.readLine()) != null) {
                parseLine(line);
            }
            hasReadFile = true;

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void parseLine(String line) {

        ArrayList<String> splitLine = new ArrayList<>(List.of(line.split(",")));
        Integer id = Integer.parseInt(splitLine.get(0));
        splitLine.remove(0);

        weatherCodes.put(id, splitLine);
    }

    /**
     * Returns an ArrayList that corresponds to the weather code ID
     * Data is taken from https://openweathermap.org/weather-conditions
     * @param id a weather code id
     * @return the associated values of the ID (Main, Description, Icon)
     */
    public static ArrayList<String> getWeatherCode(int id) {
        return weatherCodes.get(id);
    }

    public static boolean hasReadFile() {
        return hasReadFile;
    }
}
