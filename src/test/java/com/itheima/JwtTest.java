package com.itheima;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.verify;

public class JwtTest {
    @Test
    public void testGen(){
        Map<String, Object> claims = new HashMap<>();
        claims.put("user", "zhangsan");
        claims.put("id", 1);

        // 生产JWT的代码
        String token = JWT.create()
                .withClaim("user", claims) // 添加载荷部分
                .withExpiresAt(new Date(System.currentTimeMillis()+1000*60*60*12))   // 添加过期时间为12h
                .sign(Algorithm.HMAC256("shmtu"));  // 选择加密算法，指定密钥为shmtu
        System.out.println(token);
    }
    @Test
    public void testParse(){
         // 定义一个模拟的token
        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9."+
                "eyJ1c2VyIjp7ImlkIjoxLCJ1c2VyIjoiemhhbmdzYW4ifSwiZXhwIjoxNzA4ODM1Mjg0fQ."+
                "d15sxDUvxadymv53zoyH5XC_phSUB36VPHNXOcSygGo";
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256("shmtu")).build(); // 指定算法，配置密钥
        DecodedJWT decodedJWT = jwtVerifier.verify(token); // 验证token，生成一个解析后的jwt对象
        Map<String, Claim> claims = decodedJWT.getClaims();
        System.out.println(claims.get("user"));
        System.out.println(claims.get("id"));
     }
}
