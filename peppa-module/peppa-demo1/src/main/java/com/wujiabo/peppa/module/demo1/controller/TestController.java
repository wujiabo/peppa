package com.wujiabo.peppa.module.demo1.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Api(value = "测试controller", tags = {"测试api"})
@RestController
public class TestController {

    @Value("${aaaa}")
    private String aaaa;

    @ApiOperation(value = "测试")
    @GetMapping("/test")
    public String hello(@RequestParam(value = "name", required = true) String name, HttpServletRequest servletRequest) {
        System.out.println("x-token1@"+servletRequest.getHeader("x-token"));
        System.out.println("cloudclient start@" + aaaa);
        return name + "===端口：8002被调用了===+" + aaaa;
    }

}