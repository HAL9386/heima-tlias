package com.heima.tliaswebmanagement.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClazzQueryParam {
  private                 String    name;         // 班级名称
  @DateTimeFormat(pattern =         "yyyy-MM-dd")
  private                 LocalDate begin;        // 结课的开始时间
  @DateTimeFormat(pattern =         "yyyy-MM-dd")
  private                 LocalDate end;          // 结课的结束时间
  private                 Integer   page          =  1;   // 页码
  private                 Integer   pageSize      =  10;  // 每页记录数
}
