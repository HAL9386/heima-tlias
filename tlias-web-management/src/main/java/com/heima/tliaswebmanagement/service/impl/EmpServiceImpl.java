package com.heima.tliaswebmanagement.service.impl;

import com.heima.tliaswebmanagement.mapper.EmpMapper;
import com.heima.tliaswebmanagement.pojo.Emp;
import com.heima.tliaswebmanagement.pojo.PageResult;
import com.heima.tliaswebmanagement.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpServiceImpl implements EmpService {
  private final EmpMapper empMapper;
  @Autowired
  public EmpServiceImpl(EmpMapper empMapper) {
    this.empMapper = empMapper;
  }
  @Override
  public PageResult<Emp> page(Integer page, Integer pageSize) {
    // 1 查询总记录数
    Long total = empMapper.count();
    // 2 查询结果列表
    Integer start = (page - 1) * pageSize;
    List<Emp> rows = empMapper.list(start, pageSize);
    // 3 封装并返回结果
    return new PageResult<Emp>(total, rows);
  }
}
