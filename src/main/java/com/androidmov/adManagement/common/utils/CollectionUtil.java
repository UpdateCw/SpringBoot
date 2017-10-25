package com.androidmov.adManagement.common.utils;

import com.androidmov.adManagement.common.entity.BaseEntity;

import java.util.*;

/**
 * Created by Administrator on 2017/6/8 0008.
 */
public class CollectionUtil {
    public static <T extends Collection> boolean isEmpty(T t){
        if (t == null || t.isEmpty()){
            return true;
        }else {
            return false;
        }
    }

    public static <T extends Collection> boolean isNotEmpty(T t){
        return !isEmpty(t);
    }

    public static <T extends Map> boolean isEmpty(T t){
        if (t == null || t.isEmpty()){
            return true;
        }else {
            return false;
        }
    }

    public static <T extends Map> boolean isNotEmpty(T t){
        return !isEmpty(t);
    }

    public static <T extends Object,X extends BaseEntity> Map<T,X> toMapById(Collection<X> collection){
        if (isEmpty(collection)){
            return null;
        }
        Map<T,X> map = new LinkedHashMap<>();
        for (X obj : collection){
            map.put((T)obj.getId(),obj);
        }
        return map;
    }

    /*public static void main(String[] args) {
        List<RsDsRel> aa = new LinkedList<>();
        RsDsRel obj = new RsDsRel();
        obj.setId(3L);
        obj.setRs(28L);
        obj.setDs(98L);
        aa.add(obj);
        Map<Long,RsDsRel> map = CollectionUtil.toMapById(aa);
        System.out.println(map);
    }*/
}
