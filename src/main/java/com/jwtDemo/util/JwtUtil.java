package com.jwtDemo.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {
    // 加密算法
    static Algorithm algorithm = Algorithm.HMAC256("secret");
    // 生成token
    public static String createJwtToken(Long id,  long ttlMillis) {
        Date expireDate = new Date();
        Date date = new Date(ttlMillis);
        expireDate.setTime(System.currentTimeMillis() + ttlMillis);
        String token = JWT.create()
                .withClaim("id", id)
                .withExpiresAt(expireDate)
                .sign(algorithm);
        return token;
    }
    // 解析token
    public static Long parseJwtToken(String token) {
        //判断token是否为空
        if (token == null)
            throw new RuntimeException("token为空");
        //解析token
        DecodedJWT decodedJWT = JWT.require(algorithm).build().verify(token);
        //判断token是否过期
        if (decodedJWT == null)
            throw new RuntimeException("token无效");
        if (new Date().after(decodedJWT.getExpiresAt())) {
            throw new RuntimeException("Token已过期");
        }
        //获取用户id
        return decodedJWT.getClaim("id").asLong();
    }
}
