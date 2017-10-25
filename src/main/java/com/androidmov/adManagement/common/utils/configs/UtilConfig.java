package com.androidmov.adManagement.common.utils.configs;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

/**
 * Created by Administrator on 2017/7/24 0024.
 */
public class UtilConfig {
    private volatile static Configuration appConfig;
    private volatile static Configuration resultCode;

    public static Configuration getApp() {
        if (appConfig == null){
            synchronized (UtilConfig.class){
                if (appConfig == null){
                    try {
                        appConfig = new PropertiesConfiguration(
                                "projectConfigs/appConfig.properties");
                    } catch (ConfigurationException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return appConfig;
    }

    public static Configuration getResult() {
        if (resultCode == null){
            synchronized (UtilConfig.class){
                if (resultCode == null){
                    try {
                        resultCode = new PropertiesConfiguration(
                                "projectConfigs/resultCode.properties");
                    } catch (ConfigurationException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return resultCode;
    }
}
