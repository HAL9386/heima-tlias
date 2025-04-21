package com.heima.tliaswebmanagement.service;

import com.heima.tliaswebmanagement.pojo.Emp;
import com.heima.tliaswebmanagement.pojo.PageResult;

public interface EmpService {
  PageResult<Emp> page(Integer page, Integer pageSize);
}
