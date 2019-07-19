package com.wujiabo.peppa.module.auth.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.wujiabo.peppa.module.auth.dto.ClientDTO;
import org.apache.commons.lang.time.DateUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtUtils {
    private static final String SECRET = "PEPPA";
    private static final String ISSUER = "USER";

    /**
     * 生成token
     *
     * @param client
     * @return
     */
    public static String createToken(ClientDTO client) {
        Map<String, String> claims = new HashMap<>();
        claims.put("CLIENT_ID", client.getClientId());
        claims.put("CLIENT_NAME", client.getClientName());
        Algorithm algorithm = Algorithm.HMAC256(SECRET);
        JWTCreator.Builder builder = JWT.create()
                .withIssuer(ISSUER)
                //设置过期时间为2小时
                .withExpiresAt(DateUtils.addHours(new Date(), 2));
        claims.forEach(builder::withClaim);
        return builder.sign(algorithm);
    }

    /**
     * 验证jwt，并返回数据
     */
    public static Map<String, String> verifyToken(String token) throws Exception {
        Algorithm algorithm;
        Map<String, Claim> map;
        try {
            algorithm = Algorithm.HMAC256(SECRET);
            JWTVerifier verifier = JWT.require(algorithm).withIssuer(ISSUER).build();
            DecodedJWT jwt = verifier.verify(token);
            map = jwt.getClaims();
        } catch (Exception e) {
            throw new Exception("鉴权失败");
        }
        Map<String, String> resultMap = new HashMap<>(map.size());
        map.forEach((k, v) -> resultMap.put(k, v.asString()));
        return resultMap;
    }
}
