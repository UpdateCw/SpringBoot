package com.androidmov.adManagement.common.utils;

/**
 * Created by LLH
 * on 2017-08-21 11:34.
 */
public class NumberUtil {

    public static <T extends Number> boolean isNotEmpty(T... numbers){
        if (numbers == null || numbers.length <= 0){
            return false;
        }
        for (T number : numbers){
            if (number == null || number.doubleValue() <= 0){
                return false;
            }
        }
        return true;
    }

    public static <T extends Number> boolean isEmpty(T... numbers){
        return !isNotEmpty(numbers);
    }

    /*public static void main(String[] args) {
        System.out.println(NumberUtil.isEmpty(0.1));
    }*/
}
