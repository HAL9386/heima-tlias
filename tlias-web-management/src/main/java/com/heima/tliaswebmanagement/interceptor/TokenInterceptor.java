package com.heima.tliaswebmanagement.interceptor;

import com.heima.tliaswebmanagement.utils.CurrentHolder;
import com.heima.tliaswebmanagement.utils.JwtUtil;
import io.jsonwebtoken.Claims;
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
      Claims claims = JwtUtil.parseToken(token);
      Integer empId = (Integer) claims.get("id");
      CurrentHolder.setCurrentId(empId);
      log.info("令牌解析成功，当前用户ID：{}，存入ThreadLocal", empId);
      return true;
    } catch (Exception e) {
      log.info("令牌解析失败");
      response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
      return false;
    }
  }

  @Override
  public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    CurrentHolder.remove();
  }
}
