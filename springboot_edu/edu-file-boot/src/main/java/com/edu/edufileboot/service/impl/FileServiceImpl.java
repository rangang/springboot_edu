package com.edu.edufileboot.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.edu.edufileboot.service.FileService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

/**
 * @BelongsProject: springboot_edu
 * @Author: RG
 * @CreateTime: 2022/7/28 2:16 下午
 * @Description:
 */
@Service
public class FileServiceImpl implements FileService {

    @Value("${aliyun.oss.endpoint}")
    private String endpoint;
    @Value("${aliyun.oss.accessKeyId}")
    private String accessKeyId;
    @Value("${aliyun.oss.secret}")
    private String secret;
    @Value("${aliyun.oss.bucket}")
    private String bucket;

    @Override
    public String upload(MultipartFile file) {
        try {
            // 创建OSSClient实例。
            OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, secret);
            // 上传文件流。
            InputStream inputStream = file.getInputStream();
            String fileName = file.getOriginalFilename();
            // 生成随机唯一值，使用uuid，添加到文件名称里面
            String uuid = UUID.randomUUID().toString().replaceAll("-","");
            fileName = uuid+fileName;
            //按照当前日期，创建文件夹，上传到创建文件夹里面
            // 2021/02/02/01.mp4
            // String timeUrl = new DateTimeLiteralExpression.DateTime().toString("yyyy/MM/dd");
            // fileName = timeUrl+"/"+fileName; // 2021/02/02/23fads85rj4hka01.mp4
            // 调用方法实现上传
            ossClient.putObject(bucket, fileName, inputStream);
            // 关闭OSSClient。
            ossClient.shutdown();
            // 上传之后文件路径
            // https://lagou-laosun.oss-cn-beijing.aliyuncs.com/01.jpg
            String url = "https://"+bucket+"."+endpoint+"/"+fileName;
            // 返回
            return url;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }

}
