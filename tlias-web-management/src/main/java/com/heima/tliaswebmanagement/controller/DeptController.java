package com.heima.tliaswebmanagement.controller;

import com.heima.tliaswebmanagement.exception.DeleteNotAllowedException;
import com.heima.tliaswebmanagement.pojo.Dept;
import com.heima.tliaswebmanagement.pojo.Result;
import com.heima.tliaswebmanagement.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequestMapping("/depts")
@RestController
public class DeptController {
  private final DeptService deptService;

  @Autowired
  public DeptController(DeptService deptService) {
    this.deptService = deptService;
  }

//  @RequestMapping(value = "/depts", method = RequestMethod.GET)
  @GetMapping
  public Result list() {
    List<Dept> deptList = deptService.findAll();
    return Result.success(deptList);
  }

  @DeleteMapping
//  public Result delete(@RequestParam("id") Integer deptID) {
    public Result delete(Integer id) throws DeleteNotAllowedException {
    log.info("delete dept id: {}", id);
    deptService.deleteById(id);
    return Result.success();
  }

  @PostMapping
  public Result add(@RequestBody Dept dept) {
    log.info("add dept: {}", dept);
    deptService.add(dept);
    return Result.success();
  }

  @GetMapping("/{id}")
  public Result getInfoById(@PathVariable Integer id) {
    Dept dept = deptService.findById(id);
    return Result.success(dept);
  }

  @PutMapping
  public Result update(@RequestBody Dept dept) {
    log.info("update dept, id = {}", dept.getId());
    deptService.update(dept);
    return Result.success();
  }
}
