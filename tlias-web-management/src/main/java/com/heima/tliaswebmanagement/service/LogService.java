package com.heima.tliaswebmanagement.service;

import com.heima.tliaswebmanagement.pojo.OperateLog;
import com.heima.tliaswebmanagement.pojo.PageResult;

public interface LogService {
  PageResult<OperateLog> list(Integer page, Integer pageSize);
}
