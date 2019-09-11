package com.wujiabo.peppa.common.interceptor;

import com.wujiabo.peppa.common.constant.TokenConstants;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * feign传递token
 */
public class FeignTokenInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate requestTemplate) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        requestTemplate.header(TokenConstants.HEADER_TOKEN_KEY, request.getHeader(TokenConstants.HEADER_TOKEN_KEY));
    }

}
