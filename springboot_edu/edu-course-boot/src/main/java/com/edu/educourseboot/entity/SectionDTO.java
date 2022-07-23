package com.edu.educourseboot.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 课程章节表(CourseSection)实体类
 *
 * @author RG
 * @since 2022-07-11 10:42:22
 */
@Data
public class SectionDTO implements Serializable {
    private static final long serialVersionUID = -24882580307027226L;
    /**
     * 一个章节对应多个小节
     */
    private List<LessonDTO> courseLessons;
    /**
     * id
     */
    private Integer id;
    /**
     * 课程id
     */
    private Integer courseId;
    /**
     * 章节名
     */
    private String sectionName;
    /**
     * 章节描述
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
    private Integer isDe;
    /**
     * 排序字段
     */
    private Integer orderNum;
    /**
     * 状态，0:隐藏；1：待更新；2：已发布
     */
    private Integer status;


}

