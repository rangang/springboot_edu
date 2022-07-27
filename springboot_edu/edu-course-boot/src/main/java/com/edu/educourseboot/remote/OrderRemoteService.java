package com.edu.educourseboot.remote;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @BelongsProject: springboot_edu
 * @Author: RG
 * @CreateTime: 2022/7/27 2:16 下午
 * @Description:
 */
@Component
@FeignClient(name = "edu-order-boot",path = "order")
public interface OrderRemoteService {
    @GetMapping("/getOkOrderCourseIds")
    List<Object> getOkOrderCourseIds(@RequestParam("userId") Integer userId);
}
