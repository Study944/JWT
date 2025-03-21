package com.jwtDemo;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

class JwtTest {
    @Test
    public void creatToken() {
        Map<String, Object> map = new HashMap<>();
        map.put("alg", "HS256");
        map.put("typ", "JWT");
        String token = JWT.create()
                //header
                .withHeader(map)
                //payload
                .withClaim("id", 1)
                .withClaim("name", "zhangsan")
                .withExpiresAt(new Date(System.currentTimeMillis() + 1000L * 60 * 60 * 24 * 7))
                //signature
                .sign(Algorithm.HMAC256("tokenTest"));
        //token使用base64编码==>header.payload.signature
        System.out.println(token);
    }
    @Test
    public void parseToken() {
        //token
        String token ="eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJuYW1lIjoiemhhbmdzYW4iLCJpZCI6MSwiZXhwIjoxNzQzMTQzODc3fQ.deyZiMknCZ6WBG_kN5yKTaTIwxcducpstyrN1eFYcvU";
        //解析token,JWT.require(解密算法(签名)).build().verify(token)
        DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC256("tokenTest")).build().verify(token);
        //解析header
        System.out.println("alg："+decodedJWT.getAlgorithm());
        System.out.println("type："+decodedJWT.getType());
        //解析payload
        Integer id = decodedJWT.getClaim("id").asInt();
        System.out.println("id："+id);
        String name = decodedJWT.getClaim("name").asString();
        System.out.println("name："+name);
    }
}
