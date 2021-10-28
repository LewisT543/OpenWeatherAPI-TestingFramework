package com.sparta.util;



public class GenericMethods {

    public static boolean isGreaterThanOrEqualToZero(Object input){
        return isGreaterThanOrEqualTo(input,0.0);
    }

    public static boolean isGreaterThanOrEqualTo(Object inputNumber, Double baseNumberForComparison){
        if(inputNumber == null || baseNumberForComparison == null) return false;
        if(inputNumber.getClass() == String.class){
            return false;
        }
        try{
            return (Double) inputNumber >= baseNumberForComparison;
        }catch(ClassCastException ce){
            return(Integer) inputNumber >= baseNumberForComparison;
        }
    }

    public static boolean isGreaterThanOrEqualToZeroOrNull(Object input){
        if(input == null) return true;
        return isGreaterThanOrEqualToZero(input);
    }


}
