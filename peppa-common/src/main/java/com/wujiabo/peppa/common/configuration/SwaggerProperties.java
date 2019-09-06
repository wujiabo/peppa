package com.wujiabo.peppa.common.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("swagger.ui")
public class SwaggerProperties {
    private Boolean enabled = false;

    private String title = "peppa";
    private String desc = "peppa api";
    private String version = "V1.0";
    private String basePackage = "com.wujiabo.peppa";

    public Boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getBasePackage() {
        return basePackage;
    }

    public void setBasePackage(String basePackage) {
        this.basePackage = basePackage;
    }
}
