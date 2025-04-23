package com.heima.tliaswebmanagement.service;

import com.heima.tliaswebmanagement.pojo.Emp;
import com.heima.tliaswebmanagement.pojo.EmpQueryParam;
import com.heima.tliaswebmanagement.pojo.PageResult;

import java.util.List;

public interface EmpService {
  PageResult<Emp> page(EmpQueryParam empQueryParam);

  void save(Emp emp) throws Exception;

  void deleteByIds(List<Integer> ids);

  Emp queryById(Integer id);

  void update(Emp emp);
}
