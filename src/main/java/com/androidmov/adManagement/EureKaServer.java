package com.androidmov.adManagement;

import com.androidmov.adManagement.common.utils.configs.UtilConfig;
import org.apache.commons.configuration.Configuration;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;

/**
 * Created by Administrator on 2017/6/7 0007.
 */


@EnableEurekaServer
@SpringBootApplication
@RestController
@EnableScheduling
@EnableConfigurationProperties
public class EureKaServer extends SpringBootServletInitializer implements CommandLineRunner {

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
        System.out.println("=========Application init============");
    }
}
