package com.heima.tliaswebmanagement.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class RecordTimeAspect {
  @Pointcut("execution(* com.heima.tliaswebmanagement.service.*.*(..))")
  private void pointcut(){}

  @Around("pointcut()")
  public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
    log.info("开始记录时间");
    long begin = System.currentTimeMillis();
    Object result = joinPoint.proceed();
    long end = System.currentTimeMillis();
    log.info("结束记录时间，耗时：{}ms", end - begin);
    return result;
  }
}
