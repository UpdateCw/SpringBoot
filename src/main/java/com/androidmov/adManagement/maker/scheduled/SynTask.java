package com.androidmov.adManagement.maker.scheduled;

import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.concurrent.Future;

/**
 * Created by cw on 2017/10/27. good day.
 *
 * @Author: Chen Wu
 * Blog: http://www.updatecg.xin
 */
@Component
public class SynTask {
    private static Logger log = Logger.getLogger(SynTask.class);
    public static Random random =new Random();

    @Async
    public Future<String> taskOne() throws InterruptedException {
        log.info("任务一开始执行...");
        long start = System.currentTimeMillis();
        Thread.sleep(random.nextInt(10000));
        long end = System.currentTimeMillis();
        log.info("完成任务一，耗时：" + (end - start) + "毫秒");
        return new AsyncResult<>("任务一完成");
    }

    @Async
    public  Future<String> taskTwo() throws InterruptedException {
        log.info("任务二开始执行...");
        long start = System.currentTimeMillis();
        Thread.sleep(random.nextInt(10000));
        long end = System.currentTimeMillis();
        log.info("完成任务二，耗时：" + (end - start) + "毫秒");
        return new AsyncResult<>("任务二完成");
    }
    @Async
    public  Future<String> taskThreee() throws InterruptedException {
        log.info("任务三开始执行...");
        long start = System.currentTimeMillis();
        Thread.sleep(random.nextInt(10000));
        long end = System.currentTimeMillis();
        log.info("完成任务三，耗时：" + (end - start) + "毫秒");
        return new AsyncResult<>("任务三完成");
    }
}
