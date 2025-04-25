package com.heima.tliaswebmanagement.service;

import com.heima.tliaswebmanagement.pojo.ClazzOption;
import com.heima.tliaswebmanagement.pojo.JobOption;

import java.util.List;
import java.util.Map;

public interface ReportService {
  JobOption getEmpJobData();

  List<Map<String, Object>> getEmpGenderData();

  ClazzOption getStudentCountData();

  List<Map<String, Object>> getStudentDegreeData();
}
