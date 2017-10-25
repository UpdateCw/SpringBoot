package com.androidmov.adManagement.common.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Administrator on 2017/7/24 0024.
 */
public class MD5Util {
    public static String make(byte[] bytes){
        if (bytes == null){
            return null;
        }
        byte[] md5Bytes = null;
        try {
            md5Bytes = MessageDigest.getInstance("MD5").digest();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return new BigInteger(1,md5Bytes).toString();
    }
}
