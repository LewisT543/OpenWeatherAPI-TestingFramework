package com.sparta.examples;

import com.sparta.connection.ConnectionManager;
import com.sparta.dto.ResponseDTO;
import com.sparta.injector.Injector;
import com.sparta.util.Util;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.*;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

public class ExampleTests {

    //------------------------------------------------------
    // Set-up with framework
    //------------------------------------------------------
    private static ConnectionManager connectionManager;
    private static HashMap<String, String> params;
    private static ResponseDTO rDTO;


    //------------------------------------------------------
    // Set-up without framework
    //------------------------------------------------------
    private static final String ROOT = "https://api.openweathermap.org/data/2.5/weather";
    static HttpClient client = HttpClient.newHttpClient();
    private static String key;




    //--------------------------------------------------------------------
    //    Example area -
    //--------------------------------------------------------------------




    @Nested
    @DisplayName("Examples")
    class examples {

        @BeforeAll
        static void initAll(TestInfo testInfo) {
            connectionManager = new ConnectionManager();
            params = new HashMap<>();
        }

        @BeforeEach
        void init(TestInfo testInfo) {
            ConnectionManager.resetParams();
        }

        @Test
        @DisplayName("is pressure above or equal to 0")
        void isPressureAboveOrEqualToZero() {
            params.put("q","London");
            ConnectionManager.getConnection(ConnectionManager.ENDPOINTS.WEATHER_Q,params);
            rDTO = Injector.injectResponseDTO(params.get("url"));
            assertTrue(rDTO.isPressureGreaterOrEqualToZero());
        }


    }




    //--------------------------------------------------------------------
    //    Using the Framework
    //--------------------------------------------------------------------
    @Nested
    @DisplayName("UsingTheFramework")
    class usingTheFramework {

        @BeforeAll
        static void initAll(TestInfo testInfo) {
            connectionManager = new ConnectionManager();
            params = new HashMap<>();
            System.out.println(testInfo.getDisplayName() + " starting\n-----------");
        }

        @BeforeEach
        void init(TestInfo testInfo) {
            System.out.println(testInfo.getDisplayName() + " : START");
            ConnectionManager.resetParams();
        }

        @Test
        @DisplayName("Searching by Zipcode returns the correct zipcode")
        void searchingByZipcodeReturnsTheCorrectZipcode() {
            params.put("zip", "dh1,gb");
            ConnectionManager.getConnection(ConnectionManager.ENDPOINTS.WEATHER_ZIP,params);
            rDTO = Injector.injectResponseDTO(params.get("url"));
            assertEquals("Shincliffe", rDTO.getName());
        }

        @Test
        @DisplayName("is the humidity valid")
        void isTheHumidityValidValue() {
            params.put("q","London,GB");
            ConnectionManager.getConnection(ConnectionManager.ENDPOINTS.WEATHER_Q,params);
            rDTO = Injector.injectResponseDTO(params.get("url"));
            assertTrue(rDTO.isMainHumidityGreaterThan0AndLessThan100());
        }

        @Test
        @DisplayName("Check that longitude and latitude of London (UK) is valid")
        void checkThatLongitudeAndLatitudeOfLondonUkIsValid() {
            params.put("q", "London,GB");
            ConnectionManager.getConnection(ConnectionManager.ENDPOINTS.WEATHER_Q,params);
            rDTO = Injector.injectResponseDTO(params.get("url"));
            assumeTrue(rDTO.isCoordValid());
        }
    }

    //--------------------------------------------------------------------
    //    Not Using the Framework
    //--------------------------------------------------------------------
    @Nested
    @DisplayName("Not Using the framework")
    class notUsingTheFramework {

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
            System.out.println(testInfo.getDisplayName() + " starting\n-----------");
        }

        @BeforeEach
        void init(TestInfo testInfo) {
            System.out.println(testInfo.getDisplayName() + " : START");
        }

        @Test
        @DisplayName("Searching by Zipcode returns the correct zipcode")
        void searchingByZipcodeReturnsTheCorrectZipcode() {
            HttpRequest req = HttpRequest
                    .newBuilder()
                    .uri(URI.create(ROOT + "/?zip=dh1,gb&appid=" + key)) // uniform resource indicator
                    .build();
            HttpResponse<String> resp = null;
            try {
                resp = client.send(req, HttpResponse.BodyHandlers.ofString());
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }

            JSONParser jsonParser = new JSONParser();
            try {
                JSONObject obj = (JSONObject) jsonParser.parse(resp.body());
                assertEquals("Shincliffe", obj.get("name"));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        @Test
        @DisplayName("is the Humidity a valid value")
        void isTheHumidityAValidValue() {
            HttpRequest req = HttpRequest
                    .newBuilder()
                    .uri(URI.create(ROOT + "/?q=London,GB&appid=" + key)) // uniform resource indicator
                    .build();
            HttpResponse<String> resp = null;
            try {
                resp = client.send(req, HttpResponse.BodyHandlers.ofString());
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }

            JSONParser jsonParser = new JSONParser();
            try {
                JSONObject obj = (JSONObject) jsonParser.parse(resp.body());
                Long humidLong = (Long) ((JSONObject) obj.get("main")).get("humidity");
                assertTrue(humidLong >= 0 && humidLong <= 100);
            } catch (ParseException e) {
                e.printStackTrace();
            }

        }

        @Test
        @DisplayName("Check that longitude and latitude of London (UK) is valid")
        void checkThatLongitudeAndLatitudeOfLondonUKIsValid() {
            HttpRequest req = HttpRequest
                    .newBuilder()
                    .uri(URI.create(ROOT + "/?q=London,GB&appid=" + key)) // uniform resource indicator
                    .build();
            HttpResponse<String> resp = null;
            try {
                resp = client.send(req, HttpResponse.BodyHandlers.ofString());
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }

            Double longitude = null;
            Double latitude = null;
            JSONParser jsonParser = new JSONParser();
            try {
                JSONObject respObj = (JSONObject) jsonParser.parse(resp.body());
                JSONObject coord = (JSONObject) respObj.get("coord");
                longitude = (Double) coord.get("lon");
                latitude = (Double) coord.get("lat");
            } catch (ParseException e) {
                e.printStackTrace();
            }

            assertTrue(longitude.getClass() == Double.class &&
                    latitude.getClass() == Double.class &&
                    longitude > -180 &&
                    longitude < 180 &&
                    latitude > -90 &&
                    latitude < 90);
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
