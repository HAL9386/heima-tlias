package com.heima.tliaswebmanagement.filter;

import com.heima.tliaswebmanagement.utils.JwtUtil;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
//@WebFilter(urlPatterns = "/*")
public class TokenFilter implements Filter {
  @Override
  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
    HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
    HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
    String token = httpServletRequest.getHeader("token");
    String url = httpServletRequest.getRequestURL().toString();
    if (url.contains("login")) {
      log.info("登录请求，放行");
      filterChain.doFilter(httpServletRequest, httpServletResponse);
      return;
    }
    if (token == null || token.isEmpty()) {
      log.info("令牌为空");
      httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
      return;
    }
    try {
      JwtUtil.parseToken(token);
      log.info("令牌解析成功");
      filterChain.doFilter(httpServletRequest, httpServletResponse);
    } catch (Exception e) {
      log.info("令牌解析失败");
      httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    }
  }
}
