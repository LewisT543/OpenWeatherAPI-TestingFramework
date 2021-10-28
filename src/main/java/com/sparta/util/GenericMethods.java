package com.sparta.util;

public class GenericMethods {

    public static boolean isGreaterThanZero(Double input){
        return input >= 0;
    }

    public static boolean isGreaterThanZeroOrNull(Double input){
        return input == null|| input >= 0;
    }

    @Deprecated
    public static boolean isGreaterThan(Double num, Double field) {
        if (num == null || field == null) {
            throw new NullPointerException("bro why'd you give me a null value. why'd you do this to me man. " +
                    "i trusted you bro. how could you");
        }
        return field > num;
    }

    public static boolean isADouble(Object field) {
        if(field instanceof Double) {
        //if (field.getClass() == Double.class) {
            return true;
        } else {
            return false;
        }
    }


}
