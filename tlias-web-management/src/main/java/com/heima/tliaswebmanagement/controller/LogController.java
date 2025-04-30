package com.heima.tliaswebmanagement.controller;

import com.heima.tliaswebmanagement.pojo.OperateLog;
import com.heima.tliaswebmanagement.pojo.PageResult;
import com.heima.tliaswebmanagement.pojo.Result;
import com.heima.tliaswebmanagement.service.LogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping("/log")
@RestController
public class LogController {
  private final LogService logService;
  @Autowired
  public LogController(LogService logService) {
    this.logService = logService;
  }
  @GetMapping("/page")
  public Result list(@RequestParam(defaultValue = "1") Integer page,
                     @RequestParam(defaultValue = "10") Integer pageSize) {
    log.info("查询日志");
    PageResult<OperateLog> pageResult = logService.list(page, pageSize);
    return Result.success(pageResult);
  }
}
