package com.wujiabo.peppa.common.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("feign.token.propagate")
public class FeignTokenProperties {
    private Boolean enabled = false;

    public Boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }
}
