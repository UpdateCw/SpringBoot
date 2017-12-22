package com.androidmov.adManagement.maker.controllers;

import com.androidmov.adManagement.Aop.annotation.ControllerLogAnnotation;
import com.androidmov.adManagement.maker.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by cw on 2017/12/19. good day.
 *
 * @Author: Chen Wu
 * Blog: http://www.updatecg.xin
 */
@RestController
    @RequestMapping("/log")
public class LogController {

    @Autowired
    private KafkaTemplate kafkaTemplate;

    @Autowired
    private LogService logService;

    @ControllerLogAnnotation(description = "add log")
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public void add(){
        logService.add();
    }
}
