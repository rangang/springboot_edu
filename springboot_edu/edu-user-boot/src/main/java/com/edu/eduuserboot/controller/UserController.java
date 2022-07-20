package com.edu.eduuserboot.controller;

import com.edu.eduuserboot.entity.FileSystem;
import com.edu.eduuserboot.service.UserService;
import com.sun.org.apache.bcel.internal.generic.NEW;
import org.csource.common.IniFileReader;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @BelongsProject: springboot_edu
 * @Author: RG
 * @CreateTime: 2022/7/20 5:23 下午
 * @Description:
 */
@RestController
@RequestMapping("userSetting")
@CrossOrigin //跨域
public class UserController {

    private static String fastdfsip = null;

    static {
        Properties properties = new Properties();
        InputStream stream = IniFileReader.loadFromOsFileSystemOrClasspathAsStream("config/fastdfs-client.properties");
        if (stream != null) {
            try {
                properties.load(stream);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        fastdfsip = properties.getProperty("fastdfs.tracker_servers").split(":")[0];
    }

    @Autowired
    private UserService userService;

    @RequestMapping("/upload")
    public FileSystem upload(@RequestParam("file") MultipartFile file) throws IOException {
        System.out.println("接收到：" +file);
        FileSystem fs = new FileSystem();

        //获得文件的原始名称
        String oldFileName = file.getOriginalFilename();
        //获得后缀名
        String hou = oldFileName.substring(oldFileName.lastIndexOf(".")+1);

        try {
            //加载配置文件
            ClientGlobal.initByProperties("config/fastdfs-client.properties");

            System.out.println("ip:" + fastdfsip );
            //创建tracker客户端
            TrackerClient tc = new TrackerClient();
            //根据tracker客户端创建连接
            TrackerServer ts = tc.getConnection();
            StorageServer ss = null;
            //定义storage客户端
            StorageClient1 client = new StorageClient1(ts, ss);
            //文件元信息
            NameValuePair[] list = new NameValuePair[1];
            list[0] = new NameValuePair("fileName", oldFileName);
            //上传，返回fileId
            String fileId = client.upload_file1(file.getBytes(), hou, list);
            System.out.println(fileId);
            ts.close();
            //封装数据对象，将路径保存到数据库（本次不写）
            fs.setFileId(fileId);
            fs.setFilePath(fileId);
            fs.setFileName(oldFileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fs;
    }

    @RequestMapping("/updateUser")
    public void updateUser(Integer userId, String newName, String imgFileId) {
        System.out.println("newName = " + newName);
        imgFileId = "http://" + fastdfsip + "/" + imgFileId;
        System.out.println("imgFileId = " + imgFileId);
        userService.updateUser(userId,newName,imgFileId);
    }

    @RequestMapping("/updatePassword")
    public void updatePassword(Integer userId, String newPwd) {
        System.out.println("userId = " + userId);
        System.out.println("newPwd = " + newPwd);
        userService.updatePassword(userId,newPwd);
    }


}
