package com.heima.tliaswebmanagement.mapper;

import com.heima.tliaswebmanagement.pojo.Emp;
import com.heima.tliaswebmanagement.pojo.EmpQueryParam;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface EmpMapper {

  //
  // 原始分页查询实现
  //

//  /**
//   * 查询员工总记录数
//   * @return 返回总记录数
//   */
//  @Select("select count(*) from emp as e left join dept as d on e.dept_id = d.id")
//  Long count();
//
//  /**
//   * 分页查询员工数据
//   * @param start 起始页码
//   * @param pageSize 每页显示的记录数
//   * @return 返回当前页的数据列表
//   */
//  @Select("select e.*, d.name as deptName from emp as e left join dept as d on e.dept_id = d.id " +
//    "order by e.update_time desc limit #{start}, #{pageSize}")
//  List<Emp> list(Integer start, Integer pageSize);

//  @Select("select e.*, d.name as deptName from emp as e left join dept as d " +
//    "on e.dept_id = d.id order by e.update_time desc")
  List<Emp> list(EmpQueryParam queryParam);
}
