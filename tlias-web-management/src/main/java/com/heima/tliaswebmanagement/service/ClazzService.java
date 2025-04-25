package com.heima.tliaswebmanagement.service;

import com.heima.tliaswebmanagement.pojo.Clazz;
import com.heima.tliaswebmanagement.pojo.ClazzQueryParam;
import com.heima.tliaswebmanagement.pojo.PageResult;

public interface ClazzService {
  PageResult<Clazz> page(ClazzQueryParam clazzQueryParam);
}
