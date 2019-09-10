package com.wujiabo.peppa.common.interceptor;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AppTokenInterceptor implements HandlerInterceptor {

    private static final Log log = LogFactory
            .getLog(AppTokenInterceptor.class);

    private static final String HEADER_TOKEN_KEY = "x-token";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (StringUtils.isBlank(request.getHeader(HEADER_TOKEN_KEY))) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            log.warn("x-token is blank");
            return false;
        }
        return true;
    }
}
