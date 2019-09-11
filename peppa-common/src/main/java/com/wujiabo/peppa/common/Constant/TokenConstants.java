package com.wujiabo.peppa.common.Constant;

public final class TokenConstants {
    public static final String HEADER_TOKEN_KEY = "x-token";
    public static final String CLAIM_KEY = "USER_ID";
    public static final String TOKEN_SALT_KEY_PREFIX = "TOKEN_";
    //jwt token设置刷新间隔为5分钟
    public static final int TOKEN_REFRESH_INTERVAL = 300;
    //jwt token设置过期时间为1小时
    public static final int TOKEN_TIMEOUT = 3600;
}
