package com.androidmov.adManagement.common.utils;

import java.lang.reflect.Field;

import com.androidmov.adManagement.common.constants.ResultCode;
import com.androidmov.adManagement.common.exceptions.ValidException;
import org.springframework.data.domain.*;

/**
 * Created by cw on 2017/7/20. good day.
 * 分页参数封装
 */
public class RequestPackageUtils {
    /**
     * jpa封装参数匹配器
     * 反序列化对象 动态创建参数
     * @param object
     */
    public static Example  exampleMatcher(Object object){
        Field[] declaredFields = object.getClass().getDeclaredFields();
        ExampleMatcher matcher = ExampleMatcher.matching();
        for (Field field : declaredFields) {
            //因对象属性访问权限限制，需获取权限
             field.setAccessible(true);
            try {
                if(field.get(object)!=null){
                    matcher= ExampleMatcher.matching()
                            .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)//字符串匹配方式：模糊查询
                            .withMatcher(""+field.getName()+"", ExampleMatcher.GenericPropertyMatchers.contains());//根据参数进行匹配
                }
            } catch (IllegalAccessException e) {
                throw new ValidException(ResultCode.EMPTY_PARAM);
            }
        }
        return Example.of(object,matcher);
    }
}