package com.edu.eduorderboot.entity;

import lombok.Data;

import javax.persistence.Id;
import java.util.Date;
import java.io.Serializable;

/**
 * 支付订单状态日志表(PayOrderRecord)实体类
 *
 * @author RG
 * @since 2022-07-26 11:25:34
 */
@Data
public class PayOrderRecord implements Serializable {
    private static final long serialVersionUID = -51233218421465284L;
    /**
     * ID
     */
    @Id
    private Long id;
    /**
     * 订单号
     */
    private String orderNo;
    /**
     * 操作类型：CREATE|PAY|REFUND...
     */
    private String type;
    /**
     * 原订单状态
     */
    private String fromStatus;
    /**
     * 新订单状态
     */
    private String toStatus;
    /**
     * 实付金额，单位为分
     */
    private Double paidAmount;
    /**
     * 备注
     */
    private String remark;
    /**
     * 操作人
     */
    private String createdBy;
    /**
     * 操作时间
     */
    private Date createdAt;



}

