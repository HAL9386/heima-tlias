package com.heima.tliaswebmanagement.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.heima.tliaswebmanagement.exception.DeleteClazzNotAllowedException;
import com.heima.tliaswebmanagement.mapper.ClazzMapper;
import com.heima.tliaswebmanagement.pojo.Clazz;
import com.heima.tliaswebmanagement.pojo.ClazzQueryParam;
import com.heima.tliaswebmanagement.pojo.PageResult;
import com.heima.tliaswebmanagement.service.ClazzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ClazzServiceImpl implements ClazzService {

  private final ClazzMapper clazzMapper;

  @Autowired
  public ClazzServiceImpl(ClazzMapper clazzMapper) {
    this.clazzMapper = clazzMapper;
  }

  /**
   * PageHelper分页查询班级
   *
   * @return 返回分页查询结果
   */
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

  /**
   * 新增班级
   *
   * @param clazz 班级信息
   */
  @Override
  public void save(Clazz clazz) {
    clazz.setCreateTime(LocalDateTime.now());
    clazz.setUpdateTime(LocalDateTime.now());
    clazzMapper.insert(clazz);
  }


  @Override
  public Clazz getById(Integer id) {
    return clazzMapper.getById(id);
  }

  @Override
  public void deleteById(Integer id) throws DeleteClazzNotAllowedException {
    Integer count = clazzMapper.countStudentById(id);
    if (count > 0) {
      throw new DeleteClazzNotAllowedException("对不起, 该班级下有学生, 不能直接删除");
    }
    clazzMapper.deleteById(id);
  }

  @Override
  public void update(Clazz clazz) {
    clazz.setUpdateTime(LocalDateTime.now());
    clazzMapper.updateById(clazz);
  }
}
