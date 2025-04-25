package com.heima.tliaswebmanagement.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.heima.tliaswebmanagement.mapper.ClazzMapper;
import com.heima.tliaswebmanagement.pojo.Clazz;
import com.heima.tliaswebmanagement.pojo.ClazzQueryParam;
import com.heima.tliaswebmanagement.pojo.PageResult;
import com.heima.tliaswebmanagement.service.ClazzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ClazzServiceImpl implements ClazzService {

  private final ClazzMapper clazzMapper;

  @Autowired
  public ClazzServiceImpl(ClazzMapper clazzMapper) {
    this.clazzMapper = clazzMapper;
  }
  @Override
  public PageResult<Clazz> page(ClazzQueryParam clazzQueryParam) {
    PageHelper.startPage(clazzQueryParam.getPage(), clazzQueryParam.getPageSize());
    List<Clazz> clazzList = clazzMapper.list(clazzQueryParam);
    LocalDate now = LocalDate.now();
    clazzList.forEach(clazz -> {
      if (now.isAfter(clazz.getEndDate())) {
        clazz.setStatus("已结课");
      } else if (now.isBefore(clazz.getBeginDate())) {
        clazz.setStatus("未开班");
      } else {
        clazz.setStatus("在读中");
      }
    });
    Page<Clazz> p = (Page<Clazz>) clazzList;
    return new PageResult<>(p.getTotal(), p.getResult());
  }
}
