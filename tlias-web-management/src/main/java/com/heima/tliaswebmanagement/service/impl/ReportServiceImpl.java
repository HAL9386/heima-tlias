package com.heima.tliaswebmanagement.service.impl;

import com.heima.tliaswebmanagement.mapper.EmpMapper;
import com.heima.tliaswebmanagement.pojo.JobOption;
import com.heima.tliaswebmanagement.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ReportServiceImpl implements ReportService {

  private final EmpMapper empMapper;
  @Autowired
  public ReportServiceImpl(EmpMapper empMapper) {
    this.empMapper = empMapper;
  }
  @Override
  public JobOption getEmpJobData() {
    List<Map<String, Object>> list = empMapper.countEmpJobData();
    List<String> jobList = list.stream().map(dataMap -> (String) dataMap.get("jobName")).toList();
    List<Long> dataList = list.stream().map(dataMap -> (Long) dataMap.get("count")).toList();
    return new JobOption(jobList, dataList);
  }
}
