package com.androidmov.adManagement.common.utils;

import java.io.UnsupportedEncodingException;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Administrator on 2017/6/9 0009.
 */
public class StringUtil {
    public static boolean isEmpty(String... strings){
        for (String str : strings){
            if (str == null || "".equals(str.trim()) || "null".equals(str.trim().toLowerCase())){
                return true;
            }
        }
        return false;
    }

    public static boolean isNotEmpty(String... strings){
        return !isEmpty(strings);
    }

    public static String filte(String item, String symbol) {
        if (isEmpty(item,symbol)){
            return item;
        }
        return item.replace(symbol,"");
    }

    public static String setEcoding(String str , String ecoding){
        if (isEmpty(str,ecoding)){
            return null;
        }
        byte[] bytes = null;
        try {
            bytes = str.getBytes(ecoding);
            str = new String(bytes,ecoding);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return str;
    }

    /**
     * 字符串转码
     * @param userName
     * @param decodeCharset
     * @param encodeCharset
     * @return
     */
    public static String decode(String userName, String decodeCharset, String encodeCharset) {
        String result = null;
        if (isEmpty(userName)){
            return result;
        }
        try {
            result = new String(userName.getBytes(decodeCharset),encodeCharset);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static List<String> Split(String tags, Integer tagSplitCount) {
        if (isEmpty(tags) || tagSplitCount < 1){
            return null;
        }
        int totalLength = tags.length();
        List<String> tagList = new LinkedList<>();
        if (totalLength < tagSplitCount){
            tagList.add(tags);
            return tagList;
        }
        int splitCount = totalLength / tagSplitCount;
        int begain = 0;
        int end = tagSplitCount;

        for (int i = 0 ; i < splitCount ; i++){
            tagList.add(tags.substring(begain,end));
            begain = end;
            end = end + tagSplitCount;
            if (end > totalLength){
                end = totalLength;
            }
        }
        return tagList;
    }

    public static <T extends Collection> String convert2String(T t, String symbol) {
        if (t == null || t.size() <= 0){
            return null;
        }
        Iterator iterator = t.iterator();
        int i = 0;
        StringBuffer sb = new StringBuffer();
        while (iterator.hasNext()){
            if (i <= 0){
                sb.append(iterator.next());
            }else {
                sb.append(symbol).append(iterator.next());
            }
            ++i;
        }
        return sb.toString();
    }
}
