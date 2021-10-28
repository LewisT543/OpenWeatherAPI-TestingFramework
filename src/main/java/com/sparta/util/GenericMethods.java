package com.sparta.util;

public class GenericMethods {

    public static boolean isGreaterThanZero(Double input){
        return input >= 0;
    }

    public static boolean isGreaterThanZeroOrNull(Double input){
        return input == null|| input >= 0;
    }

    public static boolean isGreaterThan(Double num, Double field) {
        return field > num;
    }

    public static boolean isADouble(Object field) {
        if (field.getClass() == Double.class) {
            return true;
        } else {
            return false;
        }
    }


}
