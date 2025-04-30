package com.heima.tliaswebmanagement.controller;

import com.heima.tliaswebmanagement.anno.LogOperation;
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
import java.util.List;

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
  @LogOperation
  @PostMapping
  public Result save(@RequestBody Emp emp) throws Exception {
    log.info("新增员工，参数：{}", emp);
    empService.save(emp);
    return Result.success();
  }

  /**
   * 批量删除
   * @param ids 要删除的员工ID列表
   * @return 返回删除结果
   */
  @LogOperation
  @DeleteMapping
  public Result delete(@RequestParam List<Integer> ids) {
    log.info("批量删除，参数：{}", ids);
    empService.deleteByIds(ids);
    return Result.success();
  }

  /**
   * 根据ID查询员工信息
   * @param id 员工ID
   * @return 返回员工信息
   */
  @GetMapping("/{id}")
  public Result queryById(@PathVariable Integer id) {
    log.info("根据ID查询员工信息，参数：{}", id);
    Emp emp = empService.queryById(id);
    return Result.success(emp);
  }

  @LogOperation
  @PutMapping
  public Result update(@RequestBody Emp emp) {
    log.info("修改员工信息，参数：{}", emp);
    empService.update(emp);
    return Result.success();
  }

  @GetMapping("/list")
  public Result list() {
    List<Emp> list = empService.list();
    return Result.success(list);
  }
}
