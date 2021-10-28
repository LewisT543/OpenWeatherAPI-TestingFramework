package com.sparta.util.genericmethods;

import com.sparta.util.GenericMethods;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.fail;


public class GenericMethodTests {

    @BeforeAll
    static void initAll(TestInfo testInfo) {
        System.out.println(testInfo.getDisplayName() + " starting\n-----------");
    }

    @BeforeEach
    void init(TestInfo testInfo) {
        System.out.println(testInfo.getDisplayName() + " : START");
    }

    @Nested
    @DisplayName("IsGreaterThanZeroTests")
    class isGreaterThanZeroTests {

        @DisplayName("Given 2.0 return true")
        void given2ReturnTrue() {
            Assertions.assertTrue(GenericMethods.isGreaterThanOrEqualToZero(2.0));
        }

        @Test
        @DisplayName("Given -1.0 return false")
        void givenMinus1ReturnFalse() {
            Assertions.assertFalse(GenericMethods.isGreaterThanOrEqualToZero(-1.0));
        }

        @Test
        @DisplayName("Given 0.0 return true")
        void given0ReturnTrue() {
            Assertions.assertTrue(GenericMethods.isGreaterThanOrEqualToZero(0.0));
        }

        @Test
        @DisplayName("Given Null return false")
        void givenNullReturnFalse() {
            Assertions.assertFalse(GenericMethods.isGreaterThanOrEqualToZero(null));
        }

        @Test
        @DisplayName("Given 1 as an integer return true")
        void given1AsAnIntegerReturnTrue() {
            Assertions.assertTrue(GenericMethods.isGreaterThanOrEqualToZero(1));
        }

        @Test
        @DisplayName("Given minus 1 as an integer return false")
        void givenMinus1AsAnIntegerReturnFalse() {
            Assertions.assertFalse(GenericMethods.isGreaterThanOrEqualToZero(-1));
        }

        @Test
        @DisplayName("Given a String return false")
        void givenAStringReturnFalse() {
            Assertions.assertFalse(GenericMethods.isGreaterThanOrEqualToZero("Testing"));
        }

        @Test
        @DisplayName("given a string with a integer in it return false")
        void givenAStringWithAIntegerInItReturnFalse() {
            Assertions.assertFalse(GenericMethods.isGreaterThanOrEqualToZero("1"));
        }

        @Test
        @DisplayName("given a string with a double in return false")
        void givenAStringWithADoubleInReturnFalse() {
            Assertions.assertFalse(GenericMethods.isGreaterThanOrEqualToZero("1.0"));
        }

        @Test
        @DisplayName("given a large Double return true")
        void givenALargeDoubleReturnTrue() {
            Assertions.assertTrue(GenericMethods.isGreaterThanOrEqualToZero(1000000000000000000000000000000000000.0));
        }

        @Test
        @DisplayName("given a large Integer return true")
        void givenALargeIntegerReturnTrue() {
            Assertions.assertTrue(GenericMethods.isGreaterThanOrEqualToZero(2137999999));
        }
    }

    @Nested
    @DisplayName("IsGreaterThanOrEqualToTests")
    class isGreaterThanOrEqualToTests {

        @Test
        @DisplayName("comparing 2.0 and 0.0 return true")
        void givenAD2AndAD0() {
            Assertions.assertTrue(GenericMethods.isGreaterThanOrEqualTo(2.0, 0.0));
        }

        @Test
        @DisplayName("given 0.0 and 2.0 return false")
        void givenD0AndD2ReturnFalse() {
            Assertions.assertFalse(GenericMethods.isGreaterThanOrEqualTo(0.0, 2.0));
        }

        @Test
        @DisplayName("given 2 and null return false")
        void given2AndNullReturnFalse() {
            Assertions.assertFalse(GenericMethods.isGreaterThanOrEqualTo(2, null));
        }

        @Test
        @DisplayName("given null and 2 return false")
        void givenNullAnd2ReturnFalse() {
            Assertions.assertFalse(GenericMethods.isGreaterThanOrEqualTo(null, 2.0));
        }

        @Test
        @DisplayName("given 2 and 0.0 return true")
        void given2AndD0ReturnTrue() {
            Assertions.assertTrue(GenericMethods.isGreaterThanOrEqualTo(2, 0.0));
        }

        @Test
        @DisplayName("given 0 and 2.0 return False")
        void given0AndD2ReturnFalse() {
            Assertions.assertFalse(GenericMethods.isGreaterThanOrEqualTo(0, 2.0));

        }

    }

    @Nested
    @DisplayName("Is Greater Than Or Equal To 0 or Null")
    class isGreaterThanOrEqualTo0OrNull {
        @Test
        @DisplayName("Given Null return true")
        void givenNullReturnTrue() {
            Assertions.assertTrue(GenericMethods.isGreaterThanOrEqualToZeroOrNull(null));
        }

        @Test
        @DisplayName("Given 0.0 return true")
        void given0ReturnTrue() {
            Assertions.assertTrue(GenericMethods.isGreaterThanOrEqualToZeroOrNull(0.0));
        }
    }


    @Nested
    @DisplayName("Checking if an object is a double")
    class checkIsADouble {
        @Test
        @DisplayName("Check if Double with value is a Double")
        void checkIfDoubleWithValueIsADouble() {
            Double input = Double.valueOf(0);
            Assertions.assertTrue(GenericMethods.isDouble(input));
        }

        @Test
        @DisplayName("Check if Double without value is a Double")
        void checkIfDoubleWithoutValueIsADouble() {
            Double input = null;
            Assertions.assertFalse(GenericMethods.isDouble(input));
        }

        @Test
        @DisplayName("Check if primitive double is a Double")
        void checkIfPrimitiveDoubleIsADouble() {
            double input = 0;
            Assertions.assertTrue(GenericMethods.isDouble(input));
        }

        @Test
        @DisplayName("Check if primitive int is a Double")
        void checkIfPrimitiveIntIsADouble() {
            int input = 0;
            Assertions.assertFalse(GenericMethods.isDouble(input));
        }

        @Test
        @DisplayName("Check if Object is a Double")
        void checkIfObjectIsADouble() {
            Object input = new Object();
            Assertions.assertFalse(GenericMethods.isDouble(input));
        }

        @Test
        @DisplayName("Check if String is a Double")
        void checkIfStringIsADouble() {
            String input = "sus";
            Assertions.assertFalse(GenericMethods.isDouble(input));
        }

        @Test
        @DisplayName("Check if Double array is a Double")
        void checkIfDoubleArrayIsADouble() {
            Double[] input = new Double[]{};
            Assertions.assertFalse(GenericMethods.isDouble(input));
        }
    }

    @Nested
    @DisplayName("CityIDTests")
    class cityIdTests {


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

    @Nested
    @DisplayName("isString Tests")
    class isStringTests {
        private char aChar = 'a';
        private Integer aInteger = 7;
        private String aString = "A Test";
        private Double aDouble = 7.4312;

        @Test
        @DisplayName("Given an string type object return true")
        void givenStringObjectReturnTrue() {
            Assertions.assertTrue(GenericMethods.isString(aString));
        }

        @Test
        @DisplayName("Given null return false")
        void givenNullReturnFalse() {
            Assertions.assertFalse(GenericMethods.isString(null));
        }

        @Test
        @DisplayName("Given a char return false")
        void givenCharReturnFalse() {
            Assertions.assertFalse(GenericMethods.isString(aChar));
        }


        @Test
        @DisplayName("Given an integer object return false")
        void givenIntegerObjectReturnFalse() {
            Assertions.assertFalse(GenericMethods.isString(aInteger));
        }

        @Test
        @DisplayName("Given an double object return false")
        void givenDoubleObjectReturnFalse() {
            Assertions.assertFalse(GenericMethods.isString(aDouble));
        }
    }

    @Nested
    @DisplayName("isInteger Tests")
    class isIntegerTests {
        private int anInt = 5;
        private Integer aInteger = 7;
        private String aString = "A Test";
        private Double aDouble = 7.4312;


        @Test
        @DisplayName("Given an integer type object return true")
        void givenIntegerClassReturnTrue() {
            Assertions.assertTrue(GenericMethods.isInteger(aInteger));
        }

        @Test
        @DisplayName("Given an integer primitive type object return true")
        void givenIntegerPrimitiveReturnTrue() {
            Assertions.assertTrue(GenericMethods.isInteger(anInt));
        }

        @Test
        @DisplayName("Given null return false")
        void givenNullReturnFalse() {
            Assertions.assertFalse(GenericMethods.isInteger(null));
        }

        @Test
        @DisplayName("Given a string object return false")
        void givenStringObjectReturnFalse() {
            Assertions.assertFalse(GenericMethods.isInteger(aString));
        }

        @Test
        @DisplayName("Given a double object return false")
        void givenDoubleObjectReturnFalse() {
            Assertions.assertFalse(GenericMethods.isInteger(aDouble));
        }
    }


    @Nested
    @DisplayName("isLong Tests")
    class isLongTests{
        private Long aLong = 3456789L;
        private int anInt = 5;
        private Integer aInteger = 7;
        private String aString = "A Test";
        private Double aDouble = 7.4312;


        @Test
        @DisplayName("Given an integer type object return true")
        void givenIntegerClassReturnFalse() {
            Assertions.assertFalse(GenericMethods.isLong(aInteger));
        }

        @Test
        @DisplayName("Given a long type object return true")
        void givenLongClassReturnTrue(){
            Assertions.assertTrue(GenericMethods.isLong(aLong));
        }

        @Test
        @DisplayName("Given an integer primitive type object return true")
        void givenIntegerPrimitiveReturnFalse(){
            Assertions.assertFalse(GenericMethods.isLong(anInt));
        }

        @Test
        @DisplayName("Given null return false")
        void givenNullReturnFalse() {
            Assertions.assertFalse(GenericMethods.isLong(null));
        }

        @Test
        @DisplayName("Given a string object return false")
        void givenStringObjectReturnFalse() {
            Assertions.assertFalse(GenericMethods.isLong(aString));
        }

        @Test
        @DisplayName("Given a double object return false")
        void givenDoubleObjectReturnFalse() {
            Assertions.assertFalse(GenericMethods.isLong(aDouble));
        }
    }


    @Nested
    @DisplayName("IsNotNull tests")
    class IsNotNull {
        @Test
        @DisplayName("Given any String value, return true")
        void givenStringValueReturnTrue() {
            String testValue = "Hello";
            Assertions.assertTrue(GenericMethods.isNotNull(testValue));
        }

        @Test
        @DisplayName("Given any Integer value, return true")
        void givenIntegerValueReturnTrue() {
            Integer testValue = 1123;
            Assertions.assertTrue(GenericMethods.isNotNull(testValue));
        }

        @Test
        @DisplayName("Given any Double value, return true")
        void givenDoubleValueReturnTrue() {
            Double testValue = 1123.021;
            Assertions.assertTrue(GenericMethods.isNotNull(testValue));
        }

        @Test
        @DisplayName("Given no value, return false")
        void givenNoValueReturnFalse() {
            String testValue = null;
            Assertions.assertFalse(GenericMethods.isNotNull(testValue));
        }
    }

    @Nested
    @DisplayName("BetweenXAndY tests")
    class BetweenXAndY {
        @Test
        @DisplayName("Given value=0, x=0, y=100, return true")
        void given0And0And100ReturnTrue() {
            double testValue = 0;
            Assertions.assertTrue(GenericMethods.isBetweenXAndY(testValue, 0.0, 100.0));
        }

        @Test
        @DisplayName("Given value=0, x=0, y=100, return true")
        void given100And0And100ReturnTrue() {
            double testValue = 100;
            Assertions.assertTrue(GenericMethods.isBetweenXAndY(testValue, 0.0, 100.0));
        }

        @Test
        @DisplayName("Given value=50, x=0, y=100, return true")
        void given50And0And100ReturnTrue() {
            Double testValue = 50.0;
            Assertions.assertTrue(GenericMethods.isBetweenXAndY(testValue, 0.0, 100.0));
        }

        @Test
        @DisplayName("Given value=-12, x=0, y=100, return false")
        void givenMinus12And0And100ReturnTrue() {
            Double testValue = -12.0;
            Assertions.assertFalse(GenericMethods.isBetweenXAndY(testValue, 0.0, 100.0));
        }

        @Test
        @DisplayName("Given value=-null, x=0, y=100, return false")
        void givenNullAnd0And100ReturnTrue() {
            Double testValue = null;
            Assertions.assertFalse(GenericMethods.isBetweenXAndY(testValue, 0.0, 100.0));
        }

        @Test
        @DisplayName("Given value=-null, x=0, y=100, return false")
        void given0AndNullAnd100ReturnTrue() {
            Double testValue = 0.0;
            Assertions.assertFalse(GenericMethods.isBetweenXAndY(testValue, null, 100.0));
        }

        @Test
        @DisplayName("Given value=-null, x=0, y=100, return false")
        void given0And0AndNullReturnTrue() {
            Double testValue = 0.0;
            Assertions.assertFalse(GenericMethods.isBetweenXAndY(testValue, 0.0, null));
        }
    }

    @Nested
    @DisplayName("Epoch tests")
    class EpochTests {
        @Test
        @DisplayName("Given an epoch return true if valid date")
        void givenEpochReturnTrueIfDateValid() {
            Assertions.assertTrue(GenericMethods.epochDateIsValid(System.currentTimeMillis()/1000));
        }

        @DisplayName("Given an epoch return false if data is not valid")
        void givenEpochReturnFalseIfDataIsNotValid() {
            Assertions.assertFalse(GenericMethods.epochDateIsValid(1726572947L));
        }


        @Test
        @DisplayName("Given an epoch return true if it is 10 digits")
        void givenEpochReturnTrueIfTenDigits() {
            Assertions.assertTrue(GenericMethods.epochIsTenDigits(1026572947L));
        }

        @Test
        @DisplayName("Given an epoch return false if it is not 10 digits")
        void givenEpochReturnFalseIfNotTenDigits() {
            Assertions.assertFalse(GenericMethods.epochIsTenDigits(172947L));
        }


        @Test
        @DisplayName("Given two epochs return true if same day")
        void givenTwoEpochsReturnTrueIfSameDay() {
            Assertions.assertTrue(GenericMethods.getDateComparison(1635403619L,1635439282L));
        }

        @Test
        @DisplayName("Given two epochs return false if different day")
        void givenTwoEpochsReturnFalseIfDifferentDay() {
            Assertions.assertFalse(GenericMethods.getDateComparison(1635403619L,1026572947L));
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

