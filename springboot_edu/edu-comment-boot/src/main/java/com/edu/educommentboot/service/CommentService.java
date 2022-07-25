package com.edu.educommentboot.service;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.edu.educommentboot.entity.CourseComment;

import java.util.List;

/**
 * @BelongsProject: springboot_edu
 * @Author: RG
 * @CreateTime: 2022/7/25 1:43 下午
 * @Description:
 */
public interface CommentService {
    /**
     * 保存留言
     * @param courseComment 留言内容对象
     * @return
     */
    Integer saveCourseComment(CourseComment courseComment);

    /**
     * 某个课程的全部留言（分页）
     * @param courseId  课程编号
     * @param offset    数据偏移
     * @param pageSize  每页条目数
     * @return
     */
    List<CourseComment> getCourseCommentByCourseId(Integer courseId, Integer offset, Integer pageSize);

    /**
     * 点赞
     * @param commentId 留言编号
     * @param userId    用户编号
     * @return
     */
    Integer saveFavorite(Integer commentId,Integer userId);

    /**
     * 取消赞
     * @param commentId 留言编号
     * @param userId    用户编号
     * @return
     */
    Integer cancelFavorite(Integer commentId,Integer userId);
}
