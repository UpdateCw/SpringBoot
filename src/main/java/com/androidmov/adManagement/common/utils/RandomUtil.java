package com.androidmov.adManagement.common.utils;

/**
 * Created by Administrator on 2017/6/30 0030.
 */
public class RandomUtil {
    /**
     * 获取特定位数的随机数
     * @param howMany
     * @return
     */
    public static String getRandomNum(int howMany) {
        if (howMany < 1){
            throw new RuntimeException("getRandomNum param is < 1");
        }
        String nano = String.valueOf(System.nanoTime());
        return nano.substring(nano.length()-howMany);
    }
}
