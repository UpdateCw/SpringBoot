package com.androidmov.adManagement.common.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Created by Administrator on 2017/6/7 0007.
 */
public class JSONUtil {

    public static String toJson(Object bean) throws JsonProcessingException {
        if (bean == null){
            return null;
        }
        return new ObjectMapper().writeValueAsString(bean);
    }

    public static <T> T toBean(String json,Class<T> item) {
        T t = null;
        try {
            t = new ObjectMapper().readValue(json,item);
        }catch (Exception e){}
        return t;
    }

    /*public static void main(String[] args) throws Exception {
        String json = "[{\"advertisement\":{\"id\":21,\"valid\":1,\"createdTime\":\"2017-09-01 09:48:34\",\"updatedTime\":\"2017-09-01 09:48:34\",\"type\":\"2\",\"code\":102,\"version\":1,\"name\":\"我是文字\",\"content\":\"\",\"description\":\"我是文字\",\"typeName\":\"文字\"},\"showtime\":12,\"width\":231,\"height\":23,\"type\":2,\"url\":\"www.baidu.com\",\"time\":1,\"epgUrl\":\"www.baidu.com\",\"showTime\":12,\"isFocus\":true},{\"advertisement\":{\"id\":20,\"valid\":1,\"createdTime\":\"2017-09-01 09:45:09\",\"updatedTime\":\"2017-09-01 09:49:12\",\"type\":\"1\",\"code\":101,\"version\":1,\"name\":\"我是图片广告是\",\"content\":\"\",\"description\":\"我是图片广告\",\"typeName\":\"图片\"},\"showtime\":12,\"width\":12,\"height\":31,\"type\":1,\"url\":\"\",\"time\":0,\"epgUrl\":\"www.bais.com\",\"showTime\":12,\"isFocus\":false}]";
        PageDto pageDto = new PageDto();
        ObjectMapper objectMapper = new ObjectMapper();
        JavaType javaType = objectMapper.getTypeFactory().constructParametricType(ArrayList.class,AdgAdRel.class);
        List<AdgAdRel> adgAdRels = objectMapper.readValue(json,javaType);

        for (AdgAdRel adgAdRel : adgAdRels){
            System.out.println(adgAdRel.getUrl());
        }
    }*/
}