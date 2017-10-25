package com.androidmov.adManagement.common.utils;

import com.androidmov.adManagement.common.constants.ResultCode;
import com.androidmov.adManagement.common.exceptions.ValidException;
import com.androidmov.adManagement.common.utils.configs.UtilConfig;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/6/26 0026.
 */
public class TokenUtil {

    /**
     * 通过token获取UserID
     * @param token
     * @return
     */
    public static String getUserID(String token){
        return token;
    }

    /**
     * 根据用户ID获取用户名
     * @param userID
     * @return
     */
    public static String getUserName(String userID){
        return userID;
    }

    /**
     * 根据token获取该用户所在部门或公司
     * @param token
     * @return
     */
    public static String getDept(String token) {
        getUserID(token);
        return "cd";
    }

    /**
     * 获取用户信息
     * @param userIds
     * @return
     */
    public static Map<String,String> getUserIDs(List<String> userIds) {
        /*Map<String,String> resultMap = new LinkedHashMap<>();
        resultMap.put("9dd21736-c34c-48b4-be5c-463b2002efb4","录入员1");
        resultMap.put("28666041-a111-4188-99c4-1801bb8f2e58","审核员1");
        return resultMap;*/
        String url = UtilConfig.getApp().getString("userInfoServerUrl");
        MultiValueMap<String,String> params = new LinkedMultiValueMap<>();
        params.put("ids",userIds);
        RestTemplate restTemplate = new RestTemplate();
        String response = null;
        try {
            response = restTemplate.postForObject(url,params,String.class);
        }catch (Exception ex){
            throw new ValidException(ResultCode.UNCONNECT_SERVER);
        }

        if (StringUtil.isEmpty(response)){
            return null;
        }
        // 将JSON转换成对象并获取关键值组成Map并返回;
        Map<String,String> userIDInfoMap = new LinkedHashMap<>();
        try {
            Map<String,String> resultMap = JSONUtil.toBean(response,Map.class);
            List<Map<String,String>> resultList = JSONUtil.toBean(JSONUtil.toJson(resultMap.get("data")),List.class);
            String userId = null;
            String userName = null;
            if (CollectionUtil.isNotEmpty(resultList)){
                for (Map<String,String> tmpMap : resultList){
                    userId = String.valueOf(tmpMap.get("userId"));
                    userName = tmpMap.get("userName");
                    Log.debug("userId:"+userId+"\nuserName:"+userName);
                    userIDInfoMap.put(userId,userName);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userIDInfoMap;
    }
}
