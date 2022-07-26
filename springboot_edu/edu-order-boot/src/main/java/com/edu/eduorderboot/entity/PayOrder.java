package com.edu.eduorderboot.entity;

import lombok.Data;

import javax.persistence.Id;
import java.util.Date;
import java.io.Serializable;

/**
 * 支付订单信息表(PayOrder)实体类
 *
 * @author RG
 * @since 2022-07-26 11:25:24
 */
@Data
public class PayOrder implements Serializable {
    private static final long serialVersionUID = -13287667215938957L;
    /**
     * 主键
     */
    @Id
    private String id;
    /**
     * 订单号(唯一)
     */
    private String orderNo;
    /**
     * 用户ID
     */
    private String userId;
    /**
     * 商品唯一标识(ID)
     */
    private String productId;
    /**
     * 产品名称
     */
    private String productName;
    /**
     * 金额,单位元
     */
    private Double amount;
    /**
     * 商品数量
     */
    private Integer count;
    /**
     * 货币类型，cny-人民币 gbeans-勾豆
     */
    private String currency;
    /**
     * 支付渠道：weChat-微信支付，aliPay-支付宝支付,applePay-苹果支付
     */
    private String channel;
    /**
     * 订单状态：1-未支付 2-支付成功 3-支付失败 -1-订单失效
     */
    private Integer status;
    /**
     * 渠道中的状态码值
     */
    private Integer channelStatus;
    /**
     * 类型 1-购买课程 2-充值
     */
    private Integer orderType;
    /**
     * 支付来源 1-app 2-h5 3-pc
     */
    private Integer source;
    /**
     * 用户支付IP
     */
    private String clientIp;
    /**
     * 购买账号id
     */
    private String buyId;
    /**
     * 外部支付渠道交易号
     */
    private String outTradeNo;
    /**
     * 创建时间
     */
    private Date createdTime;
    /**
     * 更新时间
     */
    private Date updatedTime;
    /**
     * 支付时间
     */
    private Date payTime;
    /**
     * 附加字段，KV json，例如:{"mobile":13020202,"success_url":123}
     */
    private String extra;
    /**
     * 商品订单编号
     */
    private String goodsOrderNo;
    /**
     * 支付所使用的平台：1 拉勾 2 拉勾教育
     */
    private Integer platform;
    /**
     * 微信类型, 参考自lg-wechat-boot项目中的wxinfo
     */
    private Integer wxType;


}

