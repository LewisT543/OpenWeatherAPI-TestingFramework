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

    /**
     * @deprecated dan rewrote his method to implement this method's functionality
     */
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

    public static boolean isInteger(Object object) {
        return object instanceof Integer;
    }

    public static boolean isString(Object object){
        return object instanceof String;
    }
}
