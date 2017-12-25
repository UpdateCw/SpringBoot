package com.androidmov.adManagement.maker.scheduled;

import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.Random;

/**
 * Created by cw on 2017/10/27. good day.
 *
 * @Author: Chen Wu
 * Blog: http://www.updatecg.xin
 */
@Component
public class AsynTask {
    private static Logger log = Logger.getLogger(AsynTask.class);
    public static Random random =new Random();

    @Async
    public void asynTaskOne() throws InterruptedException {
        log.info(" 任务一开始执行...");
        long start = System.currentTimeMillis();
        Thread.sleep(random.nextInt(10000));
        long end = System.currentTimeMillis();
        log.info("完成任务一，耗时：" + (end - start) + "毫秒");
    }
    @Async
    public void asynTaskTwo() throws InterruptedException {
        log.info(" 任务二开始执行...");
        long start = System.currentTimeMillis();
        Thread.sleep(random.nextInt(10000));
        long end = System.currentTimeMillis();
        log.info("完成任务二，耗时：" + (end - start) + "毫秒");
    }
    @Async
    public void asynTaskThreee() throws InterruptedException {
        log.info(" 任务三开始执行...");
        long start = System.currentTimeMillis();
        Thread.sleep(random.nextInt(10000));
        long end = System.currentTimeMillis();
        log.info("完成任务一，耗时：" + (end - start) + "毫秒");
    }
}
