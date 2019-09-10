package com.wujiabo.peppa.module.demo2.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Api(value = "测试controller", tags = {"测试api"})
@RestController
@RequestMapping(value = "/api/demo2/v1")
public class TestController {
    @Autowired
    TestFeign testFeign;

    @ApiOperation(value = "测试", notes = "测试")
    @GetMapping("/test")
    public String test(@RequestParam(value = "name", required = true) String name, HttpServletRequest servletRequest) {
        System.out.println("demo2@"+servletRequest.getHeader("x-token"));
        return testFeign.test(name);
    }

}