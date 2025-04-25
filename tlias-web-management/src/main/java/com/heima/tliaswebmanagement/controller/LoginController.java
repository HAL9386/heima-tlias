package com.heima.tliaswebmanagement.controller;

import com.heima.tliaswebmanagement.exception.InvalidUserException;
import com.heima.tliaswebmanagement.pojo.Emp;
import com.heima.tliaswebmanagement.pojo.LoginInfo;
import com.heima.tliaswebmanagement.pojo.Result;
import com.heima.tliaswebmanagement.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/login")
public class LoginController {
  private final EmpService empService;
  @Autowired
  public LoginController(EmpService empService) {
    this.empService = empService;
  }

  @PostMapping
  public Result login(@RequestBody Emp emp) throws InvalidUserException {
    log.info("登录用户信息:{}", emp);
    LoginInfo info = empService.getByUsernameAndPassword(emp);
    return Result.success(info);
  }
}
