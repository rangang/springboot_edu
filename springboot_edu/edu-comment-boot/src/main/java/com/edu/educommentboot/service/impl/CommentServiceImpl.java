package com.edu.educommentboot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.edu.educommentboot.entity.CourseComment;
import com.edu.educommentboot.entity.CourseCommentFavoriteRecord;
import com.edu.educommentboot.mapper.CourseCommentFavoriteRecordMapper;
import com.edu.educommentboot.mapper.CourseCommentMapper;
import com.edu.educommentboot.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @BelongsProject: springboot_edu
 * @Author: RG
 * @CreateTime: 2022/7/25 1:43 下午
 * @Description:
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CourseCommentMapper commentMapper;

    @Autowired
    private CourseCommentFavoriteRecordMapper commentFavoriteRecordMapper;

    @Override
    public Integer saveCourseComment(CourseComment courseComment) {
        return commentMapper.insert(courseComment);
    }

    @Override
    public List<CourseComment> getCourseCommentByCourseId(Integer courseId, Integer offset, Integer pageSize) {
        return commentMapper.getCommentsByCourseId(courseId,offset,pageSize);
    }

    /**
     * 点赞：
     * 先查看当前用户对这条留言是否点过赞，
     * 如果点过：修改is_del状态即可，取消赞
     * 如果没点过：保存一条点赞的信息
     * 最终，更新赞的数量
     * @param commentId 留言编号
     * @param userId    用户编号
     * @return
     */
    @Override
    public Integer saveFavorite(Integer commentId, Integer userId) {
        QueryWrapper<CourseCommentFavoriteRecord> qw = new QueryWrapper<>();
        qw.eq("comment_id", commentId);
        qw.eq("user_id", userId);
        Integer i = commentFavoriteRecordMapper.selectCount(qw);
        int i1 = 0;
        int i2 = 0;
        CourseCommentFavoriteRecord favorite = new CourseCommentFavoriteRecord();
        favorite.setIsDel(0);

        if (i == 0) { //没点过赞
            // 添加赞的信息
            favorite.setCommentId(commentId);
            favorite.setUserId(userId);
            favorite.setCreateTime(new Date());
            favorite.setUpdateTime(new Date());
            i1 = commentFavoriteRecordMapper.insert(favorite);
        } else {
            i1 = commentFavoriteRecordMapper.update(favorite,qw);
        }

        i2 = commentMapper.updateLikeCount(1, commentId);

        if (i1 == 0 || i2 == 0) {
            throw new RuntimeException("点赞失败！");
        }
        return commentId;
    }

    /**
     * 删除点赞的信息（is_del = 1）
     * 更新留言赞的数量-1
     *
     * @param commentId 留言编号
     * @param userId     用户编号
     * @return 0:失败，1：成功
     */
    @Override
    public Integer cancelFavorite(Integer commentId, Integer userId) {
        QueryWrapper<CourseCommentFavoriteRecord> qw = new QueryWrapper<>();
        qw.eq("comment_id", commentId);
        qw.eq("user_id", userId);
        CourseCommentFavoriteRecord favorite = new CourseCommentFavoriteRecord();
        favorite.setIsDel(1); // 1 表示该赞被取消
        Integer i1 = commentFavoriteRecordMapper.update(favorite, qw);

        Integer i2 = commentMapper.updateLikeCount(-1, commentId);

        if (i1 == 0 || i2 == 0) {
            throw new RuntimeException("取消赞失败！");
        }
        return i2;
    }
}
