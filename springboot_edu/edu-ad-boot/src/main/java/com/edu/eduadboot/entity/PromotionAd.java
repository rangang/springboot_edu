package com.edu.eduadboot.entity;

import lombok.Data;

import javax.persistence.Table;
import java.util.Date;
import java.io.Serializable;

/**
 * 广告表(PromotionAd)实体类
 *
 * @author RG
 * @since 2022-07-18 16:28:53
 */
@Data
@Table(name = "promotion_ad")
public class PromotionAd implements Serializable {
    private static final long serialVersionUID = -34154379420563072L;
    
    private Integer id;
    /**
     * 广告名
     */
    private String name;
    /**
     * 广告位id
     */
    private Integer spaceId;
    /**
     * 精确搜索关键词
     */
    private String keyword;
    /**
     * 静态广告的内容
     */
    private String htmlContent;
    /**
     * 文字一
     */
    private String text;
    /**
     * 链接一
     */
    private String link;
    /**
     * 开始时间
     */
    private Date startTime;
    /**
     * 结束时间
     */
    private Date endTime;
    
    private Date createTime;
    
    private Date updateTime;
    
    private Integer status;
    /**
     * 优先级
     */
    private Integer priority;
    
    private String img;


}

