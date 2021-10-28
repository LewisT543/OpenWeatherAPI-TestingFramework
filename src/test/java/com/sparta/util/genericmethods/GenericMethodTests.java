package com.sparta.util.genericmethods;

import com.sparta.util.GenericMethods;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class GenericMethodTests {

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


}
