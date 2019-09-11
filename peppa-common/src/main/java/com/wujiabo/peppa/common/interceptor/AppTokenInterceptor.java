package com.wujiabo.peppa.common.interceptor;

import com.wujiabo.peppa.common.constant.TokenConstants;
import com.wujiabo.peppa.common.annotation.PassToken;
import com.wujiabo.peppa.common.annotation.Permission;
import com.wujiabo.peppa.common.util.JwtUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

public class AppTokenInterceptor implements HandlerInterceptor {

    private static final Log log = LogFactory
            .getLog(AppTokenInterceptor.class);


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 从 http 请求头中取出 token
        String token = request.getHeader(TokenConstants.HEADER_TOKEN_KEY);

        // 如果不是映射到方法直接通过
        if(!(handler instanceof HandlerMethod)){
            return true;
        }
        HandlerMethod handlerMethod=(HandlerMethod)handler;
        Method method=handlerMethod.getMethod();
        //检查是否有passtoken注释，有则跳过认证
        if (method.isAnnotationPresent(PassToken.class)) {
            PassToken passToken = method.getAnnotation(PassToken.class);
            if (passToken.required()) {
                return true;
            }
        }

        //检查有没有需要用户权限的注解
        if (method.isAnnotationPresent(Permission.class)) {
            // 执行认证
            if (StringUtils.isBlank(token)) {
                response.setStatus(HttpStatus.UNAUTHORIZED.value());
                log.warn("x-token is blank");
                return false;
            }

            // 获取 token 中的 user id
            String userId = JwtUtils.getUserId(token);
            if(StringUtils.isBlank(userId)){
                response.setStatus(HttpStatus.UNAUTHORIZED.value());
                log.warn("can't get userId from x-token");
                return false;
            }

            // 验证用户拥有权限点
            Permission permission = method.getAnnotation(Permission.class);
            log.info(permission.value());
        }else{
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            log.warn("Annotation Permission is not exist");
            return false;
        }
        return true;
    }
}
