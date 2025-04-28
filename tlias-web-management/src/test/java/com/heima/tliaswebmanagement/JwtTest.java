package com.heima.tliaswebmanagement;

import com.heima.tliaswebmanagement.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.junit.jupiter.api.Test;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtTest {
  private static final SecretKey key = Jwts.SIG.HS256.key().build();

  @Test
  public void testJwtFlow() {
    Map<String, Object> claims = new HashMap<>();
    claims.put("id", 1);
    claims.put("name", "zhangsan");
    // 生成token
//    String jws = Jwts.builder()
//      .claims(claims)
//      .signWith(key)
//      .expiration(new Date(System.currentTimeMillis() + 3600 * 1000))
//      .compact();
    String token = JwtUtil.generateToken(claims);

    // 解析token
//    claims = Jwts.parser()
//      .verifyWith(key)
//      .build()
//      .parseSignedClaims(jws)
//      .getPayload();
    Claims claims1 = JwtUtil.parseToken(token);

    System.out.println(claims1);
  }
}
