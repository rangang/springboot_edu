package com.edu.eduauthorityboot.service;

import com.edu.eduauthorityboot.entity.UserDTO;

/**
 * @BelongsProject: springboot_edu
 * @Author: RG
 * @CreateTime: 2022/7/19 1:31 下午
 * @Description:
 */
public interface UserService {

    /**
     * 用户登录
     * @param phone
     * @param password
     * @return
     */
    UserDTO login(String phone, String password);

    /**
     * 验证token
     * @param token
     * @return
     */
    UserDTO checkToken(String token);

}
