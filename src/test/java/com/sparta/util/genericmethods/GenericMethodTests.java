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
            Assertions.assertTrue(GenericMethods.isGreaterThanOrEqualTo(2.0,0.0));
        }

        @Test
        @DisplayName("given 0.0 and 2.0 return false")
        void givenD0AndD2ReturnFalse() {
            Assertions.assertFalse(GenericMethods.isGreaterThanOrEqualTo(0.0,2.0));
        }

        @Test
        @DisplayName("given 2 and null return false")
        void given2AndNullReturnFalse() {
            Assertions.assertFalse(GenericMethods.isGreaterThanOrEqualTo(2,null));
        }

        @Test
        @DisplayName("given null and 2 return false")
        void givenNullAnd2ReturnFalse() {
            Assertions.assertFalse(GenericMethods.isGreaterThanOrEqualTo(null,2.0));
        }

        @Test
        @DisplayName("given 2 and 0.0 return true")
        void given2AndD0ReturnTrue() {
            Assertions.assertTrue(GenericMethods.isGreaterThanOrEqualTo(2,0.0));
        }
        @Test
        @DisplayName("given 0 and 2.0 return False")
        void given0AndD2ReturnFalse() {
            Assertions.assertFalse(GenericMethods.isGreaterThanOrEqualTo(0,2.0));
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


}
