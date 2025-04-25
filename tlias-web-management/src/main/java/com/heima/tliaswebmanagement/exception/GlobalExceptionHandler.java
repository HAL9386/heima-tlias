package com.heima.tliaswebmanagement.exception;

import com.heima.tliaswebmanagement.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
  @ExceptionHandler
  public Result handleException(Exception e) {
    log.error("全局异常处理器：", e);
    return Result.error("对不起，操作失败");
  }

  @ExceptionHandler
  public Result handleDuplicateKeyException(DuplicateKeyException e) {
    log.error("全局异常处理器 重复键：", e);
    String msg = e.getMessage();
    int i = msg.indexOf("Duplicate entry");
    String[] errMsg = msg.substring(i).split(" ");
    return Result.error(errMsg[2] + "已存在");
  }

  @ExceptionHandler(DeleteNotAllowedException.class)
  public Result handleDeleteClazzNotAllowedException(DeleteNotAllowedException e) {
    log.error("全局异常处理器 删除班级：", e);
    return Result.error(e.getMessage());
  }

  @ExceptionHandler(InvalidUserException.class)
  public Result handleInvalidUserException(InvalidUserException e) {
    log.error("全局异常处理器 无效用户：", e);
    return Result.error(e.getMessage());
  }
}
