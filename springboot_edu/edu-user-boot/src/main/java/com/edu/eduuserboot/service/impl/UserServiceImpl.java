package com.edu.eduuserboot.service.impl;

import com.edu.eduuserboot.entity.User;
import com.edu.eduuserboot.mapper.UserMapper;
import com.edu.eduuserboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @BelongsProject: springboot_edu
 * @Author: RG
 * @CreateTime: 2022/7/20 5:22 下午
 * @Description:
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public void updateUser(Integer userId, String newName, String imgFileId) {
        User user = new User();
        user.setId(userId);
        user.setName(newName);
        user.setPortrait(imgFileId);
        userMapper.updateById(user);
    }

    @Override
    public void updatePassword(Integer userId, String newPwd) {
        User user = new User();
        user.setId(userId);
        user.setPassword(newPwd);
        userMapper.updateById(user);
    }
}
