package com.edu.educommentboot.controller;

import com.edu.educommentboot.entity.CourseComment;
import com.edu.educommentboot.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * @BelongsProject: springboot_edu
 * @Author: RG
 * @CreateTime: 2022/7/25 1:41 下午
 * @Description:
 */
@RestController
@CrossOrigin
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @RequestMapping("/saveCourseComment")
    public Integer saveCourseComment(Integer courseId,Integer userId,String userName,String comment) throws UnsupportedEncodingException {

        CourseComment courseComment = new CourseComment();
        courseComment.setCourseId(courseId); // 课程编号
        courseComment.setSectionId(0); // 章节编号
        courseComment.setLessonId(0);// 小节编号
        courseComment.setUserId(userId); // 用户编号
        courseComment.setUserName(userName); // 用户昵称
        courseComment.setParentId(0); //没有父id
        courseComment.setComment(comment);// 留言内容
        courseComment.setType(0); // 0用户留言
        courseComment.setLastOperator(userId); //最后操作的用户编号
        Integer i = commentService.saveCourseComment(courseComment);
        return i;
    }

    @RequestMapping("/getCourseCommentByCourseId/{courseId}/{pageIndex}/{pageSize}")
    public List<CourseComment> getCourseCommentByCourseId(@PathVariable("courseId") Integer courseId, @PathVariable("pageIndex") Integer pageIndex, @PathVariable("pageSize") Integer pageSize) {

        Integer offset = (pageIndex - 1) * pageSize;
        List<CourseComment> list = commentService.getCourseCommentByCourseId(courseId, offset, pageSize);
        return list;

    }

    @RequestMapping("/saveFavorite/{commentId}/{userId}")
    public Integer saveFavorite(@PathVariable("commentId") Integer commentId,@PathVariable("userId") Integer userId) {
        Integer i = commentService.saveFavorite(commentId, userId);
        return i;
    }

    @RequestMapping("/cancelFavorite/{commentId}/{userId}")
    public Integer cancelFavorite(@PathVariable("commentId") Integer commentId,@PathVariable("userId") Integer userId) {
        Integer i = commentService.cancelFavorite(commentId, userId);
        return i;
    }

}
