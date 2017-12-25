package com.androidmov.adManagement.maker.service.impl;

import com.androidmov.adManagement.Aop.annotation.ServiceLogAnnotation;
import com.androidmov.adManagement.maker.service.LogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Created by cw on 2017/12/21. good day.
 *
 * @Author: Chen Wu
 * Blog: http://www.updatecg.xin
 */
@Service
public class LogServiceImpl implements LogService {

    private static  final Logger log = LoggerFactory.getLogger(LogServiceImpl.class);

    @ServiceLogAnnotation(description = "add log")
    @Override
    public void add() {
        log.info("add log success !");
    }
}
