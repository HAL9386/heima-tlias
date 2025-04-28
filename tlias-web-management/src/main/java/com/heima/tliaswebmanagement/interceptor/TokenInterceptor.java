package com.heima.tliaswebmanagement.interceptor;

import com.heima.tliaswebmanagement.utils.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
@Component
public class TokenInterceptor implements HandlerInterceptor {
  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    String token = request.getHeader("token");
    if (token == null || token.isEmpty()) {
      log.info("令牌为空");
      response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
      return false;
    }
    try {
      JwtUtil.parseToken(token);
      log.info("令牌解析成功");
      return true;
    } catch (Exception e) {
      log.info("令牌解析失败");
      response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
      return false;
    }
  }
}
