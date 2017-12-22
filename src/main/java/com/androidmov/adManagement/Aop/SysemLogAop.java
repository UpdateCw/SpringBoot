package com.androidmov.adManagement.Aop;

import com.androidmov.adManagement.Aop.annotation.ControllerLogAnnotation;
import com.androidmov.adManagement.Aop.annotation.ServiceLogAnnotation;
import org.aspectj.lang.JoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import com.alibaba.fastjson.JSONObject;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * Created by cw on 2017/12/22. good day.
 *
 * @Author: Chen Wu
 * Blog: http://www.updatecg.xin
 * 系统操作日志AOP
 */
@Component
@Aspect
public class SysemLogAop {

    private static  final Logger log = LoggerFactory.getLogger(SysemLogAop.class);

    @Autowired
    KafkaTemplate kafkaTemplate;

    /**
     * <p>Discription:[后置通知，扫描com.androidmov.adManagement.maker.controllers包及此包下的所有带有controllerLogAnnotation注解的方法]</p>
     * @param joinPoint 前置参数
     * @param controllerLogAnnotation 自定义注解
     */
    @After(("execution(* com.androidmov.adManagement.maker.controllers..*.*(..)) && @annotation(controllerLogAnnotation)"))
    public void doAfterAdviceController(JoinPoint joinPoint, ControllerLogAnnotation controllerLogAnnotation){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
       // HttpSession session = request.getSession();
        //请求IP
        String ip = request.getRemoteAddr();
        try{
            log.info("===========Controller 前置通知开始执行==============");
            log.info("请求方法：" + (joinPoint.getTarget().getClass().getName() + "." +joinPoint.getSignature().getClass().getName() + "()"));
            log.info("方法描述:" + getMethodDescription(joinPoint,true));
            log.info("IP:" + ip);
            String value = controllerLogAnnotation.description();
            kafkaTemplate.send("mylog_topic", "key_controller", value);
        }catch (Exception e){
            //记录本地异常日志
            log.error("==前置通知异常==");
            log.error("异常信息:{}", e.getMessage());
        }
    }

    /**
     * <p>Discription:[后置通知，扫描com.androidmov.adManagement.maker.service包及此包下的所有带有serviceLogAnnotation注解的方法]</p>
     * @param serviceLogAnnotation 自定义注解
     */
    @After(("execution(* com.androidmov.adManagement.maker.service..*.*(..)) && @annotation(serviceLogAnnotation)"))
    public void doAfterAdviceService(JoinPoint joinPoint , ServiceLogAnnotation serviceLogAnnotation){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        // HttpSession session = request.getSession();
        //请求IP
        String ip = request.getRemoteAddr();
        //获取请求方法的参数并序列化为JSON格式字符串
        String params = "";
        if (joinPoint.getArgs() !=  null && joinPoint.getArgs().length > 0) {
            for ( int i = 0; i < joinPoint.getArgs().length; i++) {
                params += JSONObject.toJSON(joinPoint.getArgs()[i]).toString() + ";";
            }
        }
        try{
            log.info("===========Service 前置通知开始执行==============");
            log.info("请求方法：" + (joinPoint.getTarget().getClass().getName() + "." +joinPoint.getSignature().getClass().getName() + "()"));
            log.info("方法描述:" + getMethodDescription(joinPoint,false));
            log.info("请求参数：" + params);
            log.info("IP地址:" + ip);
            String value = serviceLogAnnotation.description();
            kafkaTemplate.send("mylog_topic", "key_service", value);
        }catch (Exception e){
            //记录本地异常日志
            log.error("==前置通知异常==");
            log.error("异常信息:{}", e.getMessage());
        }
    }


    /**
     * 获取注解中对方法的描述信息 用于Controller/Serivce层注解
     *
     * @param joinPoint 切点
     * @return 方法描述
     * @throws Exception
     */
    private String getMethodDescription(JoinPoint joinPoint,boolean isController) throws ClassNotFoundException {
        String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] arguments = joinPoint.getArgs();
        Class targetClass = Class.forName(targetName);
        Method[] methods = targetClass.getMethods();
        String description = "";
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                Class[] clazzs = method.getParameterTypes();
                if (clazzs.length == arguments.length) {
                    if(isController){
                        description = method.getAnnotation(ControllerLogAnnotation.class).description();
                        break;
                    }
                    description = method.getAnnotation(ServiceLogAnnotation.class).description();
                    break;
                }
            }
        }
        return description;
    }



}
