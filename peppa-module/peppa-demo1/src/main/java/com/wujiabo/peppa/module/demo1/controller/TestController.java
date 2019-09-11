package com.wujiabo.peppa.module.demo1.controller;

import com.wujiabo.peppa.common.annotation.Permission;
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
@RequestMapping(value = "/api/demo1/v1")
public class TestController {

    @Value("${swagger.ui.desc}")
    private String desc;

    @ApiOperation(value = "测试", notes = "测试")
    @GetMapping("/test")
    public String test(@RequestParam(value = "name", required = true) String name, HttpServletRequest servletRequest) {
        return name + "===" + desc + "===" + servletRequest.getHeader("x-token");
    }


    @ApiOperation(value = "testPermission", notes = "testPermission")
    @Permission("testPermission")
    @GetMapping("/testPermission")
    public String testPermission() {
        return "testPermission";
    }
}