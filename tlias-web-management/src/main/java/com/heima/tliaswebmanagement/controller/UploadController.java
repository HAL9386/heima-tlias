package com.heima.tliaswebmanagement.controller;

import com.heima.tliaswebmanagement.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Slf4j
@RestController
public class UploadController {
  @PostMapping("/upload")
  public Result upload(String name, Integer age, MultipartFile file) throws IOException {
    log.info("文件上传：{}，{}，{}", name, age, file.getOriginalFilename());
    // 保存文件到服务器
    String originalFilename = file.getOriginalFilename();
    String filenameExt = originalFilename.substring(originalFilename.lastIndexOf("."));
    String newFilename = UUID.randomUUID().toString() + filenameExt;
    String path = "D:/tmp/heima/tlias/images/";
    makeDir(path);
    file.transferTo(new File(path + newFilename));
    return Result.success();
  }
  private void makeDir(String dirPath) {
    File dir = new File(dirPath);
    if (!dir.exists()) {
      dir.mkdirs();
    }
  }
}
