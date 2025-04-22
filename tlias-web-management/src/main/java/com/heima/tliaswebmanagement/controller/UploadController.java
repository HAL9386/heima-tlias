package com.heima.tliaswebmanagement.controller;

import com.heima.tliaswebmanagement.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
public class UploadController {
  @PostMapping("/upload")
  public Result upload(String name, Integer age, MultipartFile file) {
    log.info("文件上传：{}，{}，{}", name, age, file.getOriginalFilename());
    return Result.success();
  }
}
