package com.edu.eduadboot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.edu.eduadboot.entity.PromotionAd;
import com.edu.eduadboot.mapper.AdMapper;
import com.edu.eduadboot.service.AdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @BelongsProject: springboot_edu
 * @Author: RG
 * @CreateTime: 2022/7/18 4:19 下午
 * @Description:
 */
@Service
public class AdServiceImpl implements AdService {

    @Autowired
    private AdMapper adMapper;

    @Override
    public List<PromotionAd> getAdsBySpaceId(Integer spaceId) {
        QueryWrapper<PromotionAd> qw = new QueryWrapper<>();
        qw.eq("space_id",spaceId);
        return adMapper.selectList(qw);
    }
}
