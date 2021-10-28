package com.sparta.util;

public class GenericMethods {

    public static boolean isGreaterThanZero(Double input){
        return input >= 0;
    }

    public static boolean isGreaterThanZeroOrNull(Double input){
            return input == null|| input >= 0;
    }


}
