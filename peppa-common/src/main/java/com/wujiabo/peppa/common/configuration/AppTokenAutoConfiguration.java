package com.wujiabo.peppa.common.configuration;

import com.wujiabo.peppa.common.interceptor.AppTokenInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class AppTokenAutoConfiguration implements WebMvcConfigurer {
    @Bean
    public HandlerInterceptor getAppTokenInterceptor(){
        return new AppTokenInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(getAppTokenInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/swagger-ui.html")
                .excludePathPatterns("/webjars/**")
                .excludePathPatterns("/static/*");
    }
}
