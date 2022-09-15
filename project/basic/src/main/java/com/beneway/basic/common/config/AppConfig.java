package com.beneway.basic.common.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
public class AppConfig {

    @Value("${spring.profiles.active}")
    private String active;

    @Value("${appConfig.excludePaths}")
    private String[] excludePaths;

    @Value("${appConfig.moduleType}")
    private String moduleType;

    public void setActive(String active) {
        if (this.active == null){
            this.active = active;
        }
    }

    public void setExcludePaths(String[] excludePaths) {
        if (this.excludePaths == null) {
            this.excludePaths = excludePaths;
        }
    }

    public void setModuleType(String moduleType) {
        if (this.moduleType == null) {
            this.moduleType = moduleType;
        }
    }
}
