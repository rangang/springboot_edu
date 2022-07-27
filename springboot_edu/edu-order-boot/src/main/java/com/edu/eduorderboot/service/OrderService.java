package com.edu.eduorderboot.service;

import com.edu.eduorderboot.entity.PayOrder;
import com.edu.eduorderboot.entity.PayOrderRecord;
import com.edu.eduorderboot.entity.UserCourseOrder;

import java.util.List;

/**
 * @BelongsProject: springboot_edu
 * @Author: RG
 * @CreateTime: 2022/7/26 10:46 上午
 * @Description:
 */
public interface OrderService {

    /**
     * 生成订单
     * @param orderNo   订单编号
     * @param userId    用户编号
     * @param courseId  课程编号
     * @param activityCourseId  活动课程编号
     * @param sourceType    订单来源类型
     */
    void saveOrder(String orderNo, String userId, String courseId, String activityCourseId, String sourceType);

    /**
     * 修改订单状态
     * @param orderNo   订单编号
     * @param status    订单状态 0已创建 10已支付 20已完成 30已取消 40已过期
     */
    Integer updateOrder(String orderNo,Integer status);

    /**
     * 删除订单
     * @param orderNo   订单编号
     * @return
     */
    Integer deleteOrder(String orderNo);

    /**
     * 查询登录用户的全部订单
     * @param userId    用户编号
     * @return
     */
    List<UserCourseOrder> getOkOrderCourseIds(Integer userId);

    /**
     * 保存订单信息
     * @param payOrder
     */
    void saveOrderInfo(PayOrder payOrder);

    /**
     * 保存订单记录信息
     * @param orderRecord
     */
    void saveOrderRecord(PayOrderRecord orderRecord);

}
