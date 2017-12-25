package com.androidmov.adManagement.Aop.annotation;
import java.lang.annotation.*;

/**
 * Created by cw on 2017/12/19. good day.
 *
 * @Author: Chen Wu
 * Blog: http://www.updatecg.xin
 * 定义拦截ServiceLog注解
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ServiceLogAnnotation {
    String description() default "";
}