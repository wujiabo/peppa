package com.wujiabo.peppa.module.demo2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @Autowired
    TestFeign testFeign;

    @RequestMapping("/test")
    public String hello(@RequestParam String name) {
        return testFeign.hello(name);
    }

}