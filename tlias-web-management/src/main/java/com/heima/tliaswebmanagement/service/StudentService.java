package com.heima.tliaswebmanagement.service;

import com.heima.tliaswebmanagement.pojo.PageResult;
import com.heima.tliaswebmanagement.pojo.Student;
import com.heima.tliaswebmanagement.pojo.StudentQueryParam;

public interface StudentService {
  PageResult<Student> page(StudentQueryParam param);
}
