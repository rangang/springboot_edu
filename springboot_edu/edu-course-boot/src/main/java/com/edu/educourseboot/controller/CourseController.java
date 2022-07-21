package com.edu.educourseboot.controller;

import com.edu.educourseboot.entity.Course;
import com.edu.educourseboot.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @BelongsProject: springboot_edu
 * @Author: RG
 * @CreateTime: 2022/7/21 9:51 上午
 * @Description:
 */
@RestController
@RequestMapping("/course")
@CrossOrigin //跨域
public class CourseController {

    @Autowired
    private CourseService courseService;

    @RequestMapping("/getAllCourse")
    public List<Course> getAllCourse() {

        List<Course> list = courseService.getAllCourse();
        return list;

    }

    @RequestMapping("/getCourseByUserId/{userId}")
    public List<Course> getCourseByUserId(@PathVariable("userId") String userId) {
        List<Course> list = courseService.getCourseByUserId(userId);
        return list;
    }

    @RequestMapping("/getCourseById/{courseId}")
    public Course getCourseById(@PathVariable("courseId") Integer courseId) {
        return courseService.getCourseById(courseId);
    }
}
