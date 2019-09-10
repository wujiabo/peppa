package com.wujiabo.peppa.module.auth.dto;

import java.io.Serializable;

public class LoginDTO implements Serializable {
    private String userName;
    private String unencryptedPassword;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUnencryptedPassword() {
        return unencryptedPassword;
    }

    public void setUnencryptedPassword(String unencryptedPassword) {
        this.unencryptedPassword = unencryptedPassword;
    }
}
