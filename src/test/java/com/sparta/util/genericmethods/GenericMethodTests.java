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


}
