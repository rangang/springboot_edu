package com.edu.educourseboot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.edu.educourseboot.entity.*;
import com.edu.educourseboot.mapper.*;
import com.edu.educourseboot.service.CourseService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @BelongsProject: springboot_edu
 * @Author: RG
 * @CreateTime: 2022/7/21 9:51 上午
 * @Description:
 */
@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseMapper courseMapper;

    @Autowired
    private TeacherMapper teacherMapper;

    @Autowired
    private LessonMapper lessonMapper;

    @Autowired
    private SectionMapper sectionMapper;

    @Autowired
    private MediaMapper mediaMapper;

    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    public List<CourseDTO> getAllCourse() {

        // 将redis内存中的序列化的集合名称用String重新命名（增加可读性）
        RedisSerializer rs = new StringRedisSerializer();
        redisTemplate.setKeySerializer(rs);

        // 1.先去redis中查询
        System.out.println("***查询redis***");
        List<CourseDTO> list = (List<CourseDTO>) redisTemplate.opsForValue().get("allCourse");
        // 2.redis中没有，才会去mysql查询
        if (null == list) {
            synchronized (this) {
                list = (List<CourseDTO>) redisTemplate.opsForValue().get("allCourse");
                if (null == list) {
                    System.out.println("===查询mysql===");
                    List<Course> courses = getInitCourse();
                    list = new ArrayList<>();
                    for (Course course : courses) {
                        CourseDTO dto = new CourseDTO();
                        // course将属性全部赋给courseDTO对象
                        BeanUtils.copyProperties(course,dto);
                        list.add(dto);
                        // 设置老师
                        setTeacher(dto);
                        // 设置前两节课
                        setTop2Lesson(dto);
                    }
                    redisTemplate.opsForValue().set("allCourse",list,10, TimeUnit.MINUTES);
                }
            }

        }

        return list;
    }

    /**
     * 初始化基本的全部课程
     * @return
     */
    private List<Course> getInitCourse() {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("status",1); // 已上架
        queryWrapper.eq("is_del",Boolean.FALSE); // 未删除
        queryWrapper.orderByDesc("sort_num"); // 排序
        return courseMapper.selectList(queryWrapper);
    }

    /**
     * 基本的老师查询
     * @param courseDTO
     */
    private void setTeacher(CourseDTO courseDTO) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("course_id",courseDTO.getId()); // 一个课程一个老师
        queryWrapper.eq("is_del",Boolean.FALSE); // 删除
        Teacher teacher = teacherMapper.selectOne(queryWrapper);
        courseDTO.setTeacher(teacher);
    }

    /**
     * 前两节课
     * @param courseDTO
     */
    private void setTop2Lesson(CourseDTO courseDTO) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("course_id",courseDTO.getId());
        queryWrapper.eq("is_del",Boolean.FALSE);
        queryWrapper.orderByAsc("section_id","order_num");
        queryWrapper.last("limit 0, " + 2);
        List<CourseLesson> list = lessonMapper.selectList(queryWrapper);
        courseDTO.setLessons(list);
    }

    @Override
    public CourseDTO getCourseById(Integer courseId) {
        // 根据课程ID获取课程的基本信息
        QueryWrapper<Course> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id",courseId);
        Course course = courseMapper.selectOne(queryWrapper);
        CourseDTO courseDTO = new CourseDTO();
        BeanUtils.copyProperties(course,courseDTO);
        // 关联老师
        setTeacher(courseDTO);
        // 关联章节
        List<SectionDTO> sectionDTOS = getCourseSection(courseDTO);
        courseDTO.setCourseSections(sectionDTOS);

        return courseDTO;
    }

    /**
     * 关联章节
     * @param courseDTO
     * @return
     */
    private List<SectionDTO> getCourseSection(CourseDTO courseDTO) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("course_id",courseDTO.getId());
        queryWrapper.eq("is_del",Boolean.FALSE);
        queryWrapper.eq("status",2);
        queryWrapper.orderByAsc("order_num");
        // 基本的章节信息
        List<CourseSection> list = sectionMapper.selectList(queryWrapper);
        // 关联的章节信息
        List<SectionDTO> sectionDTOS = new ArrayList<>();
        for (CourseSection section : list) {
            SectionDTO sectionDTO = new SectionDTO();
            BeanUtils.copyProperties(section,sectionDTO);

            // 清除条件
            queryWrapper.clear();
            queryWrapper.eq("course_id",courseDTO.getId());
            queryWrapper.eq("is_del",Boolean.FALSE);
            queryWrapper.orderByDesc("order_num");
            // 某章节的全部小节(基本信息)
            List<CourseLesson> lessons = lessonMapper.selectList(queryWrapper);
            // 某章节的全部小节（关联信息）
            List<LessonDTO> lessonDTOS = new ArrayList<>();
            for (CourseLesson lesson : lessons) {
                LessonDTO lessonDTO = new LessonDTO();
                BeanUtils.copyProperties(lesson,lessonDTO);
                // 设置每节课对应的视频
                setMedia(lessonDTO);
                lessonDTOS.add(lessonDTO);
            }
            // 章节关联所有小节
            sectionDTO.setCourseLessons(lessonDTOS);
            // 某个章节放入到章节集合
            sectionDTOS.add(sectionDTO);
        }

        return sectionDTOS;
    }

    /**
     * 设置每节课的视频
     * @param lessonDTO
     */
    private void setMedia(LessonDTO lessonDTO) {
        QueryWrapper queryWrapper = new QueryWrapper();
        // 一节课，一个视频
        queryWrapper.eq("lesson_id",lessonDTO.getId());
        // 未删除
        queryWrapper.eq("is_del",Boolean.FALSE);
        CourseMedia media = mediaMapper.selectOne(queryWrapper);
        lessonDTO.setCourseMedia(media);
    }

    @Override
    public List<Course> getCourseByUserId(String userId) {

        return null;
    }


}
