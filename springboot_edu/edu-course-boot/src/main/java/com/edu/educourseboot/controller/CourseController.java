package com.edu.educourseboot.controller;

import com.edu.educourseboot.entity.Course;
import com.edu.educourseboot.entity.CourseDTO;
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
    public List<CourseDTO> getAllCourse() {

        List<CourseDTO> list = courseService.getAllCourse();
        return list;

    }

    @RequestMapping("/getCourseById/{courseId}")
    public CourseDTO getCourseById(@PathVariable("courseId") Integer courseId) {
        return courseService.getCourseById(courseId);
    }

    @RequestMapping("/getCourseByUserId/{userId}")
    public List<CourseDTO> getCourseByUserId(@PathVariable("userId") Integer userId) {
        List<CourseDTO> list = courseService.getCourseByUserId(userId);
        return list;
    }
}
