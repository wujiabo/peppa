package com.wujiabo.peppa.module.auth.service;

import com.wujiabo.peppa.module.auth.dto.LoginDTO;

public interface IAuthService {
    String login(LoginDTO loginDTO);

    void logout();
}
