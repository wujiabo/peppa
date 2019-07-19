package com.wujiabo.peppa.module.auth.service.impl;

import com.wujiabo.peppa.module.auth.dto.AuthRequestDTO;
import com.wujiabo.peppa.module.auth.dto.ClientDTO;
import com.wujiabo.peppa.module.auth.service.IAuthService;
import com.wujiabo.peppa.module.auth.util.JwtUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class AuthService implements IAuthService {

    private static BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    @Override
    public String login(AuthRequestDTO authRequest) {
        ClientDTO client = new ClientDTO();
        String clientEncodeSecret="";//根据用户查询数据库中的加密后用户密码
        String token = null;
        if(encoder.matches(authRequest.getSecret(),clientEncodeSecret)) {
            token = JwtUtils.createToken(client);
        }
        return token;
    }

    @Override
    public String refresh(String oldToken) {

        return null;
    }

    @Override
    public boolean validate(String token, String resource) {
        return false;
    }
}
