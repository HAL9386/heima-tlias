package com.heima.tliaswebmanagement.aop;

import com.heima.tliaswebmanagement.mapper.OperateLogMapper;
import com.heima.tliaswebmanagement.pojo.OperateLog;
import com.heima.tliaswebmanagement.utils.CurrentHolder;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;

@Slf4j
@Aspect
@Component
public class OperationLogAspect {
  private final OperateLogMapper operateLogMapper;
  @Autowired
  public OperationLogAspect(OperateLogMapper operateLogMapper) {
    this.operateLogMapper = operateLogMapper;
  }
  @Around("@annotation(com.heima.tliaswebmanagement.anno.LogOperation)")
  public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
    long begin = System.currentTimeMillis();
    Object result = joinPoint.proceed();
    long end = System.currentTimeMillis();
    long costTime = end - begin;
    OperateLog operateLog = new OperateLog();
    operateLog.setOperateEmpId(getOperateEmpId());
    operateLog.setOperateTime(LocalDateTime.now());
    operateLog.setClassName(joinPoint.getTarget().getClass().getName());
    operateLog.setMethodName(joinPoint.getSignature().getName());
    operateLog.setMethodParams(Arrays.toString(joinPoint.getArgs()));
    operateLog.setReturnValue(result != null ? result.toString() : "void");
    operateLog.setCostTime(costTime);
    log.info("记录操作日志：{}", operateLog);
    operateLogMapper.insert(operateLog);
    return result;
  }
  private Integer getOperateEmpId() {
    return CurrentHolder.getCurrentId();
  }
}
