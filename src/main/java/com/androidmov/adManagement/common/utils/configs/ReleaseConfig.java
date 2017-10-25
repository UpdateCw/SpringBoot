package com.androidmov.adManagement.common.utils.configs;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.validation.constraints.NotNull;

/**
 * Created by LLH
 * on 2017-08-23 16:53.
 */
@Configuration
@ConfigurationProperties(prefix = "release")
@PropertySource("classpath:projectConfigs/appConfig.properties")
public class ReleaseConfig {
    @NotNull
    private Integer tagCount;

    private Integer tagSplitCount = 4;

    public Integer getTagCount() {
        return tagCount;
    }

    public void setTagCount(Integer tagCount) {
        this.tagCount = tagCount;
    }

    public Integer getTagSplitCount() {
        return tagSplitCount;
    }

    public void setTagSplitCount(Integer tagSplitCount) {
        this.tagSplitCount = tagSplitCount;
    }
}
