package com.edu.edufileboot.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @BelongsProject: springboot_edu
 * @Author: RG
 * @CreateTime: 2022/7/28 2:15 下午
 * @Description:
 */
public interface FileService {

    String upload(MultipartFile file);

}
