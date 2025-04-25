package com.heima.tliaswebmanagement.controller;

import com.heima.tliaswebmanagement.exception.DeleteClazzNotAllowedException;
import com.heima.tliaswebmanagement.pojo.Clazz;
import com.heima.tliaswebmanagement.pojo.ClazzQueryParam;
import com.heima.tliaswebmanagement.pojo.PageResult;
import com.heima.tliaswebmanagement.pojo.Result;
import com.heima.tliaswebmanagement.service.ClazzService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/clazzs")
public class ClazzController {

  private final ClazzService clazzService;
  @Autowired
  public ClazzController(ClazzService clazzService) {
    this.clazzService = clazzService;
  }

  /**
   * 分页查询班级数据
   * @param clazzQueryParam 分页查询参数
   * @return 分页结果
   */
  @GetMapping
  public Result page(ClazzQueryParam clazzQueryParam) {
    log.info("分页查询，参数：{}", clazzQueryParam);
    PageResult<Clazz> pageResult = clazzService.page(clazzQueryParam);
    return Result.success(pageResult);
  }

  /**
   * 新增班级
   * @param clazz 班级信息
   */
  @PostMapping
  public Result save(@RequestBody Clazz clazz) {
    log.info("新增班级，参数：{}", clazz);
    clazzService.save(clazz);
    return Result.success();
  }

  /**
   * 根据id查询班级信息
   * @param id 班级id
   * @return 班级信息
   */
  @GetMapping("/{id}")
  public Result getById(@PathVariable Integer id) {
    log.info("根据ID查询班级，参数：{}", id);
    Clazz clazz = clazzService.getById(id);
    return Result.success(clazz);
  }

  /**
   * 根据id删除班级
   * @param id 班级id
   * @return 删除结果
   * */
  @DeleteMapping("/{id}")
  public Result deleteById(@PathVariable Integer id) throws DeleteClazzNotAllowedException {
    log.info("根据ID删除班级，参数：{}", id);
    clazzService.deleteById(id);
    return Result.success();
  }

  /**
   * 根据id更新班级信息
   * @param clazz 班级信息
   * @return 更新结果
   */
  @PutMapping
  public Result updateById(@RequestBody Clazz clazz) {
    log.info("根据ID更新班级，参数：{}", clazz);
    clazzService.update(clazz);
    return Result.success();
  }

  /**
   * 查询所有班级
   * @return 所有班级信息
   */
  @GetMapping("/list")
  public Result list() {
    log.info("查询所有班级");
    List<Clazz> list = clazzService.findAll();
    return Result.success(list);
  }
}
