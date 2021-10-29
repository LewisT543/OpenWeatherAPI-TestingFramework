package com.sparta.examples;

import com.sparta.connection.ConnectionManager;
import com.sparta.dto.ResponseDTO;
import com.sparta.injector.Injector;
import org.junit.jupiter.api.*;

import java.io.IOException;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

public class ExampleTests {

    private static ConnectionManager connectionManager;
    private static HashMap<String, String> params;
    private static ResponseDTO rDTO;

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

//        @Test
//        @DisplayName("when a language is specified the weather description should be in that language") // needs a helper method, but im pretty sure this would be incredibly difficult to actually do.
//        void whenALanguageIsSpecifiedTheWeatherDescriptionShouldBeInThatLanguage() {
//            params.put("q","Barcelona");
//            params.put("lang","es");
//            ConnectionManager.getConnection(ConnectionManager.ENDPOINTS.WEATHER,params);
//            rDTO = Injector.injectResponseDTO(params.get("url"));
//            assertTrue(true);
//        }

//        @Test
//        @DisplayName("call data in a given unit of measurement") // again difficult to determine as how do we know if the response is in each of the units
//        void callDataInAGivenUnitOfMeasurement() {
//            params.put("q", "Edinburgh");
//            params.put("units","imperial");
//            ConnectionManager.getConnection(ConnectionManager.ENDPOINTS.WEATHER,params);
//            rDTO = Injector.injectResponseDTO(params.get("url"));
//            assertTrue(true);
//        }

        @Test
        @DisplayName("Searching by Zipcode returns the correct zipcode")
        void searchingByZipcodeReturnsTheCorrectZipcode() {
            params.put("q", "Dh1,gb"); // strange that you can replace the q here with zip
            ConnectionManager.getConnection(ConnectionManager.ENDPOINTS.WEATHER,params);
            rDTO = Injector.injectResponseDTO(params.get("url"));
            assertEquals("GB",rDTO.getSys().getCountry());
        }




    }

    @Nested
    @DisplayName("Not Using the framework")
    class notUsingTheFramework {
        @Test
        @DisplayName("when a language is specified the weather description should be in that language")
        void whenALanguageIsSpecifiedTheWeatherDescriptionShouldBeInThatLanguage() {
            
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
