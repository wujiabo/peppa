package com.wujiabo.peppa.module.demo1.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Value("${aaaa}")
    private String aaaa;

    @GetMapping("/test")
    public String hello(@RequestParam(value = "name", required = true) String name) {
        System.out.println("cloudclient start@" + aaaa);
        return name + "===端口：8002被调用了===+" + aaaa;
    }

}