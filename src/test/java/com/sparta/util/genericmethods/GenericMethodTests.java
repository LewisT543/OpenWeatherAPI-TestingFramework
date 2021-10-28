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

    @AfterEach
    void tearDown (TestInfo testInfo) {
        System.out.println(testInfo.getDisplayName() + " : END");
    }

    @AfterAll
    static void tearDownAll(TestInfo testInfo) {
        System.out.println("-----------\n" + testInfo.getDisplayName() + " completed");
    }

}
