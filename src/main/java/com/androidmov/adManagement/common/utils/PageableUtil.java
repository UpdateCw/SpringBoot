package com.androidmov.adManagement.common.utils;

import com.androidmov.adManagement.common.dtos.PageDto;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by LLH
 * on 2017-08-24 10:39.
 */
public class PageableUtil {

    public static Pageable build(PageDto pageDto){
        if (StringUtil.isEmpty(pageDto.order)){
            pageDto.order = "createdTime DESC";
        }
        String[] sortList = pageDto.order.split(",");
        Sort sort = buildSort(Arrays.asList(sortList));
        int currentPage = pageDto.currentPage;
        if (currentPage < 1){
            currentPage = 0;
        }else {
            currentPage -= 1;
        }
        return new PageRequest(currentPage,pageDto.pageSize,sort);
    }

    private static Sort buildSort(List<String> orderList){
        List<Sort.Order> orders = new LinkedList<>();
        String[] orderArr = null;
        for (String orderStr : orderList){
            orderArr = orderStr.split(" ");
            if (orderArr.length == 1){
                orders.add(buildOrder(orderArr[0],"asc"));
            }else if (orderArr.length == 2){
                orders.add(buildOrder(orderArr[0],orderArr[1]));
            }else {
                continue;
            }
        }
        return new Sort(orders);
    }

    private static Sort.Order buildOrder(String property,String direction){
        Sort.Direction directionInt = convertDirection(direction);
        return new Sort.Order(directionInt,property);
    }

    private static Sort.Direction convertDirection(String direction){
        if ("asc".equals(direction.trim().toLowerCase())){
            return Sort.Direction.ASC;
        }else if ("desc".equals(direction.trim().toLowerCase())){
            return Sort.Direction.DESC;
        }else {
            throw new RuntimeException("err direction string");
        }
    }
}
