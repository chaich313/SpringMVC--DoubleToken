package com.itranlin.reimu.common.util;

import com.itranlin.reimu.admin.bo.BaseUser;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author itranlin
 */
public class TokenUtil {

    /**
     * 过期时间一周
     */
    private static final long ACCESS_EXPIRE_TIME = 7 * 12 * 3600 * 1000;

    private static final long REFRESH_EXPIRE_TIME =30 * 12 * 3600 * 1000;
    private static final String TOKEN_SECRET = "qcy999";

    public static final String TOKEN_TYPE_REFRESH = "refresh";

    public static final String TOKEN_TYPE_ACCESS = "access";

    /**
     * 生成签名，15分钟过期
     *
     * @param user 用户信息
     * @return token
     */
    public static String accessToken (BaseUser user) {
        // 设置过期时间
        Date date = new Date(System.currentTimeMillis() + ACCESS_EXPIRE_TIME);
        // 私钥和加密算法
        Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
        // 设置头部信息
        Map<String, Object> header = new HashMap<>(2);
        header.put("Type", "Jwt");
        header.put("alg", "HS256");
        String accessToken = JWT.create()
                .withHeader(header)
                .withClaim("username", user.getUsername())
                .withClaim("id", user.getId())
                .withClaim("role", user.getType())
                .withClaim("tokenType", TOKEN_TYPE_ACCESS)
                .withExpiresAt(date)
                .sign(algorithm);
        RedisUtil.set(accessToken, "Authorization", 60 * 60 * 24);
        return accessToken;
    }

    public static String refreshToken(BaseUser user) {
        // 设置过期时间
        Date date = new Date(System.currentTimeMillis() + REFRESH_EXPIRE_TIME);
        // 私钥和加密算法
        Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
        // 设置头部信息
        Map<String, Object> header = new HashMap<>(2);
        header.put("Type", "Jwt");
        header.put("alg", "HS256");
        String refreshToken = JWT.create()
                .withHeader(header)
                .withClaim("id", user.getId())
                .withClaim("tokenType", TOKEN_TYPE_REFRESH)
                .withExpiresAt(date)
                .sign(algorithm);
        RedisUtil.set(refreshToken, "Authorization", 60 * 60 * 24);
        return refreshToken;
    }

    /**
     * 检验token是否正确
     *
     * @param accessToken token串
     * @return token是否通过
     */
    public static boolean verify(String accessToken) {
        if (null == RedisUtil.get(accessToken)) {
            return false;
        }
        Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
        JWTVerifier verifier = JWT.require(algorithm).build();
        verifier.verify(accessToken);
        return true;
    }
}
