package com.heima.tliaswebmanagement.mapper;

import com.heima.tliaswebmanagement.pojo.Student;
import com.heima.tliaswebmanagement.pojo.StudentQueryParam;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StudentMapper {

  List<Student> list(StudentQueryParam param);
}
