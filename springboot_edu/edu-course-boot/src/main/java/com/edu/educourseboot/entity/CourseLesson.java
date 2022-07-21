package com.edu.educourseboot.entity;

import lombok.Data;

import java.util.Date;
import java.io.Serializable;

/**
 * 课程节内容(CourseLesson)实体类
 *
 * @author RG
 * @since 2022-07-11 10:42:22
 */
@Data
public class CourseLesson implements Serializable {
    private static final long serialVersionUID = 866785801548048114L;
    /**
     *  一小节对应一个视频
     */
    // private CourseMedia courseMedia;
    /**
     * id
     */
    private Integer id;
    /**
     * 课程id
     */
    private Integer courseId;
    /**
     * 章节id
     */
    private Integer sectionId;
    /**
     * 课时主题
     */
    private String theme;
    /**
     * 课时时长(分钟)
     */
    private Integer duration;
    /**
     * 是否免费
     */
    private Integer isFree;
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
    /**
     * 排序字段
     */
    private Integer orderNum;
    /**
     * 课时状态,0-隐藏，1-未发布，2-已发布
     */
    private Integer status;



}

