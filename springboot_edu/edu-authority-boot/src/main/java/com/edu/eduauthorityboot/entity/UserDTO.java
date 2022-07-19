package com.edu.eduauthorityboot.entity;

import lombok.Data;

/**
 * @BelongsProject: springboot_edu
 * @Author: RG
 * @CreateTime: 2022/7/19 1:27 下午
 * @Description: 数据传输对象（DTO) Data Transfer Object
 */
@Data
public class UserDTO {
    private static final long serialVersionUID = 1L;
    private int state;  // 操作状态
    private String message;  // 状态描述
    private User content;  // 响应内容

    private String token;
}
