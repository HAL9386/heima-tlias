package com.heima.tliaswebmanagement.controller;

import com.heima.tliaswebmanagement.pojo.PageResult;
import com.heima.tliaswebmanagement.pojo.Result;
import com.heima.tliaswebmanagement.pojo.Student;
import com.heima.tliaswebmanagement.pojo.StudentQueryParam;
import com.heima.tliaswebmanagement.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/students")
public class StudentController {

  private final StudentService studentService;

  @Autowired
  public StudentController(StudentService studentService) {
    this.studentService = studentService;
  }

  /**
   * 分页查询学员信息
   * @return 返回分页查询结果
   */
  @GetMapping
  public Result page(StudentQueryParam param) {
    log.info("分页查询, 参数: {}", param);
    PageResult<Student> pageResult = studentService.page(param);
    return Result.success(pageResult);
  }

  /**
   * 新增学员
   * @param student 学员信息
   * @return 返回新增结果
   */
  @PostMapping
  public Result save(@RequestBody Student student) {
    log.info("新增学员, 参数: {}", student);
    studentService.save(student);
    return Result.success();
  }

  /**
   * 根据ID查询学员信息
   * @param id 学员ID
   * @return 返回学员信息
   */
  @GetMapping("/{id}")
  public Result getStudentById(@PathVariable Integer id) {
    log.info("根据ID查询学员信息, 参数: {}", id);
    Student student = studentService.getStudentById(id);
    return Result.success(student);
  }

  /**
   * 批量删除学员
   * @param ids 要删除的学员ID列表
   */
  @DeleteMapping("/{ids}")
  public Result delete(@PathVariable String ids) {
    log.info("批量删除学员, 参数: {}", ids);
    try {
      List<Integer> idList = Arrays.stream(ids.split(","))
        .map(Integer::parseInt)
        .toList();
      studentService.deleteByIds(idList);
      return Result.success();
    } catch (NumberFormatException e) {
      throw new IllegalArgumentException("参数格式错误，ID必须为数字");
    }
  }

  /**
   * 修改学员信息
   * @param student 学员信息
   * @return 返回修改结果
   */
  @PutMapping
  public Result update(@RequestBody Student student) {
    log.info("修改学员信息, 参数: {}", student);
    studentService.update(student);
    return Result.success();
  }

  @PutMapping("/violation/{id}/{score}")
  public Result violate(
    @PathVariable Integer id,
    @PathVariable Integer score
  ) {
    log.info("学生违纪扣分, id: {}, 扣除分数: {}", id, score);
    studentService.handleViolation(id, score);
    return Result.success();
  }
}
