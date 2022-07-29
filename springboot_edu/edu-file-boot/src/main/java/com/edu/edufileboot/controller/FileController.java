package com.edu.edufileboot.controller;

import com.edu.edufileboot.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @BelongsProject: springboot_edu
 * @Author: RG
 * @CreateTime: 2022/7/28 2:15 下午
 * @Description:
 */
@RestController
@RequestMapping("/file")
@CrossOrigin
public class FileController {

    @Autowired
    private FileService fileService;

    //上传文件到阿里云oss
    @PostMapping("/fileUpload")
    public String fileUpload(@RequestParam("file") MultipartFile file) {
        //获取上传文件
        String url = fileService.upload(file);
        return url;
    }

}
