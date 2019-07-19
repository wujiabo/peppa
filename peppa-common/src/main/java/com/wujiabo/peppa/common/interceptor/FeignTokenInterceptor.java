package com.wujiabo.peppa.common.interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * feign传递token
 */
public class FeignTokenInterceptor implements RequestInterceptor {
    private static final String HEADER_TOKEN_KEY = "x-token";

    @Override
    public void apply(RequestTemplate requestTemplate) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        requestTemplate.header(HEADER_TOKEN_KEY, request.getHeader(HEADER_TOKEN_KEY));
    }

}
