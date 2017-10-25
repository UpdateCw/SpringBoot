package com.androidmov.adManagement.common.exceptions;


import com.androidmov.adManagement.common.utils.Log;
import com.androidmov.adManagement.common.utils.RespUtil;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * Created by Administrator on 2017/6/7 0007.
 */
@ControllerAdvice
public class GlobalExceptionHandle {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Map<String,Object> globalExceptionHandle(Exception ex) throws Exception{
        Log.error("[==localizedMessage: "+ex.getLocalizedMessage()+"==]\n  [==message: "
                +ex.getMessage()+"==]\n[==cause: "+ex.getCause()+" ==]",ex);
        return RespUtil.serverErr(ex.getMessage()+"[cause:"+ex.getCause()+"  "+ex.getLocalizedMessage()+"]");
    }

    @ExceptionHandler(ValidException.class)
    @ResponseBody
    public Map<String,Object> validExceptionHandle(ValidException vex) throws Exception{
        Log.record("code:"+vex.getCode()+"   msg:"+vex.getMessage());
        return RespUtil.result(vex.getCode(),vex.getMessage());
    }
}
