package com.heima.tliaswebmanagement.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {
  private Integer       id;             // 主键ID
  private String        name;           // 姓名
  private String        no;             // 学号
  private Integer       gender;         // 性别
  private String        phone;          // 手机号
  private String        idCard;         // 身份证号
  private Integer       isCollege;      // 是否是大学生
  private String        address;        // 家庭住址
  private Integer       degree;         // 学历
  private LocalDate     graduationDate; // 毕业时间
  private Integer       clazzId;        // 班级ID，关联班级表的主键ID
  private Integer       violationCount; // 违规次数
  private Integer       violationScore; // 违规扣分
  private LocalDateTime createTime;     // 创建时间
  private LocalDateTime updateTime;     // 更新时间

  private String clazzName; // 班级名称
}
