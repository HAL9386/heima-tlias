package com.heima.tliaswebmanagement.mapper;

import com.heima.tliaswebmanagement.pojo.Clazz;
import com.heima.tliaswebmanagement.pojo.ClazzQueryParam;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ClazzMapper {
  List<Clazz> list(ClazzQueryParam clazzQueryParam);

  @Insert("insert into clazz(name, room, begin_date, end_date, master_id, subject, create_time, update_time) VALUE (#{name}, #{room}, #{beginDate}, #{endDate}, #{masterId}, #{subject}, #{createTime}, #{updateTime})")
  void insert(Clazz clazz);

  @Select("select c.name, c.room, c.begin_date, c.end_date," +
    "c.master_id, c.subject, c.create_time, c.update_time, e.name as masterName from clazz as c " +
    "left join emp as e on c.master_id = e.id where c.id = #{id}")
  Clazz getById(Integer id);

  @Select("select count(*) from student as s left join clazz as c on c.id = s.clazz_id where c.id = #{id}")
  Integer countStudentById(Integer id);

  @Delete("delete from clazz where id = #{id}")
  void deleteById(Integer id);

  void updateById(Clazz clazz);
}
