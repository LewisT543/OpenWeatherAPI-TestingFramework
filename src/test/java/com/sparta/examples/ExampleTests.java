package com.sparta.examples;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.connection.ConnectionManager;
import com.sparta.dto.ResponseDTO;
import com.sparta.injector.Injector;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.*;

import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Random;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;

public class ExampleTests {

    private static ConnectionManager connectionManager;
    private static HashMap<String, String> params;
    private static ResponseDTO rDTO;
    private static final String ROOT = "https://api.openweathermap.org/data/2.5/weather";
    static HttpClient client = HttpClient.newHttpClient();

    @BeforeAll
    static void initAll(TestInfo testInfo) {
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
            params.put("zip", "Dh1,gb"); // strange that you can replace the q here with zip
            ConnectionManager.getConnection(ConnectionManager.ENDPOINTS.WEATHER_ZIP,params);
            rDTO = Injector.injectResponseDTO(params.get("url"));
            assertEquals("GB", rDTO.getSys().getCountry());
        }
    }

    @Nested
    @DisplayName("Not Using the framework")
    class notUsingTheFramework {

        @Test
        @DisplayName("Check that longitude and latitude are valid")
        void checkThatLongitudeAndLatitudeAreValid() {
            // yay manual connection manager
            HttpRequest req = HttpRequest
                    .newBuilder()
                    .uri(URI.create("http://shibe.online/api/shibes")) // uniform resource indicator
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
