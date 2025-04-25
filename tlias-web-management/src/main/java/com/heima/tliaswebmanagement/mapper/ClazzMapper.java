package com.heima.tliaswebmanagement.mapper;

import com.heima.tliaswebmanagement.pojo.Clazz;
import com.heima.tliaswebmanagement.pojo.ClazzQueryParam;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ClazzMapper {
  List<Clazz> list(ClazzQueryParam clazzQueryParam);
}
