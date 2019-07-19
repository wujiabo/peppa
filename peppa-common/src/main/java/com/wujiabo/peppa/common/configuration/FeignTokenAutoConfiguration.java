package com.wujiabo.peppa.common.configuration;

import com.netflix.hystrix.Hystrix;
import com.wujiabo.peppa.common.interceptor.FeignTokenInterceptor;
import com.wujiabo.peppa.common.strategy.FeignTokenStrategy;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnClass({Hystrix.class})
@ConditionalOnProperty(value = "feign.token.propagate.enabled", matchIfMissing = false)
@EnableConfigurationProperties(FeignTokenProperties.class)
public class FeignTokenAutoConfiguration {
    @Bean
    public FeignTokenStrategy feignTokenAutoConfiguration() {
        return new FeignTokenStrategy();
    }

    @Bean
    public FeignTokenInterceptor feignTokenInterceptor() {
        return new FeignTokenInterceptor();
    }
}
