package com.heima.tliaswebmanagement.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Map;

public class JwtUtil {
  private static final SecretKey SECRET_KEY = Jwts.SIG.HS256.key().build();
  private static final long TOKEN_EXPIRATION = 12 * 60 * 60 * 1000; // Token过期时间，单位为毫秒，12小时
  /**
   * 生成JWT Token
   * @param claims 存储在Token中的信息
   * @return 生成的Token字符串
   */
  public static String generateToken(Map<String, Object> claims) {
    return Jwts.builder()
            .claims(claims)
            .signWith(SECRET_KEY)
            .expiration(new Date(System.currentTimeMillis() + TOKEN_EXPIRATION))
      .compact();
  }
  /**
   * 解析JWT Token
   * @param token 要解析的Token字符串
   * @return 解析后的Claims对象
   */
  public static Claims parseToken(String token) {
    return Jwts.parser()
           .verifyWith(SECRET_KEY)
           .build()
           .parseSignedClaims(token)
      .getPayload();
  }
}
