package com.heima.tliaswebmanagement.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.heima.tliaswebmanagement.mapper.EmpExprMapper;
import com.heima.tliaswebmanagement.mapper.EmpMapper;
import com.heima.tliaswebmanagement.pojo.Emp;
import com.heima.tliaswebmanagement.pojo.EmpExpr;
import com.heima.tliaswebmanagement.pojo.EmpQueryParam;
import com.heima.tliaswebmanagement.pojo.PageResult;
import com.heima.tliaswebmanagement.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class EmpServiceImpl implements EmpService {
  private final EmpMapper empMapper;
  private final EmpExprMapper empExprMapper;
  @Autowired
  public EmpServiceImpl(EmpMapper empMapper, EmpExprMapper empExprMapper) {
    this.empMapper = empMapper;
    this.empExprMapper = empExprMapper;
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
   *
   * @return 返回分页查询结果
   * 1 定义的SQL语句结尾不能加分号，因为PageHelper是拼接的
   * 2 仅对紧跟其后的第一条SQL语句生效
   */
  @Override
  public PageResult<Emp> page(EmpQueryParam queryParam) {
    PageHelper.startPage(queryParam.getPage(), queryParam.getPageSize());
    List<Emp> empList = empMapper.list(queryParam);
    Page<Emp> p = (Page<Emp>) empList;
    return new PageResult<>(p.getTotal(), p.getResult());
  }

  @Transactional
  @Override
  public void save(Emp emp) {
    emp.setCreateTime(LocalDateTime.now());
    emp.setUpdateTime(LocalDateTime.now());
    empMapper.insert(emp);
    List<EmpExpr> exprList = emp.getExprList();
    if (!exprList.isEmpty()) {
      exprList.forEach(empExpr -> empExpr.setEmpId(emp.getId()));
      empExprMapper.insertBatch(exprList);
    }
  }
}
