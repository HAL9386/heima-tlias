package com.heima.tliaswebmanagement.mapper;

import com.heima.tliaswebmanagement.pojo.Dept;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DeptMapper {

//  @Results({
//    @Result(property = "createTime", column = "create_time"),
//    @Result(property = "updateTime", column = "update_time")
//  })
  @Select("select id, name, create_time, update_time from dept order by update_time desc")
//  @Select("select id, name, create_time as createTime, update_time as updateTime from dept order by update_time desc")
  List<Dept> findAll();

  @Delete("delete from dept where id = #{id}")
  void deleteById(Integer id);

  @Insert("insert into dept(name, create_time, update_time) values(#{name}, #{createTime}, #{updateTime})")
  void add(Dept dept);

  @Select("select id, name, create_time, update_time from dept where id = #{id}")
  Dept findById(Integer id);

  @Update("update dept set name = #{name}, update_time = #{updateTime} where id = #{id}")
  void update(Dept dept);
}
