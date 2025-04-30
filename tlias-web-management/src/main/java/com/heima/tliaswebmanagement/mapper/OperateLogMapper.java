package com.heima.tliaswebmanagement.mapper;

import com.heima.tliaswebmanagement.pojo.OperateLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface OperateLogMapper {

  //插入日志数据
  @Insert("insert into operate_log (operate_emp_id, operate_time, class_name, method_name, method_params, return_value, cost_time) " +
    "values (#{operateEmpId}, #{operateTime}, #{className}, #{methodName}, #{methodParams}, #{returnValue}, #{costTime});")
  public void insert(OperateLog log);


  @Select("select id, operate_emp_id, operate_time, class_name, method_name, method_params, return_value, cost_time from operate_log order by operate_time desc")
  List<OperateLog> list();
}