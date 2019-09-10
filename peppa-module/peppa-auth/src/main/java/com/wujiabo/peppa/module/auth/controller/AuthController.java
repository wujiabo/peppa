package com.wujiabo.peppa.module.auth.controller;

import com.wujiabo.peppa.module.auth.dto.LoginDTO;
import com.wujiabo.peppa.module.auth.service.IAuthService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@Api(value = "登录验证", tags = {"登录验证"})
@RestController
@RequestMapping(value = "/api/auth/v1")
public class AuthController {
    private static final String HEADER_TOKEN_KEY = "x-token";

    @Autowired
    private IAuthService authService;

    @ApiOperation(value = "用户登录", notes = "用户登录")
    @PostMapping(value = "/login")
    public ResponseEntity<Void> login(@RequestBody LoginDTO loginDTO, HttpServletResponse response) {
        String newToken = authService.login(loginDTO);
        if(StringUtils.isBlank(newToken)){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        response.setHeader(HEADER_TOKEN_KEY, newToken);
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "退出登录", notes = "退出登录")
    @GetMapping(value = "/logout")
    public ResponseEntity<Void> logout() {
        authService.logout();
        return ResponseEntity.ok().build();
    }


    @ApiOperation(value = "获取当前用户信息", notes = "获取当前用户信息")
    @GetMapping(value = "/user")
    public ResponseEntity<Void> user() {
        return ResponseEntity.ok().build();
    }
}