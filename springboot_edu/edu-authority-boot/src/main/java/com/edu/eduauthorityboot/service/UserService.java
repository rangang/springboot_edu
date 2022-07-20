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

    /**
     * 用户注册
     *
     * @param phone    手机号
     * @param password 密码
     * @param nickname 昵称
     * @param portrait 头像
     * @return 受影响的行数
     */
    Integer register(String phone, String password, String nickname, String portrait);

}
