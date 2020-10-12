package com.example.common.utils;

import java.util.Date;
import java.util.Map;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtUtil {

  /**
   * 用户登录成功后生成Jwt
   * 使用Hs256算法
   *
   * @param exp jwt过期时间
   * @param claims 保存在Payload（有效载荷）中的内容
   * @return token字符串
   */
  public  String createJWT(Date exp, Map<String, Object> claims, String signKey) {
    //指定签名的时候使用的签名算法
    SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
    //生成JWT的时间
    long nowMillis = System.currentTimeMillis();
    Date now = new Date(nowMillis);
    //创建一个JwtBuilder，设置jwt的body
    JwtBuilder builder = Jwts.builder()
        //保存在Payload（有效载荷）中的内容
        .setClaims(claims)
        //iat: jwt的签发时间
        .setIssuedAt(now)
        //设置过期时间
        .setExpiration(exp)
        //设置签名使用的签名算法和签名使用的秘钥
        .signWith(signatureAlgorithm, signKey);
    return builder.compact();
  }

  /**
   * 解析token，获取到Payload（有效载荷）中的内容，包括验证签名，判断是否过期
   *
   * @param token
   * @return
   */
  public Claims parseJWT(String token, String signKey) {
    //得到DefaultJwtParser
    Claims claims = Jwts.parser()
        //设置签名的秘钥
        .setSigningKey(signKey)
        //设置需要解析的token
        .parseClaimsJws(token).getBody();
    return claims;
  }

}