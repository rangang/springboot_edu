package com.edu.educommentboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.edu.educommentboot.entity.CourseComment;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @BelongsProject: springboot_edu
 * @Author: RG
 * @CreateTime: 2022/7/25 3:39 下午
 * @Description:
 */
@Component
public interface CourseCommentMapper extends BaseMapper<CourseComment> {

    /**
     * 某个课程的全部留言（分页）
     * @param courseId 课程编号
     * @param offset 数据偏移
     * @param pageSize 每页条目数
     * @return 留言集合
     */
    @Select({"SELECT\n" +
            "        id,`course_id`,`section_id`,`lesson_id`,user_id,`user_name`,`parent_id`,`is_top`,`comment`,`like_count`,`is_reply`,`type`,`status`,create_time ,update_time ,is_del,`last_operator`,`is_notify`,`mark_belong`,`replied` \n" +
            "        FROM course_comment \n" +
            "        WHERE is_del = 0\n" +
            "        AND course_id = #{courseId}\n" +
            "        ORDER BY is_top DESC , like_count DESC , create_time DESC\n" +
            "        LIMIT #{offset}, #{pageSize}"})
    @Results({
            @Result(column = "id",property = "id"),
            @Result(column = "id" , property = "favoriteRecords", many = @Many(select = "com.edu.educommentboot.mapper.CourseCommentFavoriteRecordMapper.getFavorites"))
    })
    List<CourseComment> getCommentsByCourseId(@Param("courseId") Integer courseId, @Param("offset") Integer offset, @Param("pageSize") Integer pageSize);

    /**
     * 更新点赞的数量
     * @param x +1的话，赞的数量增加，-1的话，赞的数量减少
     * @param commentId 某条留言的编号
     * @return 0：保存失败，1：保存成功
     */
    @Update({"update course_comment set like_count = like_count + #{x} where id = #{commentId}"})
    Integer updateLikeCount(@Param("x") Integer x, @Param("commentId") Integer commentId);

}
