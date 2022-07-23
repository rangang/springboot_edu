package com.edu.educourseboot.service;

import com.edu.educourseboot.entity.Course;
import com.edu.educourseboot.entity.CourseDTO;

import java.util.List;

/**
 * @BelongsProject: springboot_edu
 * @Author: RG
 * @CreateTime: 2022/7/21 9:50 上午
 * @Description:
 */
public interface CourseService {
    /**
     * 查询全部课程信息
     * @return
     */
    List<CourseDTO> getAllCourse();

    /**
     * 查询某门课程的详细信息
     * @param courseId
     * @return
     */
    CourseDTO getCourseById(Integer courseId);

    /**
     * 查询已登录用户购买的全部课程信息
     * @param userId
     * @return
     */
    List<Course> getCourseByUserId(String userId);


}
