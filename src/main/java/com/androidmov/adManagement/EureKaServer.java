package com.androidmov.adManagement;

import com.androidmov.adManagement.common.utils.configs.UtilConfig;
import com.androidmov.adManagement.maker.scheduled.SynTask;
import org.apache.commons.configuration.Configuration;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.util.concurrent.Future;

/**
 * Created by cw on 2017/10/25. good day.
 *
 * @Author: Chen Wu
 * Blog: http://www.updatecg.xin
 */
@EnableEurekaServer
@SpringBootApplication
@RestController
@EnableScheduling
@EnableConfigurationProperties
@EnableAsync
public class EureKaServer extends SpringBootServletInitializer implements CommandLineRunner {

    private static Logger log = Logger.getLogger(EureKaServer.class);

    @Autowired
    private SynTask task;

    public static void main(String[] args) {
        createLocalTempDirectory();
        SpringApplication.run(EureKaServer.class, args);
    }

    /**
     * 使用SpringBoot初始化Servlet配置并能够部署至tomcat使项目运行
     * @param builder
     * @return
     */
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(EureKaServer.class);
    }

    /**
     * 依据配置文件创建本地目录
     */
    protected static void createLocalTempDirectory(){
        Configuration cfg = UtilConfig.getApp();
        File tmpFile = new File(cfg.getString("tmpFilePath"));
        tmpFile.mkdirs();
    }

    @Override
    public void run(String... strings) throws Exception {
        long start = System.currentTimeMillis();
        System.out.println("=========Application init============");

        Future<String> futureOne = task.taskOne();
        Future<String> futureTwo = task.taskTwo();
        Future<String> futureThree = task.taskThreee();

        while (true){
            //TODO 三个任务全部执行完成
            if(futureThree.isDone()&&futureOne.isDone()&&futureTwo.isDone()){
                break;
            }
            Thread.sleep(1000);
        }
        long end = System.currentTimeMillis();
        log.info("任务全部完成，总耗时：" + (end - start) + "毫秒");
    }
}
