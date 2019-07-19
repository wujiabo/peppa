package com.wujiabo.peppa.module.auth.controller;

import com.wujiabo.peppa.module.auth.dto.AuthRequestDTO;
import com.wujiabo.peppa.module.auth.dto.AuthResponseDTO;
import com.wujiabo.peppa.module.auth.service.IAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthController {

    @Autowired
    private IAuthService iAuthService;

    @PostMapping(value = "auth")
    public ResponseEntity<?> createAuthenticationToken(
            @RequestBody AuthRequestDTO authRequest) {
        final String token = iAuthService.login(authRequest);

        // Return the token
        return ResponseEntity.ok(new AuthResponseDTO(token));
    }
    @GetMapping(value = "refresh")
    public ResponseEntity<?> refreshAndGetAuthenticationToken(
            String token) {
        String refreshedToken = iAuthService.refresh(token);
        if(refreshedToken == null) {
            return ResponseEntity.badRequest().body(null);
        } else {
            return ResponseEntity.ok(new AuthResponseDTO(refreshedToken));
        }
    }
    @GetMapping(value = "verify")
    public ResponseEntity<?> verify(String token,String resource){
        if(iAuthService.validate(token,resource))
            return ResponseEntity.ok(true);
        else
            return ResponseEntity.status(401).body(false);
    }

}