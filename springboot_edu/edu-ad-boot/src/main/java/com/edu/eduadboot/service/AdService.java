package com.edu.eduadboot.service;

import com.edu.eduadboot.entity.PromotionAd;

import java.util.List;

/**
 * @BelongsProject: springboot_edu
 * @Author: RG
 * @CreateTime: 2022/7/18 4:19 下午
 * @Description:
 */
public interface AdService {
    /**
     * 根据广告位ID查询广告
     * @param spaceId   广告位ID
     * @return
     */
    List<PromotionAd> getAdsBySpaceId(Integer spaceId);

}
