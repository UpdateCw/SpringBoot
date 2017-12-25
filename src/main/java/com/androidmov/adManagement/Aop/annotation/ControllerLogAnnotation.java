package com.androidmov.adManagement.Aop.annotation;

import java.lang.annotation.*;

/**
 * Created by cw on 2017/12/19. good day.
 *
 * @Author: Chen Wu
 * Blog: http://www.updatecg.xin
 * 自定义ControllerLog注解
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ControllerLogAnnotation {

    String description() default "";

}