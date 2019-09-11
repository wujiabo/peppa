package com.wujiabo.peppa.module.auth.service.impl;

import com.wujiabo.peppa.common.constant.TokenConstants;
import com.wujiabo.peppa.common.util.JwtUtils;
import com.wujiabo.peppa.module.auth.dto.LoginDTO;
import com.wujiabo.peppa.module.auth.service.IAuthService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements IAuthService {

    @Override
    public String login(LoginDTO loginDTO) {
        if(StringUtils.isEmpty(loginDTO.getUserName()) || StringUtils.isEmpty(loginDTO.getUnencryptedPassword())){
            return null;
        }
        //将salt保存到缓存中
        String salt = JwtUtils.generateSalt();
        return JwtUtils.sign(loginDTO.getUserName(), salt, TokenConstants.TOKEN_TIMEOUT);
    }

    @Override
    public void logout() {
        //缓存中删除token
    }
}
