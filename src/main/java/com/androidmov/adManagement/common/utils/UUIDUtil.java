package com.androidmov.adManagement.common.utils;

import java.util.UUID;

/**
 * Created by Administrator on 2017/6/7 0007.
 */
public class UUIDUtil {
    public static String getUUID(){
        return UUID.randomUUID().toString();
    }

    public static boolean isUUID(String uuid){
        try {
            UUID.fromString(uuid);
        }catch (Exception ex){
            return false;
        }
        return true;
    }


}
