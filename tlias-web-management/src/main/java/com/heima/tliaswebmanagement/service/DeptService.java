package com.heima.tliaswebmanagement.service;

import com.heima.tliaswebmanagement.pojo.Dept;

import java.util.List;

public interface DeptService {
  List<Dept> findAll();

  void deleteById(Integer id);

  void add(Dept dept);

  Dept findById(Integer id);

  void update(Dept dept);
}
