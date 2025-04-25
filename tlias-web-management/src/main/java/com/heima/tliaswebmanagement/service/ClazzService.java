package com.heima.tliaswebmanagement.service;

import com.heima.tliaswebmanagement.exception.DeleteClazzNotAllowedException;
import com.heima.tliaswebmanagement.pojo.Clazz;
import com.heima.tliaswebmanagement.pojo.ClazzQueryParam;
import com.heima.tliaswebmanagement.pojo.PageResult;

public interface ClazzService {
  PageResult<Clazz> page(ClazzQueryParam clazzQueryParam);

  void save(Clazz clazz);

  Clazz getById(Integer id);

  void deleteById(Integer id) throws DeleteClazzNotAllowedException;

  void update(Clazz clazz);
}
