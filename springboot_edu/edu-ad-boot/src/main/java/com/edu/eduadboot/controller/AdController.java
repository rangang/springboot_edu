package com.edu.eduadboot.controller;

import com.edu.eduadboot.entity.PromotionAd;
import com.edu.eduadboot.service.AdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @BelongsProject: springboot_edu
 * @Author: RG
 * @CreateTime: 2022/7/18 4:18 下午
 * @Description:
 */
@RestController
@RequestMapping("/ad")
@CrossOrigin    // 解决跨域
public class AdController {

    @Autowired
    private AdService adService;

    @RequestMapping("/getAdsBySpaceId/{spaceId}")
    public List<PromotionAd> getAdsBySpaceId(@PathVariable("spaceId") Integer spaceId) {
        return adService.getAdsBySpaceId(spaceId);
    }

}
