package com.edu.eduauthorityboot.controller;

import com.edu.eduauthorityboot.entity.UserDTO;
import com.edu.eduauthorityboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @BelongsProject: springboot_edu
 * @Author: RG
 * @CreateTime: 2022/7/19 3:35 下午
 * @Description:
 */
@RestController
@RequestMapping("/user")
@CrossOrigin
public class AuthorityController {

    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    public UserDTO login(String phone,String password) {
        return userService.login(phone,password);
    }

    @RequestMapping("/checkToken")
    public UserDTO checkToken(String token) {
        return userService.checkToken(token);
    }

}
