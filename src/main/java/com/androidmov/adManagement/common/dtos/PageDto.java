package com.androidmov.adManagement.common.dtos;


import com.androidmov.adManagement.common.constants.ResultCode;
import com.androidmov.adManagement.common.exceptions.ValidException;
import com.androidmov.adManagement.common.utils.JSONUtil;
import com.androidmov.adManagement.common.utils.StringUtil;

/**
 * Created by Administrator on 2017/6/27 0027.
 */
public class PageDto {
    public Long totalCount;
    public Long totalPage;
    public Integer currentPage;
    public Integer pageSize;
    public String order;

    /**
     * 初始化page的JSON串为对象
     * @param page
     * @return
     */
    public static PageDto init(String page){
        if (StringUtil.isEmpty(page)){
            return null;
        }
        try {
            return JSONUtil.toBean(page,PageDto.class);
        } catch (Exception e) {
            throw new ValidException(ResultCode.JSON_CONVERT_ERR);
        }
    }

    public static Integer calcStartLine(Integer currentPage,Integer pageSize){
        return (currentPage-1) * pageSize;
    }

    public void setTotalCount(Long collectionSize){
        this.totalCount = collectionSize;
        this.totalPage = collectionSize < pageSize ? 1:collectionSize/pageSize;
    }

    public Long getTotalCount() {
        return totalCount;
    }

    public Long getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Long totalPage) {
        this.totalPage = totalPage;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage < 1 ? 1 : currentPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }
}
