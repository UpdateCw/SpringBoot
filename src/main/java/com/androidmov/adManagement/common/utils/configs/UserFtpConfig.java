package com.androidmov.adManagement.common.utils.configs;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import javax.validation.constraints.NotNull;

/**
 * Created by LLH
 * on 2017-09-18 15:28.
 */
@Configuration
@ConfigurationProperties(prefix = "user.ftp")
@PropertySource("classpath:projectConfigs/appConfig.properties")
public class UserFtpConfig {
    @NotNull
    private String host;
    @NotNull
    private Integer port;
    @NotNull
    private String userName;
    @NotNull
    private String password;
    @NotNull
    private String encoding;
    @NotNull
    private String rootDir;
    @NotNull
    private String remotePath;
    @NotNull
    private String userFileName;
    @NotNull
    private String groupFileName;
    @NotNull
    private String areaCode;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEncoding() {
        return encoding;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    public String getRootDir() {
        return rootDir;
    }

    public void setRootDir(String rootDir) {
        this.rootDir = rootDir;
    }

    public String getRemotePath() {
        return remotePath;
    }

    public void setRemotePath(String remotePath) {
        this.remotePath = remotePath;
    }

    public String getUserFileName() {
        return userFileName;
    }

    public void setUserFileName(String userFileName) {
        this.userFileName = userFileName;
    }

    public String getGroupFileName() {
        return groupFileName;
    }

    public void setGroupFileName(String groupFileName) {
        this.groupFileName = groupFileName;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }
}
