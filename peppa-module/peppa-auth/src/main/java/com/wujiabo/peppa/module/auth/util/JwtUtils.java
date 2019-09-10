package com.wujiabo.peppa.module.auth.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

public class JwtUtils {
    public static final String CLAIM_KEY = "USER_NAME";
    public static final String HEADER_TOKEN_KEY = "banana-auth-token";
    public static final String TOKEN_SALT_KEY_PREFIX = "TOKEN_";
    //jwt token设置刷新间隔为5分钟
    public static final int TOKEN_REFRESH_INTERVAL = 300;
    //jwt token设置过期时间为1小时
    public static final int TOKEN_TIMEOUT = 3600;

    /**
     * 获得token中的信息无需secret解密也能获得
     *
     * @return token中包含的签发时间
     */
    public static Date getIssuedAt(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getIssuedAt();
        } catch (JWTDecodeException e) {
            return null;
        }
    }

    /**
     * 获得token中的信息无需secret解密也能获得
     *
     * @return token中包含的用户名
     */
    public static String getUsername(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim(CLAIM_KEY).asString();
        } catch (JWTDecodeException e) {
            return null;
        }
    }

    /**
     * 生成签名,expireTime后过期
     *
     * @param username 用户名
     * @param time     过期时间s
     * @return 加密的token
     */
    public static String sign(String username, String salt, long time) {
        Date date = new Date(System.currentTimeMillis() + time * 1000);
        Algorithm algorithm = Algorithm.HMAC256(salt);
        // 附带username信息
        return JWT.create()
                .withClaim(CLAIM_KEY, username)
                .withExpiresAt(date)
                .withIssuedAt(new Date())
                .sign(algorithm);
    }

    /**
     * token是否过期
     *
     * @return true：过期
     */
    public static boolean isTokenExpired(String token) {
        Date now = Calendar.getInstance().getTime();
        DecodedJWT jwt = JWT.decode(token);
        return jwt.getExpiresAt().before(now);
    }

    /**
     * 生成随机盐,长度32位
     *
     * @return
     */
    public static String generateSalt() {
        return UUID.randomUUID().toString();
    }

}
