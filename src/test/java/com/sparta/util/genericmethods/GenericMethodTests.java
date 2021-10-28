package com.sparta.util.genericmethods;

import com.sparta.util.GenericMethods;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class GenericMethodTests {

    @Nested
    @DisplayName("IsGreaterThanZeroTests")
    class isGreaterThanZeroTests {
        @Test
        @DisplayName("Given 2 return true")
        void givenAValueAboveZeroReturnTrue() {
            assertTrue(GenericMethods.isGreaterThanZero(2.0));
        }

        @Test
        @DisplayName("Given -1 return false")
        void given1ReturnFalse() {
            assertFalse(GenericMethods.isGreaterThanZero(-1.0));
        }

        @Test
        @DisplayName("Given 0 return true")
        void given0ReturnTrue() {
            assertTrue(GenericMethods.isGreaterThanZero(0.0));
        }

//        @Test
//        @DisplayName("Given Null return false")
//        void givenNullReturnFalse() {
//            Assertions.assertFalse(GenericMethods.isGreaterThanZero(null));
//        }

    }

    @Nested
    @DisplayName("Load city list tests")
    class loadCityListTests {

        @Test
        @DisplayName("Returns valid jsonnode")
        void returnsValidJsonnode() {
            assertTrue(GenericMethods.loadCityList() != null);
        }

        @ParameterizedTest
        @MethodSource("cityIDTestValues")
        @DisplayName("CityID Returns true if ID is correct")
        void CityIDReturnsTrueIfIDisCorrect(String name, int ID) {
            assertTrue(GenericMethods.cityIdIsCorrect(name, ID));
        }

        public static Stream<Arguments> cityIDTestValues() {
            return Stream.of(
                    Arguments.arguments("Ḩeşār-e Sefīd", 833),
                    Arguments.arguments("Gniebitz", 2919664),
                    Arguments.arguments("Gerton", 102908597)
            );
        }

        @Test
        @DisplayName("CityID Returns false if Lat is correct")
        void CityIDReturnsFalseIfLonIsIncorrect() {
            assertFalse(GenericMethods.cityIdIsCorrect("Gerton", 0));
        }

        @Test
        @DisplayName("CityID Returns false if City is correct")
        void CityIDReturnsFalseIfCityIsIncorrect() {
            assertFalse(GenericMethods.cityIdIsCorrect("Incorrect", 102908597));
        }

        @ParameterizedTest
        @MethodSource("cityLonTestValues")
        @DisplayName("CityLon returns true if Lon is correct")
        void CityLatReturnsTrueIfLonIsCorrect(String name, double lon) {
            assertTrue(GenericMethods.cityLonIsCorrect(name, lon));
        }

        public static Stream<Arguments> cityLonTestValues() {
            return Stream.of(
                    Arguments.arguments("Ḩeşār-e Sefīd", 47.159401),
                    Arguments.arguments("Gniebitz", 12.78333),
                    Arguments.arguments("Gerton", -82.348056)
            );
        }

        @Test
        @DisplayName("CityLon Returns false if Lat is correct")
        void CityLonReturnsFalseIfLonIsIncorrect() {
            assertFalse(GenericMethods.cityLonIsCorrect("Gerton", 0));
        }

        @Test
        @DisplayName("CityLon Returns false if City is correct")
        void CityLonReturnsFalseIfCityIsIncorrect() {
            assertFalse(GenericMethods.cityLonIsCorrect("Incorrect", -82.348056));
        }

        @ParameterizedTest
        @MethodSource("cityLatTestValues")
        @DisplayName("CityLat returns true if Lat is correct")
        void CityLatReturnsTrueIfLatIsCorrect(String name, double lat) {
            assertTrue(GenericMethods.cityLatIsCorrect(name, lat));
        }

        public static Stream<Arguments> cityLatTestValues() {
            return Stream.of(
                    Arguments.arguments("Ḩeşār-e Sefīd", 34.330502),
                    Arguments.arguments("Gniebitz", 51.616669),
                    Arguments.arguments("Gerton", 35.479167)
            );
        }

        @Test
        @DisplayName("CityLat Returns false if Lat is correct")
        void cityLatReturnsFalseIfLatIsIncorrect() {
            assertFalse(GenericMethods.cityLatIsCorrect("Gerton", 0));
        }

        @Test
        @DisplayName("CityLat Returns false if City is correct")
        void cityLatReturnsFalseIfCityIsIncorrect() {
            assertFalse(GenericMethods.cityLatIsCorrect("Incorrect", 35.479167));
        }

    }

}
