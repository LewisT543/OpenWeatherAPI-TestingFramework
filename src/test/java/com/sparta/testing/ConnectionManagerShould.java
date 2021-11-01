package com.sparta.testing;

import com.sparta.connection.ConnectionManager;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;

public class ConnectionManagerShould {
    private static HashMap<String, String> params;

    @BeforeAll
    @DisplayName("Connection manager testing started")
    static void setup() {
        ConnectionManager connectionManager = new ConnectionManager();
        params = new HashMap<>();
    }

    @Nested
    public class HeaderShould {
        @BeforeEach
        void init() {
            params.put("q", "London");
        }

        @Test
        void GivenGoodCall_StatusCodeIs2XX() {
            ConnectionManager.getConnection(ConnectionManager.ENDPOINTS.WEATHER_Q, params);
            int statusCode = Integer.parseInt(ConnectionManager.getHeadersAndStatusCode().get("status_code"));
            assertTrue( statusCode > 199 && statusCode < 300);
        }

        @Test
        void GivenCall_HeaderContentTypeIsJSON() {
            ConnectionManager.getConnection(ConnectionManager.ENDPOINTS.WEATHER_Q, params);
            assertTrue(ConnectionManager.getHeadersAndStatusCode()
                    .get("content-type").contains("application/json;"));
        }

        @Test
        void GivenCall_HeaderCacheKeyContainsQueryParam() {
            ConnectionManager.getConnection(ConnectionManager.ENDPOINTS.WEATHER_Q, params);
            assertTrue(ConnectionManager.getHeadersAndStatusCode()
                    .get("x-cache-key").contains("q=london"));
        }

        @Test
        void GivenCall_HeaderContentTypeCharSetIsUTF8() {
            ConnectionManager.getConnection(ConnectionManager.ENDPOINTS.WEATHER_Q, params);
            assertTrue(ConnectionManager.getHeadersAndStatusCode()
                    .get("content-type").contains("charset=utf-8"));
        }

        @Test
        void GivenCall_HeaderShouldNotBeNull() {
            ConnectionManager.getConnection(ConnectionManager.ENDPOINTS.WEATHER_Q, params);
            assertNotNull(ConnectionManager.getHeadersAndStatusCode());
        }

        @Test
        void GivenCall_HeaderShouldNotBeEmpty() {
            ConnectionManager.getConnection(ConnectionManager.ENDPOINTS.WEATHER_Q, params);
            assertFalse(ConnectionManager.getHeadersAndStatusCode().isEmpty());
        }

        @Test
        void GivenStatusCode_ShouldNotBeNull() {
            ConnectionManager.getConnection(ConnectionManager.ENDPOINTS.WEATHER_Q, params);
            assertNotNull(ConnectionManager.getHeadersAndStatusCode().get("status_code"));
        }

        @Test
        @DisplayName("Test to see if 200 http status code is returned")
        void getStatusCode() {
            ConnectionManager.getConnection(ConnectionManager.ENDPOINTS.WEATHER_Q, params);
            assertEquals(200, Integer.parseInt(ConnectionManager.getHeadersAndStatusCode().get("status_code")));
        }

        @AfterEach
        void teardown() {
            ConnectionManager.resetParams();
        }
    }

    @Nested
    @DisplayName("find endpoint testing")
    public class FindShould {

        @BeforeEach
        void init() {
            params.put("lat", "55.356");
            params.put("lon", "-17.81");
            params.put("units", "standard");
        }

        @AfterEach
        void teardown() {
            ConnectionManager.resetParams();
        }

        @Test
        @DisplayName("check if error is thrown if lat and lon is not in hashmap")
        void checkIfErrorIsThrownIfLatAndLonAreNotInHashMap() {
            HashMap<String, String> params = new HashMap<>();
            assertThrows(IllegalArgumentException.class, () ->
                    ConnectionManager.getConnection(ConnectionManager.ENDPOINTS.FIND,params));
        }

        @Test
        @DisplayName("check to see if it returns the url correctly")
        void checkThatCorrectUrlIsReturnedCorrectly() {
            var response = ConnectionManager.getConnection(ConnectionManager.ENDPOINTS.FIND, params);
            assertTrue(response.get("url").contains("lat=55.356") && response.get("url").contains("lon=-17.81"));

        }

        @Test
        void GivenLatNotInParams_ShouldThrowIllegalArgumentException() {
            params.remove("lat");
            assertThrows(IllegalArgumentException.class, ()-> {
                ConnectionManager.getConnection(ConnectionManager.ENDPOINTS.FIND, params).get("url");
            });
        }

        @Test
        void GivenLonNotInParams_ShouldThrowIllegalArgumentException() {
            params.remove("lon");
            assertThrows(IllegalArgumentException.class, ()-> {
                ConnectionManager.getConnection(ConnectionManager.ENDPOINTS.FIND, params);
            });
        }

        @Test
        void GivenBadCNT_ShouldThrowIllegalArgumentException() {
            params.put("cnt", "ww");
            assertThrows(IllegalArgumentException.class, ()-> {
                ConnectionManager.getConnection(ConnectionManager.ENDPOINTS.FIND, params);
            });
        }

        @Test
        void GivenNullCNT_ShouldThrowIllegalArgumentException() {
            params.put("cnt", null);
            assertThrows(IllegalArgumentException.class, ()-> {
                ConnectionManager.getConnection(ConnectionManager.ENDPOINTS.FIND, params);
            });
        }

        @Test
        void GivenCNT_ShouldReturnCNTInURL() {
            params.put("cnt", "10");

            var response = ConnectionManager.getConnection(ConnectionManager.ENDPOINTS.FIND, params);
            assertTrue(response.get("url").contains("cnt=10"));
        }

        @Test
        void GivenZeroCNT_ShouldThrowIllegalArgumentException() {
            params.put("cnt", "0");
            assertThrows(IllegalArgumentException.class, ()-> {
                ConnectionManager.getConnection(ConnectionManager.ENDPOINTS.FIND, params);
            });
        }

        @Test
        void GivenNegativeCNT_ShouldThrowIllegalArgumentException() {
            params.put("cnt", "-51");
            assertThrows(IllegalArgumentException.class, ()-> {
                ConnectionManager.getConnection(ConnectionManager.ENDPOINTS.FIND, params);
            });
        }

        @Test
        void Given51CNT_ShouldThrowIllegalArgumentException() {
            params.put("cnt", null);
            assertThrows(IllegalArgumentException.class, ()-> {
                ConnectionManager.getConnection(ConnectionManager.ENDPOINTS.FIND, params);
            });
        }

        @Test
        void Given38CNT_ShouldReturnCNTInURL() {
            params.put("cnt", "38");

            var response = ConnectionManager.getConnection(ConnectionManager.ENDPOINTS.FIND, params);
            assertTrue(response.get("url").contains("cnt=38"));
        }

        @Test
        void GivenLang_ShouldReturnURLWithLang() {
            params.put("lang", "en");

            var connResponse = ConnectionManager.getConnection(ConnectionManager.ENDPOINTS.FIND, params);
            assertTrue(connResponse.get("url").contains("lang=en"));
        }

        @Test
        void GivenWrongUnitFormat_ShouldThrowIllegalArgumentException() {
            params.put("units", "freedom-units");

            assertThrows(IllegalArgumentException.class, () ->
                    ConnectionManager.getConnection(ConnectionManager.ENDPOINTS.FIND, params));
        }

        @Test
        void GivenImperialUnit_ShouldReturnURLWithUnit() {
            params.put("units", "imperial");

            var connResponse = ConnectionManager.getConnection(ConnectionManager.ENDPOINTS.FIND, params);
            assertTrue((connResponse.get("url").contains("units=imperial")));
        }

        @Test
        void GivenMetricUnit_ShouldReturnURLWithUnit() {
            params.put("units", "metric");

            var connResponse = ConnectionManager.getConnection(ConnectionManager.ENDPOINTS.FIND, params);
            assertTrue((connResponse.get("url").contains("units=metric")));
        }

        @Test
        void GivenNoUnit_ShouldReturnStandard() {
            params.remove("units");

            var connResponse = ConnectionManager.getConnection(ConnectionManager.ENDPOINTS.FIND, params);
            assertTrue( (connResponse.get("url")).contains("units=standard"));
        }


        @Test
        void GivenXMLMode_ShouldRevertToJSON() {
            params.put("mode", "xml");

            var connResponse = ConnectionManager.getConnection(ConnectionManager.ENDPOINTS.FIND, params);
            assertTrue( (connResponse.get("url")).contains("mode=json"));
        }

        @Test
        void GivenHTMLMode_ShouldRevertToJSON() {
            params.put("mode", "html");

            var connResponse = ConnectionManager.getConnection(ConnectionManager.ENDPOINTS.FIND, params);
            assertTrue((connResponse.get("url")).contains("mode=json"));
        }

        @Test
        void GivenJSONMode_ShouldStayAsJSON() {
            params.put("mode", "json");

            var connResponse = ConnectionManager.getConnection(ConnectionManager.ENDPOINTS.FIND, params);
            assertTrue((connResponse.get("url")).contains("mode=json"));
        }

    }

    @Nested
    @DisplayName("box endpoint testing")
    class BoxShould {
        private static HashMap<String, String> params = new HashMap<>();

        @BeforeEach
        void init() {
            params.put("bbox", "12,34,22,12,56");
            params.put("units", "standard");
        }

        @Test
        void GivenLang_ShouldReturnURLWithLang() {
            params.put("lang", "en");

            var connResponse = ConnectionManager.getConnection(ConnectionManager.ENDPOINTS.BOX, params);
            assertTrue((connResponse.get("url")).contains("lang=en"));
        }

        @Test
        void GivenWrongBBoxFormat_ShouldThrowIllegalArgumentException() {
            params.put("bbox", "12,22,12,56");

            assertThrows(IllegalArgumentException.class, () ->
                    ConnectionManager.getConnection(ConnectionManager.ENDPOINTS.BOX, params));
        }

        @Test
        void GivenWrongUnitFormat_ShouldThrowIllegalArgumentException() {
            params.put("units", "freedom-units");

            assertThrows(IllegalArgumentException.class, () ->
                    ConnectionManager.getConnection(ConnectionManager.ENDPOINTS.BOX, params));
        }

        @Test
        void GivenImperialUnit_ShouldReturnURLWithUnit() {
            params.put("units", "imperial");

            var connResponse = ConnectionManager.getConnection(ConnectionManager.ENDPOINTS.BOX, params);
            assertTrue((connResponse.get("url")).contains("units=imperial"));
        }

        @Test
        void GivenMetricUnit_ShouldReturnURLWithUnit() {
            params.put("units", "metric");

            var connResponse = ConnectionManager.getConnection(ConnectionManager.ENDPOINTS.BOX, params);
            assertTrue((connResponse.get("url")).contains("units=metric"));
        }

        @Test
        void GivenNoUnit_ShouldReturnStandard() {
            params.remove("units");

            var connResponse = ConnectionManager.getConnection(ConnectionManager.ENDPOINTS.BOX, params);
            assertTrue((connResponse.get("url")).contains("units=standard"));
        }


        @Test
        void GivenXMLMode_ShouldRevertToJSON() {
            params.put("mode", "xml");

            var connResponse = ConnectionManager.getConnection(ConnectionManager.ENDPOINTS.BOX, params);
            assertTrue((connResponse.get("url")).contains("mode=json"));
        }

        @Test
        void GivenHTMLMode_ShouldRevertToJSON() {
            params.put("mode", "html");

            var connResponse = ConnectionManager.getConnection(ConnectionManager.ENDPOINTS.BOX, params);
            assertTrue((connResponse.get("url")).contains("mode=json"));
        }

        @Test
        void GivenJSONMode_ShouldStayAsJSON() {
            params.put("mode", "json");

            var connResponse = ConnectionManager.getConnection(ConnectionManager.ENDPOINTS.BOX, params);
            assertTrue((connResponse.get("url")).contains("mode=json"));
        }
    }

    @Nested
    @DisplayName("weather endpoint testing")
    public class WeatherShould {

        @Test
        void GivenValidCity_ShouldReturnURL() {
            HashMap<String, String> params = new HashMap<>();
            params.put("q", "london");
            var connResponse = ConnectionManager.getConnection(ConnectionManager.ENDPOINTS.WEATHER_Q, params);
            assertFalse((connResponse.get("url")).isEmpty());
        }

        @Test
        void GivenValidCity_ShouldHaveCityInURL() {
            HashMap<String, String> params = new HashMap<>();
            params.put("q", "london");
            var connResponse = ConnectionManager.getConnection(ConnectionManager.ENDPOINTS.WEATHER_Q, params);
            assertTrue((connResponse.get("url")).contains("q=london"));
        }

        @Test
        void GivenNoQParam_ShouldThrowIllegalArgumentException() {
            HashMap<String, String> params = new HashMap<>();
            assertThrows(IllegalArgumentException.class, () -> {
                ConnectionManager.getConnection(ConnectionManager.ENDPOINTS.WEATHER_Q, params);
            });
        }

        @Test
        void GivenNullQParam_ShouldThrowIllegalArgumentException() {
            HashMap<String, String> params = new HashMap<>();
            params.put("q", null);
            assertThrows(IllegalArgumentException.class, () -> {
                ConnectionManager.getConnection(ConnectionManager.ENDPOINTS.WEATHER_Q, params);
            });
        }

        @Test
        void GivenXMLMode_ShouldRevertToJSON() {
            HashMap<String, String> params = new HashMap<>();
            params.put("q", "london");
            params.put("mode", "xml");

            var connResponse = ConnectionManager.getConnection(ConnectionManager.ENDPOINTS.WEATHER_Q, params);
            assertTrue((connResponse.get("url")).contains("mode=json"));
        }

        @Test
        void GivenHTMLMode_ShouldRevertToJSON() {
            HashMap<String, String> params = new HashMap<>();
            params.put("q", "london");
            params.put("mode", "html");

            var connResponse = ConnectionManager.getConnection(ConnectionManager.ENDPOINTS.WEATHER_Q, params);
            assertTrue((connResponse.get("url")).contains("mode=json"));
        }

        @Test
        void GivenJSONMode_ShouldStayAsJSON() {
            HashMap<String, String> params = new HashMap<>();
            params.put("q", "london");
            params.put("mode", "json");

            var connResponse = ConnectionManager.getConnection(ConnectionManager.ENDPOINTS.WEATHER_Q, params);
            assertTrue((connResponse.get("url")).contains("mode=json"));
        }
    }

    @AfterAll
    @DisplayName("Connection manager tested finished")
    static void teardownAll(TestInfo testInfo) {
        System.out.println(testInfo.getDisplayName() + " tests finished");
    }
}
