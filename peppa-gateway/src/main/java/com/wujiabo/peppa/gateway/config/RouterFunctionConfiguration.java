package com.wujiabo.peppa.gateway.config;

import com.wujiabo.peppa.gateway.handler.HystrixFallbackHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;

@Configuration
public class RouterFunctionConfiguration {
	private final HystrixFallbackHandler hystrixFallbackHandler;

	public RouterFunctionConfiguration(HystrixFallbackHandler hystrixFallbackHandler) {
		this.hystrixFallbackHandler = hystrixFallbackHandler;
	}

	@Bean
	public RouterFunction routerFunction() {
		return RouterFunctions.route(
			RequestPredicates.path("/fallback")
				.and(RequestPredicates.accept(MediaType.TEXT_PLAIN)), hystrixFallbackHandler);

	}

}
