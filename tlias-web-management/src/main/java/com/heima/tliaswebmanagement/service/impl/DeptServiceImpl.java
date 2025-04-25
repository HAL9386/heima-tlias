package com.heima.tliaswebmanagement.service.impl;

import com.heima.tliaswebmanagement.exception.DeleteNotAllowedException;
import com.heima.tliaswebmanagement.mapper.DeptMapper;
import com.heima.tliaswebmanagement.pojo.Dept;
import com.heima.tliaswebmanagement.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {
  private final DeptMapper deptMapper;

  @Autowired
  public DeptServiceImpl(DeptMapper deptMapper) {
    this.deptMapper = deptMapper;
  }

  @Override
  public List<Dept> findAll() {
    return deptMapper.findAll();
  }

  @Override
  public void deleteById(Integer id) throws DeleteNotAllowedException {
    Integer count = deptMapper.countByDeptId(id);
    if (count > 0) {
      throw new DeleteNotAllowedException("该部门下有员工，无法删除");
    }
    deptMapper.deleteById(id);
  }

  @Override
  public void add(Dept dept) {
    dept.setCreateTime(LocalDateTime.now());
    dept.setUpdateTime(LocalDateTime.now());
    deptMapper.add(dept);
  }

  @Override
  public Dept findById(Integer id) {
    return deptMapper.findById(id);
  }

  @Override
  public void update(Dept dept) {
    dept.setUpdateTime(LocalDateTime.now());
    deptMapper.update(dept);
  }
}
