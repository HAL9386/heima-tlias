package com.heima.tliaswebmanagement.service;

import com.heima.tliaswebmanagement.pojo.PageResult;
import com.heima.tliaswebmanagement.pojo.Student;
import com.heima.tliaswebmanagement.pojo.StudentQueryParam;

import java.util.List;

public interface StudentService {
  PageResult<Student> page(StudentQueryParam param);

  void save(Student student);

  Student getStudentById(Integer id);

  void deleteByIds(List<Integer> ids);
}
