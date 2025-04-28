package com.heima.tliaswebmanagement.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.heima.tliaswebmanagement.exception.InvalidUserException;
import com.heima.tliaswebmanagement.mapper.EmpExprMapper;
import com.heima.tliaswebmanagement.mapper.EmpMapper;
import com.heima.tliaswebmanagement.pojo.*;
import com.heima.tliaswebmanagement.service.EmpLogService;
import com.heima.tliaswebmanagement.service.EmpService;
import com.heima.tliaswebmanagement.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EmpServiceImpl implements EmpService {
  private final EmpMapper empMapper;
  private final EmpExprMapper empExprMapper;
  private final EmpLogService empLogService;
  @Autowired
  public EmpServiceImpl(EmpMapper empMapper,
                        EmpExprMapper empExprMapper,
                        EmpLogService empLogService) {
    this.empMapper = empMapper;
    this.empExprMapper = empExprMapper;
    this.empLogService = empLogService;
  }

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

  /**
   * 新增员工
   *
   * @param emp 员工信息
   */
  @Transactional(rollbackFor = {Exception.class}) // 默认情况下，事务只对运行时异常进行回滚，对编译异常不回滚.
  // 可以通过rollbackFor属性指定异常类型进行回滚
  @Override
  public void save(Emp emp) {
    try {
      emp.setCreateTime(LocalDateTime.now());
      emp.setUpdateTime(LocalDateTime.now());
      empMapper.insert(emp);
      List<EmpExpr> exprList = emp.getExprList();
      if (!exprList.isEmpty()) {
        exprList.forEach(empExpr -> empExpr.setEmpId(emp.getId()));
        empExprMapper.insertBatch(exprList);
      }
    } finally {
      empLogService.insertLog(new EmpLog(null, LocalDateTime.now(), emp.toString()));
    }
  }

  /**
   * 批量删除
   *
   * @param ids 员工id列表
   */
  @Transactional(rollbackFor = {Exception.class})
  @Override
  public void deleteByIds(List<Integer> ids) {
    if (ids.isEmpty()) {
      return;
    }
    empMapper.deleteByIds(ids);
    empExprMapper.deleteByEmpIds(ids);
  }

  /**
   * 根据ID查询员工信息
   *
   * @param id 员工ID
   * @return 返回员工信息
   */
  @Override
  public Emp queryById(Integer id) {
    return empMapper.queryById(id);
  }

  /**
   * 修改员工信息
   * 对于工作经历可能修改，删除，新增
   * 1 先删除
   * 2 再新增
   *
   * @param emp 员工信息
   */
  @Transactional(rollbackFor = {Exception.class})
  @Override
  public void update(Emp emp) {
    emp.setUpdateTime(LocalDateTime.now());
    empMapper.updateById(emp);
    empExprMapper.deleteByEmpIds(Collections.singletonList(emp.getId()));
    empExprMapper.insertBatch(emp.getExprList());
  }

  @Override
  public List<Emp> list() {
    return empMapper.findAll();
  }

  @Override
  public LoginInfo getByUsernameAndPassword(Emp emp) throws InvalidUserException {
    Emp e = empMapper.getByUsernameAndPassword(emp);
    if (e == null) {
      throw new InvalidUserException("用户名或密码错误");
    }
    Map<String, Object> claims = new HashMap<>();
    claims.put("id", e.getId());
    claims.put("username", e.getUsername());
    String token = JwtUtil.generateToken(claims);
    LoginInfo info = new LoginInfo();
    info.setId(e.getId());
    info.setUsername(e.getUsername());
    info.setName(e.getName());
    info.setToken(token);
    return info;
  }
}
