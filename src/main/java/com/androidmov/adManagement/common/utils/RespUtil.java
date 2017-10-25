package com.androidmov.adManagement.common.utils;

import com.androidmov.adManagement.common.constants.GlobalResultType;
import com.androidmov.adManagement.common.constants.ResultCode;
import com.androidmov.adManagement.common.utils.configs.UtilConfig;

import java.io.UnsupportedEncodingException;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/6/7 0007.
 */
public class RespUtil {
    private static Map<String,Object> buildResult(final Integer code, String message,
                                                  final Map<String,Object> data){
        Map<String,Object> results = new LinkedHashMap<>();
        results.put(GlobalResultType.CODE,code);
        results.put(GlobalResultType.MSG,message);
        if (data == null || data.isEmpty()){
            return results;
        }else {
            results.putAll(data);
            return results;
        }
    }

    public static Map<String,Object> success(final String message){
        return buildResult(ResultCode.NORMAL,message,null);
    }

    public static Map<String,Object> success(final Object obj){
        Map<String,Object> data = new LinkedHashMap<>();
        data.put("data",obj);
        return buildResult(ResultCode.NORMAL,"success",data);
    }

    public static Map<String,Object> success(final String message,final Map<String,Object> details){
        return buildResult(ResultCode.NORMAL,message,details);
    }

    public static Map<String,Object> fails(final String message){
        return buildResult(ResultCode.FAIL,message,null);
    }

    public static Map<String,Object> fails(final String message,final Map<String,Object> details){
        return buildResult(ResultCode.FAIL,message,details);
    }

    public static Map<String,Object> result(final Integer code,final String message){
        return buildResult(code,message,null);
    }

    public static Map<String,Object> result(final Integer code){
        String message = UtilConfig.getResult().getString(String.valueOf(code));
        return buildResult(code,message,null);
    }

    public static Map<String,Object> result(final Integer code,final String message,
                                            final Map<String,Object> details){
        return buildResult(code,message,details);
    }

    public static Map<String,Object> serverErr(final String message){
        return buildResult(ResultCode.SERVER_ERR,message,null);
    }

    public static Map<String, Object> emptyParam(){
        return buildResult(ResultCode.EMPTY_PARAM,"Empty param",null);
    }
}
