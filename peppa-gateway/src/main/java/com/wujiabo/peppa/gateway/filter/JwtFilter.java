package com.wujiabo.peppa.gateway.filter;

import org.apache.commons.lang.StringUtils;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class JwtFilter extends AbstractGatewayFilterFactory {
    private static final String HEADER_TOKEN_KEY = "x-token";

    @Override
    public GatewayFilter apply(Object config) {
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            String jwtToken = request.getHeaders().getFirst(HEADER_TOKEN_KEY);
            //校验jwtToken的合法性
            if (StringUtils.isNotBlank(jwtToken)) {
                // 合法
                // 将用户id作为参数传递下去
                return chain.filter(exchange);
            }
            //不合法(响应未登录的异常)
            ServerHttpResponse response = exchange.getResponse();
            //设置headers
            HttpHeaders httpHeaders = response.getHeaders();
            httpHeaders.add("Content-Type", "application/json; charset=UTF-8");
            httpHeaders.add("Cache-Control", "no-store, no-cache, must-revalidate, max-age=0");
            //设置body
            String warningStr = "未登录或登录超时";
            DataBuffer bodyDataBuffer = response.bufferFactory().wrap(warningStr.getBytes());
            return response.writeWith(Mono.just(bodyDataBuffer));
        };
    }
}
