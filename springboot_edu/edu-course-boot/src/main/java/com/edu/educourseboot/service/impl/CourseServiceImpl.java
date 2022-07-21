package com.edu.educourseboot.service.impl;

import com.edu.educourseboot.entity.Course;
import com.edu.educourseboot.mapper.CourseDao;
import com.edu.educourseboot.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @BelongsProject: springboot_edu
 * @Author: RG
 * @CreateTime: 2022/7/21 9:51 上午
 * @Description:
 */
@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseDao courseDao;

    public List<Course> getAllCourse() {
        return courseDao.getAllCourse();
    }

    @Override
    public List<Course> getCourseByUserId(String userId) {
        return courseDao.getCourseByUserId(userId);
    }

    @Override
    public Course getCourseById(Integer courseId) {
        return courseDao.getCourseById(courseId);
    }
}
