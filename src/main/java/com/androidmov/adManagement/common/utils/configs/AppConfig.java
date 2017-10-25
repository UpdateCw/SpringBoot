package com.androidmov.adManagement.common.utils.configs;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.validation.constraints.NotNull;

/**
 * Created by Administrator on 2017/6/30 0030.
 */
@Configuration
@ConfigurationProperties
@PropertySource("classpath:projectConfigs/appConfig.properties")
public class AppConfig {
    @NotNull
    private String tmpFilePath;
    @NotNull
    private String doorPreviewAddress;
    @NotNull
    private String ftpSourceAddress;
    @NotNull
    private String sourceAddress;
    @NotNull
    private String doorSourceAddress;
    @NotNull
    private String ftpServerHost;
    @NotNull
    private String ftpServerPort;
    @NotNull
    private String ftpUserName;
    @NotNull
    private String ftpPassword;
    @NotNull
    private String ftpEncoding;
    @NotNull
    private String rootWorkingDir;

    public String getDoorPreviewAddress() {
        return doorPreviewAddress;
    }
    public void setDoorPreviewAddress(String doorPreviewAddress) {
        this.doorPreviewAddress = doorPreviewAddress;
    }

    public String getFtpSourceAddress() {
        return ftpSourceAddress;
    }

    public void setFtpSourceAddress(String ftpSourceAddress) {
        this.ftpSourceAddress = ftpSourceAddress;
    }

    public String getSourceAddress() {
        return sourceAddress;
    }

    public void setSourceAddress(String sourceAddress) {
        this.sourceAddress = sourceAddress;
    }

    public String getDoorSourceAddress() {
        return doorSourceAddress;
    }

    public void setDoorSourceAddress(String doorSourceAddress) {
        this.doorSourceAddress = doorSourceAddress;
    }

    public String getFtpServerHost() {
        return ftpServerHost;
    }

    public void setFtpServerHost(String ftpServerHost) {
        this.ftpServerHost = ftpServerHost;
    }

    public String getFtpServerPort() {
        return ftpServerPort;
    }

    public void setFtpServerPort(String ftpServerPort) {
        this.ftpServerPort = ftpServerPort;
    }

    public String getFtpUserName() {
        return ftpUserName;
    }

    public void setFtpUserName(String ftpUserName) {
        this.ftpUserName = ftpUserName;
    }

    public String getFtpPassword() {
        return ftpPassword;
    }

    public void setFtpPassword(String ftpPassword) {
        this.ftpPassword = ftpPassword;
    }

    public String getFtpEncoding() {
        return ftpEncoding;
    }

    public void setFtpEncoding(String ftpEncoding) {
        this.ftpEncoding = ftpEncoding;
    }

    public String getRootWorkingDir() {
        return rootWorkingDir;
    }

    public void setRootWorkingDir(String rootWorkingDir) {
        this.rootWorkingDir = rootWorkingDir;
    }

    public String getTmpFilePath() {
        return tmpFilePath;
    }

    public void setTmpFilePath(String tmpFilePath) {
        this.tmpFilePath = tmpFilePath;
    }
}
