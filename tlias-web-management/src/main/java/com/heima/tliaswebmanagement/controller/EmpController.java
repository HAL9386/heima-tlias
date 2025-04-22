package com.heima.tliaswebmanagement.controller;

import com.heima.tliaswebmanagement.pojo.Emp;
import com.heima.tliaswebmanagement.pojo.EmpQueryParam;
import com.heima.tliaswebmanagement.pojo.PageResult;
import com.heima.tliaswebmanagement.pojo.Result;
import com.heima.tliaswebmanagement.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

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
   * @return 返回分页查询结果
   */
  @GetMapping
  public Result page(EmpQueryParam queryParam) {
    log.info("分页查询，参数：{}", queryParam);
    PageResult<Emp> pageResult = empService.page(queryParam);
    return Result.success(pageResult);
  }

  /**
   * 新增员工
   * @param emp 员工信息
   * @return 返回新增结果
   */
  @PostMapping
  public Result save(@RequestBody Emp emp) throws Exception {
    log.info("新增员工，参数：{}", emp);
    empService.save(emp);
    return Result.success();
  }
}
