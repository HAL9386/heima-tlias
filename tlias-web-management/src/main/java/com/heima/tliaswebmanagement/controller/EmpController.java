package com.heima.tliaswebmanagement.controller;

import com.heima.tliaswebmanagement.pojo.Emp;
import com.heima.tliaswebmanagement.pojo.PageResult;
import com.heima.tliaswebmanagement.pojo.Result;
import com.heima.tliaswebmanagement.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping("/emps")
@RestController
public class EmpController {
  private final EmpService empService;

  @Autowired
  public EmpController(EmpService empService) {
    this.empService = empService;
  }

  /**
   * 分页查询
   * @param page 页码
   * @param pageSize 每页显示的记录数
   * @return 返回分页查询结果
   */
  @GetMapping
  public Result page(@RequestParam(defaultValue = "1") Integer page,
                     @RequestParam(defaultValue = "10") Integer pageSize) {
    log.info("分页查询，参数：page = {}, pageSize = {}", page, pageSize);
    PageResult<Emp> pageResult = empService.page(page, pageSize);
    return Result.success(pageResult);
  }
}
