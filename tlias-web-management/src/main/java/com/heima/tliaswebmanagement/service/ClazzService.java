package com.heima.tliaswebmanagement.service;

import com.heima.tliaswebmanagement.exception.DeleteNotAllowedException;
import com.heima.tliaswebmanagement.pojo.Clazz;
import com.heima.tliaswebmanagement.pojo.ClazzQueryParam;
import com.heima.tliaswebmanagement.pojo.PageResult;

import java.util.List;

public interface ClazzService {
  PageResult<Clazz> page(ClazzQueryParam clazzQueryParam);

  void save(Clazz clazz);

  Clazz getById(Integer id);

  void deleteById(Integer id) throws DeleteNotAllowedException;

  void update(Clazz clazz);

  List<Clazz> findAll();
}
