package com.edu.eduadboot.entity;

import lombok.Data;

import java.util.Date;
import java.io.Serializable;

/**
 * 广告区域表(PromotionSpace)实体类
 *
 * @author RG
 * @since 2022-07-18 16:29:45
 */
@Data
public class PromotionSpace implements Serializable {
    private static final long serialVersionUID = -85844197793397548L;
    
    private Integer id;
    /**
     * 名称
     */
    private String name;
    /**
     * 广告位key
     */
    private String spaceKey;
    
    private Date createTime;
    
    private Date updateTime;
    
    private Integer isDel;

}

