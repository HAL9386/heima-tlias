package com.heima.tliaswebmanagement.controller;

import com.heima.tliaswebmanagement.pojo.JobOption;
import com.heima.tliaswebmanagement.pojo.Result;
import com.heima.tliaswebmanagement.service.ReportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/report")
public class ReportController {

  private final ReportService reportService;
  @Autowired
  public ReportController(ReportService reportService) {
    this.reportService = reportService;
  }
  /**
   * 统计报表：员工性别数据
   */
  @RequestMapping("/empJobData")
  public Result getEmpJobData() {
    log.info("统计报表：员工职位人数");
    JobOption jobOption = reportService.getEmpJobData();
    return Result.success(jobOption);
  }
}
