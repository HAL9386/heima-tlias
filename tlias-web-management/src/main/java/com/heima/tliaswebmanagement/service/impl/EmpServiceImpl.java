package com.heima.tliaswebmanagement.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
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

  //
  // 原始分页查询实现
  //

//  @Override
//  public PageResult<Emp> page(Integer page, Integer pageSize) {
//    // 1 查询总记录数
//    Long total = empMapper.count();
//    // 2 查询结果列表
//    Integer start = (page - 1) * pageSize;
//    List<Emp> rows = empMapper.list(start, pageSize);
//    // 3 封装并返回结果
//    return new PageResult<Emp>(total, rows);
//  }

  /**
   * PageHelper分页查询
   * @param page 页码
   * @param pageSize 每页显示的记录数
   * @return 返回分页查询结果
   */
  @Override
  public PageResult<Emp> page(Integer page, Integer pageSize) {
    PageHelper.startPage(page, pageSize);
    List<Emp> empList = empMapper.list();
    Page<Emp> p = (Page<Emp>) empList;
    return new PageResult<>(p.getTotal(), p.getResult());
  }
}
