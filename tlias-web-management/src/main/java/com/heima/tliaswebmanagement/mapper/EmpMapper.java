package com.heima.tliaswebmanagement.mapper;

import com.heima.tliaswebmanagement.pojo.Emp;
import com.heima.tliaswebmanagement.pojo.EmpQueryParam;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

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

  @Options(useGeneratedKeys = true, keyProperty = "id")
  @Insert("insert into emp(username, name, gender, phone, job, salary, image, entry_date, dept_id, create_time, update_time) " +
    "VALUE (#{username}, #{name}, #{gender}, #{phone}, #{job}, #{salary}, #{image}, #{entryDate}, #{deptId}, #{createTime}, #{updateTime})")
  void insert(Emp emp);

  void deleteByIds(List<Integer> ids);

  Emp queryById(Integer id);

  void updateById(Emp emp);

  /**
   * 查询员工的职位和对应数量
   * @return 返回职位和数量的映射
   */
  @MapKey("jobName")
  List<Map<String, Object>> countEmpJobData();

  /**
   * 查询员工的性别和对应数量
   * @return 返回性别和数量的映射
   */
  @MapKey("name")
  List<Map<String, Object>> countEmpGenderData();

  @Select("select e.id, e.username, e.name, e.gender, e.image, e.job," +
    "e.salary, e.entry_date, e.dept_id, e.create_time, e.update_time from emp as e")
  List<Emp> findAll();
}
