package com.heima.tliaswebmanagement.service.impl;

import com.heima.tliaswebmanagement.mapper.EmpLogMapper;
import com.heima.tliaswebmanagement.pojo.EmpLog;
import com.heima.tliaswebmanagement.service.EmpLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EmpLogServiceImpl implements EmpLogService {

  private final EmpLogMapper empLogMapper;

  @Autowired
  public EmpLogServiceImpl(EmpLogMapper empLogMapper) {
    this.empLogMapper = empLogMapper;
  }

  @Transactional(propagation = Propagation.REQUIRES_NEW)
  @Override
  public void insertLog(EmpLog empLog) {
    empLogMapper.insert(empLog);
  }
}