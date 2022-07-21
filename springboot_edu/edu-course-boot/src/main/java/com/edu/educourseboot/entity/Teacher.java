package com.edu.educourseboot.entity;

import lombok.Data;

import java.util.Date;
import java.io.Serializable;

/**
 * 讲师表(Teacher)实体类
 *
 * @author RG
 * @since 2022-07-11 10:40:38
 */
@Data
public class Teacher implements Serializable {
    private static final long serialVersionUID = -15622705751321612L;
    /**
     * id
     */
    private Integer id;
    /**
     * 课程ID
     */
    private Integer courseId;
    /**
     * 讲师姓名
     */
    private String teacherName;
    /**
     * 职务
     */
    private String position;
    /**
     * 讲师介绍
     */
    private String description;
    /**
     * 记录创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;
    /**
     * 是否删除
     */
    private Integer isDel;



}

