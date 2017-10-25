package com.androidmov.adManagement.common.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2017/6/7 0007.
 */
public class DateUtil {
    public static final String Default = "yyyy-MM-dd HH:mm:ss";

    public static String formatDate(Date date){
        return new SimpleDateFormat(Default).format(date);
    }
}
