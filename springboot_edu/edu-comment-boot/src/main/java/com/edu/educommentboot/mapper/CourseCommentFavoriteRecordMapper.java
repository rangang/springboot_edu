package com.edu.educommentboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.edu.educommentboot.entity.CourseCommentFavoriteRecord;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @BelongsProject: springboot_edu
 * @Author: RG
 * @CreateTime: 2022/7/25 1:55 下午
 * @Description:
 */
@Component
public interface CourseCommentFavoriteRecordMapper extends BaseMapper<CourseCommentFavoriteRecord> {

    @Select({"SELECT * FROM course_comment_favorite_record WHERE comment_id = #{commentId} and is_del = 0"})
    List<CourseCommentFavoriteRecord> getFavorites(Integer commentId);

}
