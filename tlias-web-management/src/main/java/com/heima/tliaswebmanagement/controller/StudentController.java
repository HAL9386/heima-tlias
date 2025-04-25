package com.heima.tliaswebmanagement.controller;

import com.heima.tliaswebmanagement.pojo.PageResult;
import com.heima.tliaswebmanagement.pojo.Result;
import com.heima.tliaswebmanagement.pojo.Student;
import com.heima.tliaswebmanagement.pojo.StudentQueryParam;
import com.heima.tliaswebmanagement.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

  @GetMapping("/{id}")
  public Result getStudentById(@PathVariable Integer id) {
    log.info("根据ID查询学员信息, 参数: {}", id);
    Student student = studentService.getStudentById(id);
    return Result.success(student);
  }
}
