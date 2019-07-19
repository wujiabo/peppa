package com.wujiabo.peppa.module.auth.service;

import com.wujiabo.peppa.module.auth.dto.AuthRequestDTO;

public interface IAuthService {
    String login(AuthRequestDTO authRequest);

    String refresh(String token);

    boolean validate(String token, String resource);
}
