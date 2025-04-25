package com.heima.tliaswebmanagement.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.heima.tliaswebmanagement.mapper.StudentMapper;
import com.heima.tliaswebmanagement.pojo.PageResult;
import com.heima.tliaswebmanagement.pojo.Student;
import com.heima.tliaswebmanagement.pojo.StudentQueryParam;
import com.heima.tliaswebmanagement.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

  private final StudentMapper studentMapper;

  @Autowired
  public StudentServiceImpl(StudentMapper studentMapper) {
    this.studentMapper = studentMapper;
  }
  @Override
  public PageResult<Student> page(StudentQueryParam param) {
    PageHelper.startPage(param.getPage(), param.getPageSize());
    List<Student> list = studentMapper.list(param);
    Page<Student> p = (Page<Student>) list;
    return new PageResult<>(p.getTotal(), p.getResult());
  }

  @Override
  public void save(Student student) {
    student.setCreateTime(LocalDateTime.now());
    student.setUpdateTime(LocalDateTime.now());
    studentMapper.insert(student);
  }

  @Override
  public Student getStudentById(Integer id) {
    return studentMapper.selectById(id);
  }

  @Override
  public void deleteByIds(List<Integer> ids) {
    if (ids.isEmpty()) {
      return;
    }
    studentMapper.deleteByIds(ids);
  }

  @Override
  public void update(Student student) {
    student.setUpdateTime(LocalDateTime.now());
    studentMapper.updateById(student);
  }

  @Override
  public void handleViolation(Integer id, Integer score) {
    if (score == null || score < 0) {
      throw new IllegalArgumentException("分数必须大于等于0");
    }
    Student student = studentMapper.selectById(id);
    if (student == null) {
      throw new IllegalArgumentException("学生不存在");
    }
    student.setViolationScore(student.getViolationScore() + score); ;
    student.setViolationCount(student.getViolationCount() + 1) ;
    student.setUpdateTime(LocalDateTime.now());
    studentMapper.updateById(student);
  }
}
