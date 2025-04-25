package com.heima.tliaswebmanagement.service.impl;

import com.heima.tliaswebmanagement.mapper.ClazzMapper;
import com.heima.tliaswebmanagement.mapper.EmpMapper;
import com.heima.tliaswebmanagement.mapper.StudentMapper;
import com.heima.tliaswebmanagement.pojo.ClazzOption;
import com.heima.tliaswebmanagement.pojo.JobOption;
import com.heima.tliaswebmanagement.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ReportServiceImpl implements ReportService {

  private final EmpMapper empMapper;
  private final ClazzMapper clazzMapper;
  private final StudentMapper studentMapper;
  @Autowired
  public ReportServiceImpl(EmpMapper empMapper, ClazzMapper clazzMapper, StudentMapper studentMapper) {
    this.empMapper = empMapper;
    this.clazzMapper = clazzMapper;
    this.studentMapper = studentMapper;
  }
  @Override
  public JobOption getEmpJobData() {
    List<Map<String, Object>> list = empMapper.countEmpJobData();
    List<String> jobList = list.stream().map(dataMap -> (String) dataMap.get("jobName")).toList();
    List<Long> dataList = list.stream().map(dataMap -> (Long) dataMap.get("count")).toList();
    return new JobOption(jobList, dataList);
  }

  @Override
  public List<Map<String, Object>> getEmpGenderData() {
    return empMapper.countEmpGenderData();
  }

  @Override
  public ClazzOption getStudentCountData() {
    List<Map<String, Object>> list = clazzMapper.countStudentCountData();
    List<String> nameList = list.stream().map(dataMap -> (String) dataMap.get("clazzName")).toList();
    List<Long> dataList = list.stream().map(dataMap -> (Long) dataMap.get("count")).toList();
    return new ClazzOption(nameList, dataList);
  }

  @Override
  public List<Map<String, Object>> getStudentDegreeData() {
    return studentMapper.countStudentDegreeData();
  }
}
