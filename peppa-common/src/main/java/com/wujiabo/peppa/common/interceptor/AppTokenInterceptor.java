package com.wujiabo.peppa.common.interceptor;

import com.wujiabo.peppa.common.Constant.TokenConstants;
import com.wujiabo.peppa.common.annotation.PassToken;
import com.wujiabo.peppa.common.annotation.Permission;
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
            Permission permission = method.getAnnotation(Permission.class);
            String permissionCode = permission.value();
            if (StringUtils.isNotBlank(permissionCode)) {
                // 执行认证
                if (StringUtils.isBlank(token)) {
                    response.setStatus(HttpStatus.UNAUTHORIZED.value());
                    log.warn("x-token is blank");
                    return false;
                }
                log.info(permissionCode);
//                // 获取 token 中的 user id
//                String userId;
//                try {
//                    userId = JWT.decode(token).getAudience().get(0);
//                } catch (JWTDecodeException j) {
//                    throw new RuntimeException("401");
//                }
//                User user = userService.findUserById(userId);
//                if (user == null) {
//                    throw new RuntimeException("用户不存在，请重新登录");
//                }
//                // 验证 token
//                JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(user.getPassword())).build();
//                try {
//                    jwtVerifier.verify(token);
//                } catch (JWTVerificationException e) {
//                    throw new RuntimeException("401");
//                }
//                return true;
            }
        }else{
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            log.warn("Annotation Permission is not exist");
            return false;
        }
        return true;
    }
}
