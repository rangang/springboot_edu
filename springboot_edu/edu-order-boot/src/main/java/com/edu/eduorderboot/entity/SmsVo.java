package com.edu.eduorderboot.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @BelongsProject: springboot_edu
 * @Author: RG
 * @CreateTime: 2022/7/28 11:20 上午
 * @Description:
 */
@Data
public class SmsVo implements Serializable {

    private static final long serialVersionUID = -13287667215938957L;

    private String phone;
    private String courseName;

}
