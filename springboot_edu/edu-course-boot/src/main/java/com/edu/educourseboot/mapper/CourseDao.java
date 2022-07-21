package com.edu.educourseboot.mapper;

import com.edu.educourseboot.entity.Course;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @BelongsProject: distributed_edu
 * @Author: RG
 * @CreateTime: 2022/7/11 3:02 下午
 * @Description:
 */
@Component
public interface CourseDao {

    /**
     * 查询全部课程信息
     * @return
     */
    List<Course> getAllCourse();

    /**
     * 查询已登录用户购买的全部课程信息
     * @param userId
     * @return
     */
    List<Course> getCourseByUserId(String userId);

    /**
     * 查询某门课程的详细信息
     * @param courseId
     * @return
     */
    Course getCourseById(Integer courseId);


}
