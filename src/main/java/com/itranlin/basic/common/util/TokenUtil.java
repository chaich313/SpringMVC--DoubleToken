package com.itranlin.basic.common.util;

import com.itranlin.basic.admin.entity.SysUser;

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
    private static final long EXPIRE_TIME = 7 * 12 * 3600 * 1000;
    private static final String TOKEN_SECRET = "qcy999";

    /**
     * 生成签名，15分钟过期
     *
     * @param user 用户信息
     * @return token
     */
    public static String sign(SysUser user) {
        // 设置过期时间
        Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
        // 私钥和加密算法
        Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
        // 设置头部信息
        Map<String, Object> header = new HashMap<>(2);
        header.put("Type", "Jwt");
        header.put("alg", "HS256");
        String token = JWT.create()
                .withHeader(header)
                .withClaim("username", user.getUsername())
                .withClaim("id", user.getId())
                .withClaim("role", user.getType())
                .withExpiresAt(date)
                .sign(algorithm);
        RedisUtil.set(token, "Authorization", 60 * 60 * 24);
        return token;
    }

    /**
     * 检验token是否正确
     *
     * @param token token串
     * @return token是否通过
     */
    public static boolean verify(String token) {
        if (null == RedisUtil.get(token)) {
            return false;
        }
        Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
        JWTVerifier verifier = JWT.require(algorithm).build();
        verifier.verify(token);
        return true;
    }
}
