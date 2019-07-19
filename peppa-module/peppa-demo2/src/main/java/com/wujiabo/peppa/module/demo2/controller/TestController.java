package com.wujiabo.peppa.module.demo2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class TestController {
    @Autowired
    TestFeign testFeign;

    @GetMapping("/test")
    public String hello(@RequestParam(value = "name", required = true) String name, HttpServletRequest servletRequest) {
        System.out.println("x-token2@"+servletRequest.getHeader("x-token"));
        return testFeign.hello(name);
    }

}