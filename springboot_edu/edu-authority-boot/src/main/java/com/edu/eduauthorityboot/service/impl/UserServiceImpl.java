package com.edu.eduauthorityboot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.edu.eduauthorityboot.entity.EduConstant;
import com.edu.eduauthorityboot.entity.User;
import com.edu.eduauthorityboot.entity.UserDTO;
import com.edu.eduauthorityboot.mapper.UserMapper;
import com.edu.eduauthorityboot.service.UserService;
import com.edu.eduauthorityboot.tools.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @BelongsProject: springboot_edu
 * @Author: RG
 * @CreateTime: 2022/7/19 1:31 下午
 * @Description:
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisTemplate<Object,Object> redisTemplate;

    @Override
    public UserDTO login(String phone, String password) {
        UserDTO userDTO = new UserDTO();

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("phone",phone);
        Integer i = userMapper.selectCount(queryWrapper);
        if (i == 0) {
            // 手机号不存在
            userDTO.setState(EduConstant.ERROR_NOT_FOUND_PHONE_CODE);
            userDTO.setMessage(EduConstant.ERROR_NOT_FOUND_PHONE);
        } else {
            queryWrapper.eq("password",password);
            User user = userMapper.selectOne(queryWrapper);
            if (user == null) {
                // 账号密码不匹配
                userDTO.setState(EduConstant.ERROR_PASSWORD_CODE);
                userDTO.setMessage(EduConstant.ERROR_PASSWORD);
            } else {
                // 登录成功
                userDTO.setState(EduConstant.LOGIN_SUCCESS_CODE);
                userDTO.setMessage(EduConstant.LOGIN_SUCCESS);
                // 生成token
                System.out.println(user.getId());
                String token = JwtUtil.createToken(user);
                // 将token保存到redis中，并设置过期时间
                redisTemplate.opsForValue().set(token,token,15*1000, TimeUnit.SECONDS);
                userDTO.setToken(token);
                System.out.println("token = " + token);
            }
        }

        return userDTO;
    }

    @Override
    public UserDTO checkToken(String token) {
        return null;
    }
}
