package com.heima.tliaswebmanagement.mapper;

import com.heima.tliaswebmanagement.pojo.Student;
import com.heima.tliaswebmanagement.pojo.StudentQueryParam;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface StudentMapper {

  List<Student> list(StudentQueryParam param);

  @Insert("insert into student(name, no, gender, phone, id_card, is_college, address, degree, graduation_date, clazz_id, create_time, update_time) value (#{name}, #{no}, #{gender}, #{phone}, #{idCard}, #{isCollege}, #{address}, #{degree}, #{graduationDate}, #{clazzId}, #{createTime}, #{updateTime})")
  void insert(Student student);

  @Select("select s.*, c.name as clazzName from student as s left join clazz as c on s.clazz_id = c.id where s.id = #{id}")
  Student selectById(Integer id);

  void deleteByIds(List<Integer> ids);
}
