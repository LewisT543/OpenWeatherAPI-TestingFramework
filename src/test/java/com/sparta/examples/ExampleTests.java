package com.sparta.examples;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.connection.ConnectionManager;
import com.sparta.dto.ResponseDTO;
import com.sparta.injector.Injector;
import com.sparta.util.Util;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.*;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Properties;
import java.util.Random;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;

public class ExampleTests {

    private static ConnectionManager connectionManager;
    private static HashMap<String, String> params;
    private static ResponseDTO rDTO;
    private static final String ROOT = "https://api.openweathermap.org/data/2.5/weather";
    private static String key;
    static HttpClient client = HttpClient.newHttpClient();

    @BeforeAll
    static void initAll(TestInfo testInfo) {
        // fetch api key
        try (InputStream input = Util.class.getClassLoader().getResourceAsStream("application.properties")) {
            Properties prop = new Properties();
            if (input == null) {
                System.err.println("Sorry, unable to find application.properties");
            }
            prop.load(input);
            key = prop.getProperty("API-Key");
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        connectionManager = new ConnectionManager();
        params = new HashMap<>();
        System.out.println(testInfo.getDisplayName() + " starting\n-----------");
    }

    @BeforeEach
    void init(TestInfo testInfo) {
        System.out.println(testInfo.getDisplayName() + " : START");
    }

        // checking if data comes back

    @Nested
    @DisplayName("UsingTheFrameWork")
    class usingTheFrameWork {


        @Test
        @DisplayName("Searching by Zipcode returns the correct zipcode")
        void searchingByZipcodeReturnsTheCorrectZipcode() {
            params.put("zip", "Dh1,gb");
            ConnectionManager.getConnection(ConnectionManager.ENDPOINTS.WEATHER_ZIP,params);
            rDTO = Injector.injectResponseDTO(params.get("url"));
            assertEquals("Shincliffe", rDTO.getName());
        }

        @Test
        @DisplayName("is the humidity valid")
        void isTheHumidityValidValue() {
            params.put("q","Dh1,gb");
            ConnectionManager.getConnection(ConnectionManager.ENDPOINTS.WEATHER_Q,params);
            rDTO = Injector.injectResponseDTO(params.get("url"));
            assertTrue(rDTO.isMainHumidityGreaterThan0AndLessThan100());
        }
    }

    @Nested
    @DisplayName("Not Using the framework")
    class notUsingTheFramework {


        @Test
        @DisplayName("Searching by Zipcode returns the correct zipcode")
        void searchingByZipcodeReturnsTheCorrectZipcode() {
            params.put("zip", "Dh1,gb");
            ConnectionManager.getConnection(ConnectionManager.ENDPOINTS.WEATHER_ZIP, params);
            rDTO = Injector.injectResponseDTO(params.get("url"));
            assertEquals("Shincliffe", rDTO.getName());
        }

        @Test
        @DisplayName("is the Humidity a valid value")
        void isTheHumidityAValidValue() {
            params.put("q","Dh1,gb");
            ConnectionManager.getConnection(ConnectionManager.ENDPOINTS.WEATHER_Q,params);
            rDTO = Injector.injectResponseDTO(params.get("url"));
            assertTrue(rDTO.getMain().getHumidity()<= 100 && rDTO.getMain().getHumidity()>= 0 );
        }

        @Test
        @DisplayName("Check that longitude and latitude are valid")
        void checkThatLongitudeAndLatitudeAreValid() {
            // yay manual connection manager
            HttpRequest req = HttpRequest
                    .newBuilder()
                    .uri(URI.create(ROOT + "/?q=London&appid=" + key)) // uniform resource indicator
                    .build();
            HttpResponse<String> resp = null;
            try {
                resp = client.send(req, HttpResponse.BodyHandlers.ofString());
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            JSONParser jsonParser = new JSONParser();
            try {
                JSONObject obj = (JSONObject) jsonParser.parse(resp.body());
                System.out.println(obj);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }
    
    
    

    @AfterEach
    void tearDown(TestInfo testInfo) {
        System.out.println(testInfo.getDisplayName() + " : END");
    }

    @AfterAll
    static void tearDownAll(TestInfo testInfo) {
        System.out.println("-----------\n" + testInfo.getDisplayName() + " completed");
    }
}
