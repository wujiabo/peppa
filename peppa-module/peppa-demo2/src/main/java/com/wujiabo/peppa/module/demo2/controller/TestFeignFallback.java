package com.wujiabo.peppa.module.demo2.controller;

import org.springframework.stereotype.Component;

@Component
public class TestFeignFallback implements TestFeign {
    @Override
    public String hello(String name) {
        System.out.println("TestFeignFallback");
        return null;
    }
}
