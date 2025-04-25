package com.heima.tliaswebmanagement.controller;

import com.heima.tliaswebmanagement.pojo.Clazz;
import com.heima.tliaswebmanagement.pojo.ClazzQueryParam;
import com.heima.tliaswebmanagement.pojo.PageResult;
import com.heima.tliaswebmanagement.pojo.Result;
import com.heima.tliaswebmanagement.service.ClazzService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/clazzs")
public class ClazzController {

  private final ClazzService clazzService;
  @Autowired
  public ClazzController(ClazzService clazzService) {
    this.clazzService = clazzService;
  }
  @GetMapping
  public Result page(ClazzQueryParam clazzQueryParam) {
    log.info("分页查询，参数：{}", clazzQueryParam);
    PageResult<Clazz> pageResult = clazzService.page(clazzQueryParam);
    return Result.success(pageResult);
  }
}
