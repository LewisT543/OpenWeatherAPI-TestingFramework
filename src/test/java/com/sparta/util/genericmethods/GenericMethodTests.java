package com.sparta.util.genericmethods;

import com.sparta.util.GenericMethods;
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
        @Test
        @DisplayName("Given 2 return true")
        void givenAValueAboveZeroReturnTrue() {
            Assertions.assertTrue(GenericMethods.isGreaterThanZero(2.0));
        }

        @Test
        @DisplayName("Given -1 return false")
        void given1ReturnFalse() {
            Assertions.assertFalse(GenericMethods.isGreaterThanZero(-1.0));
        }

        @Test
        @DisplayName("Given 0 return true")
        void given0ReturnTrue() {
            Assertions.assertTrue(GenericMethods.isGreaterThanZero(0.0));
        }

//        @Test
//        @DisplayName("Given Null return false")
//        void givenNullReturnFalse() {
//            Assertions.assertFalse(GenericMethods.isGreaterThanZero(null));
//        }

    }

    @Nested
    @DisplayName("Checking if Double is greater than number")
    class CheckIsGreaterThan {
        @Test
        @DisplayName("Check one is greater than zero")
        void checkOneIsGreaterThanZero() {
            Assertions.assertTrue(GenericMethods.isGreaterThan(Double.valueOf(0), Double.valueOf(1)));
        }

        @Test
        @DisplayName("Check zero is not greater than one")
        void checkZeroIsNotGreaterThanOne() {
            Assertions.assertFalse(GenericMethods.isGreaterThan(Double.valueOf(1), Double.valueOf(0)));
        }

        @Test
        @DisplayName("Check that positive number is greater than negative number")
        void checkThatPositiveNumberIsGreaterThanNegativeNumber() {
            Assertions.assertTrue(GenericMethods.isGreaterThan(Double.valueOf(-1), Double.valueOf(1)));
        }

        @Test
        @DisplayName("Check that negative number is not greater than positive number")
        void checkThatNegativeNumberIsNotGreaterThanPositiveNumber() {
            Assertions.assertFalse(GenericMethods.isGreaterThan(Double.valueOf(1), Double.valueOf(-1)));
        }

        @Test
        @DisplayName("Check that 1.56 is greater than 1.33")
        void checkThat1Point56IsGreaterThan1Point33() {
            Assertions.assertTrue(GenericMethods.isGreaterThan(Double.valueOf(1.33), Double.valueOf(1.56)));
        }

        @Test
        @DisplayName("Check that 1.33 is not greater than 1.56")
        void checkThat1Point33IsNotGreaterThan1Point56() {
            Assertions.assertFalse(GenericMethods.isGreaterThan(Double.valueOf(1.56), Double.valueOf(1.33)));
        }

        @Test
        @DisplayName("Check that NullPointerException is thrown when either parameter is null")
        void checkThatNullPointerExceptionIsThrownWhenEitherParameterIsNull() {
            Assertions.assertThrows(NullPointerException.class, () -> {
                GenericMethods.isGreaterThan(null, Double.valueOf(1));
            });
            Assertions.assertThrows(NullPointerException.class, () -> {
                GenericMethods.isGreaterThan(Double.valueOf(1), null);
            });
            Assertions.assertThrows(NullPointerException.class, () -> {
                GenericMethods.isGreaterThan(null, null);
            });
        }
    }

    @Nested
    @DisplayName("Checking if an object is a double")
    class checkIsADouble {
        @Test
        @DisplayName("Check if Double with value is a Double")
        void checkIfDoubleWithValueIsADouble() {
            Double input = Double.valueOf(0);
            Assertions.assertTrue(GenericMethods.isADouble(input));
        }

        @Test
        @DisplayName("Check if Double without value is a Double")
        void checkIfDoubleWithoutValueIsADouble() {
            Double input = null;
            Assertions.assertFalse(GenericMethods.isADouble(input));
        }

        @Test
        @DisplayName("Check if primitive double is a Double")
        void checkIfPrimitiveDoubleIsADouble() {
            double input = 0;
            Assertions.assertTrue(GenericMethods.isADouble(input));
        }

        @Test
        @DisplayName("Check if primitive int is a Double")
        void checkIfPrimitiveIntIsADouble() {
            int input = 0;
            Assertions.assertFalse(GenericMethods.isADouble(input));
        }

        @Test
        @DisplayName("Check if Object is a Double")
        void checkIfObjectIsADouble() {
            Object input = new Object();
            Assertions.assertFalse(GenericMethods.isADouble(input));
        }

        @Test
        @DisplayName("Check if String is a Double")
        void checkIfStringIsADouble() {
            String input = "sus";
            Assertions.assertFalse(GenericMethods.isADouble(input));
        }

        @Test
        @DisplayName("Check if Double array is a Double")
        void checkIfDoubleArrayIsADouble() {
            Double[] input = new Double[] {};
            Assertions.assertFalse(GenericMethods.isADouble(input));
        }
    }

  
    @Nested
    @DisplayName("isInteger Tests")
    class isIntegerTests{
        Integer aInteger = 7;
        String aString = "A Test";
        Double aDouble = 7.4312;

        @Test
        @DisplayName("Given an integer type object return true")
        void givenIntegerClassReturnTrue(){
            Assertions.assertTrue(GenericMethods.isInteger(aInteger));
        }

        @Test
        @DisplayName("Given null return false")
        void givenNullReturnFalse(){
            Assertions.assertFalse(GenericMethods.isInteger(null));
        }

        @Test
        @DisplayName("Given a string object return false")
        void givenStringObjectReturnFalse(){
            Assertions.assertFalse(GenericMethods.isInteger(aString));
        }

        @Test
        @DisplayName("Given a double object return false")
        void givenDoubleObjectReturnFalse(){
            Assertions.assertFalse(GenericMethods.isInteger(aDouble));
        }

    }

    @Nested
    @DisplayName("isString Tests")
    class isStringTests{
        Integer aInteger = 7;
        String aString = "A Test";
        Double aDouble = 7.4312;

        @Test
        @DisplayName("Given an string type object return true")
        void givenStringObjectReturnTrue(){
            Assertions.assertTrue(GenericMethods.isString(aString));
        }

        @Test
        @DisplayName("Given null return false")
        void givenNullReturnFalse(){
            Assertions.assertFalse(GenericMethods.isString(null));
        }

        @Test
        @DisplayName("Given an integer object return false")
        void givenIntegerObjectReturnFalse(){
            Assertions.assertFalse(GenericMethods.isString(aInteger));
        }

        @Test
        @DisplayName("Given an double object return false")
        void givenDoubleObjectReturnFalse(){
            Assertions.assertFalse(GenericMethods.isString(aDouble));
        }
    }
  
    @AfterEach
    void tearDown (TestInfo testInfo) {
        System.out.println(testInfo.getDisplayName() + " : END");
    }

    @AfterAll
    static void tearDownAll(TestInfo testInfo) {
        System.out.println("-----------\n" + testInfo.getDisplayName() + " completed");
    }

}
