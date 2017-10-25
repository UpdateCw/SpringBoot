package com.androidmov.adManagement.common.entity;

import com.androidmov.adManagement.common.constants.Valid;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by cw on 2017/7/19. good day.
 */
@MappedSuperclass
public class BaseEntity implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    /**
     * 1.有效
     2.无效
     */
    @Column
    private Integer valid;

    @Column
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createdTime;

    @Column
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updatedTime;

    public BaseEntity() {
    }

    public BaseEntity(Long id,Integer valid, Date createdTime, Date updatedTime) {
        this.id=id;
        this.valid = valid;
        this.createdTime = createdTime;
        this.updatedTime = updatedTime;
    }

    public BaseEntity(Integer valid, Date createdTime, Date updatedTime) {
        this.valid = valid;
        this.createdTime = createdTime;
        this.updatedTime = updatedTime;
    }


    /**
     * 设置对象
     */
    public static void setObject(BaseEntity object){
        Date currentTime = new Date();
        if(object.getId()==null){
            object.setValid(Valid.NOT_DELETED);
            object.setCreatedTime(currentTime);
        }
        object.setUpdatedTime(currentTime);
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getValid() {
        return valid;
    }

    public void setValid(Integer valid) {
        this.valid = valid;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Date getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }
}
