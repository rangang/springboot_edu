package com.edu.eduorderboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.edu.eduorderboot.entity.UserCourseOrder;
import org.springframework.stereotype.Component;

/**
 * @BelongsProject: springboot_edu
 * @Author: RG
 * @CreateTime: 2022/7/26 10:42 上午
 * @Description:
 */
@Component
public interface OrderMapper extends BaseMapper<UserCourseOrder> {
}
