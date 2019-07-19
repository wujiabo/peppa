package com.wujiabo.peppa.module.auth.dto;

import java.io.Serializable;

public class AuthResponseDTO implements Serializable {

    private final String token;

    public AuthResponseDTO(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
