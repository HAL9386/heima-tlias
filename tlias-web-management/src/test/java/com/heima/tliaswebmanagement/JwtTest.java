package com.heima.tliaswebmanagement;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.junit.jupiter.api.Test;

import javax.crypto.SecretKey;
import java.util.Date;

public class JwtTest {
  private static final SecretKey key = Jwts.SIG.HS256.key().build();

  @Test
  public void testJwtFlow() {
      // 生成token
      String jws = Jwts.builder()
              .claim("id", 1)
              .claim("name", "zhangsan")
              .signWith(key)
              .expiration(new Date(System.currentTimeMillis() + 3600 * 1000))
              .compact();

      // 解析token
      Claims claims = Jwts.parser()
              .verifyWith(key)
              .build()
              .parseSignedClaims(jws)
              .getPayload();

      System.out.println(claims);
  }
}
