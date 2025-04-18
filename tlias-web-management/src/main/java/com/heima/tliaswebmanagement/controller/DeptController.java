package com.heima.tliaswebmanagement.controller;

import com.heima.tliaswebmanagement.pojo.Dept;
import com.heima.tliaswebmanagement.pojo.Result;
import com.heima.tliaswebmanagement.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public Result delete(Integer id) {
    System.out.println("delete dept id: " + id);
    deptService.deleteById(id);
    return Result.success();
  }

  @PostMapping
  public Result add(@RequestBody Dept dept) {
    System.out.println("add dept: " + dept);
    deptService.add(dept);
    return Result.success();
  }

//  @GetMapping("/depts/{id}")
//  public Result getInfoById(@PathVariable("id") Integer deptId) {
//
//  }

  @GetMapping("/{id}")
  public Result getInfoById(@PathVariable Integer id) {
    Dept dept = deptService.findById(id);
    return Result.success(dept);
  }

  @PutMapping
  public Result update(@RequestBody Dept dept) {
    System.out.println("update dept, id = " + dept.getId());
    deptService.update(dept);
    return Result.success();
  }
}
