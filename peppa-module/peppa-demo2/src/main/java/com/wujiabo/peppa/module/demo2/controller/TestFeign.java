package com.wujiabo.peppa.module.demo2.controller;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "peppa-demo1", fallback = TestFeignFallback.class)
public interface TestFeign {
    @GetMapping("/api/demo1/v1/test")
    String test(@RequestParam(value = "name", required = true) String name);
}
