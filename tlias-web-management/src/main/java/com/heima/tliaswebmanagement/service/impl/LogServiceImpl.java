package com.heima.tliaswebmanagement.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.heima.tliaswebmanagement.mapper.OperateLogMapper;
import com.heima.tliaswebmanagement.pojo.OperateLog;
import com.heima.tliaswebmanagement.pojo.PageResult;
import com.heima.tliaswebmanagement.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogServiceImpl implements LogService {
  private final OperateLogMapper operateLogMapper;
  @Autowired
  public LogServiceImpl(OperateLogMapper operateLogMapper) {
    this.operateLogMapper = operateLogMapper;
  }
  @Override
  public PageResult<OperateLog> list(Integer page, Integer pageSize) {
    if (page == null) {
      page = 1;
    }
    if (pageSize == null) {
      pageSize = 10;
    }
    PageHelper.startPage(page, pageSize);
    List<OperateLog> logs = operateLogMapper.list();
    Page<OperateLog> p = (Page<OperateLog>) logs;
    return new PageResult<>(p.getTotal(), p.getResult());
  }
}
