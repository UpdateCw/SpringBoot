package com.androidmov.adManagement.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Administrator on 2017/6/15 0015.
 */
public final class Log {
    private static final Logger err = LoggerFactory.getLogger("error");
    private static final Logger info = LoggerFactory.getLogger("info");
    private static final Logger debug = LoggerFactory.getLogger("debug");
    private static final Logger record = LoggerFactory.getLogger("record");

    public static void info(String message){
        info.info(message);
    }

    public static void error(String message){
        StringBuilder sb = new StringBuilder(message + ":\n");
        err.error(sb.toString());
    }

    public static void error(String message,Exception ex){
        StringBuffer sb = new StringBuffer(message + ":\n");
        StackTraceElement[] stes = ex.getStackTrace().clone();
        if (stes != null){
            for (StackTraceElement ste : stes){
                sb.append("  ").append(ste.toString()).append("\n");
            }
        }
        err.error(sb.toString());
    }

    public static void debug(String message){
        debug.debug(message);
    }

    public static void record(String message){
        record.debug(message);
    }
}
