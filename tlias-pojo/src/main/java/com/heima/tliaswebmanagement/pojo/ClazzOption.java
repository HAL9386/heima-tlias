package com.heima.tliaswebmanagement.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClazzOption {
  private List<String> clazzList; // 班级列表
  private List<Long> dataList; // 数据列表
}
