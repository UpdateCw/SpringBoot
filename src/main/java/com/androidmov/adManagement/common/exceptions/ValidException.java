package com.androidmov.adManagement.common.exceptions;

import com.androidmov.adManagement.common.constants.ResultCode;
import com.androidmov.adManagement.common.utils.Log;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;

/**
 * Created by Administrator on 2017/6/22 0022.
 */
public class ValidException extends RuntimeException {

    private Integer code;
    private String message;
    private static volatile Configuration CFG;

    /**
     * Constructs a new runtime exception with {@code null} as its
     * detail message.  The cause is not initialized, and may subsequently be
     * initialized by a call to {@link #initCause}.
     */
    public ValidException() {
        super();
    }

    /**
     * Constructs a new runtime exception with the specified detail message.
     * The cause is not initialized, and may subsequently be initialized by a
     * call to {@link #initCause}.
     *
     * @param message the detail message. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method.
     */
    public ValidException(String message) {
        super(message);
    }

    public ValidException(Integer code) {
        try {
            if (CFG == null){
                synchronized (ValidException.class){
                    if (CFG == null){
                        CFG = new PropertiesConfiguration("projectConfigs/resultCode.properties");
                    }
                }
            }
        } catch (ConfigurationException e) {
            throw new GlobalException("CFG init fail");
        }
        this.code = code;
        try {
            this.message = new String((CFG.getString(String.valueOf(code))==null?"null":CFG.getString(String.valueOf(code)))
                    .getBytes("ISO-8859-1"),"UTF8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public ValidException(Integer code, Class<? extends Object> clazz, String methodName,Class<?>... parameterTypes) {
        this.code = code;
        try {
            Method method = clazz.getMethod(methodName,parameterTypes);
            this.message = clazz.getSimpleName()+" "+method.getName();
            if (code.equals(ResultCode.EMPTY_PARAM)){
                this.message += " param is empty";
            }
            if (code.equals(ResultCode.EMPTY_RESULT)){
                String fullMethodName = method.getGenericReturnType().toString();
                String simpMethodName = fullMethodName.substring(fullMethodName.lastIndexOf("."));
                this.message += simpMethodName+" is empty";
            }
        } catch (NoSuchMethodException e) {
            Log.error(e.getMessage());
        }
    }

    public ValidException(Integer code, Class<? extends Object> clazz, String methodName) {
        this.code = code;

        this.message = clazz.getSimpleName() + " " + methodName;
        if (code.equals(ResultCode.EMPTY_PARAM)) {
            this.message += " param is empty";
        }
        if (code.equals(ResultCode.EMPTY_RESULT)) {
            this.message += methodName + " result is empty";
        }
    }

    @Override
    public String getMessage(){
        return message;
    }

    public Integer getCode(){
        return code;
    }
}
