package com.heima.tliaswebmanagement.service;

import com.heima.tliaswebmanagement.pojo.EmpLog;

public interface EmpLogService {
  //记录新增员工日志
  public void insertLog(EmpLog empLog);
}