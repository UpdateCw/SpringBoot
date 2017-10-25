package com.androidmov.adManagement.common.utils;

/**
 * Created by Administrator on 2017/6/23 0023.
 */
public class ExceptionMaker {
    public static String build(Integer code,String message){
        return String.valueOf(code)+"$"+message;
    }
}
