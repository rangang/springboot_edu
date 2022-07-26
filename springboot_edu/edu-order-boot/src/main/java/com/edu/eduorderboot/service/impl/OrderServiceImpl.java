package com.edu.eduorderboot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.edu.eduorderboot.entity.PayOrder;
import com.edu.eduorderboot.entity.PayOrderRecord;
import com.edu.eduorderboot.entity.UserCourseOrder;
import com.edu.eduorderboot.mapper.OrderMapper;
import com.edu.eduorderboot.mapper.PayOrderMapper;
import com.edu.eduorderboot.mapper.PayOrderRecordMapper;
import com.edu.eduorderboot.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @BelongsProject: springboot_edu
 * @Author: RG
 * @CreateTime: 2022/7/26 10:46 上午
 * @Description:
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private PayOrderMapper payOrderMapper;
    @Autowired
    private PayOrderRecordMapper payOrderRecordMapper;


    @Override
    public void saveOrder(String orderNo, String userId, String courseId, String activityCourseId, String sourceType) {

        UserCourseOrder order = new UserCourseOrder();
        order.setOrderNo(orderNo);
        order.setUserId(Integer.parseInt(userId));
        order.setCourseId(Integer.parseInt(courseId));
        order.setActivityCourseId(Integer.parseInt(activityCourseId));
        order.setSourceType(Integer.parseInt(sourceType));
        order.setStatus(0);
        order.setIsDel(0);
        order.setCreateTime(new Date());
        order.setUpdateTime(new Date());

        orderMapper.insert(order);
    }

    @Override
    public Integer updateOrder(String orderNo, Integer status) {
        UserCourseOrder order = new UserCourseOrder();
        order.setStatus(status);
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("order_no",orderNo);

        return orderMapper.update(order,queryWrapper);
    }

    @Override
    public Integer deleteOrder(String orderNo) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("order_no",orderNo);
        return orderMapper.delete(queryWrapper);
    }

    @Override
    public List<UserCourseOrder> getOrderByUserId(String userId) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("user_id",userId);
        return orderMapper.selectList(queryWrapper);
    }

    @Override
    public void saveOrderInfo(PayOrder payOrder) {
        payOrderMapper.insert(payOrder);
    }

    @Override
    public void saveOrderRecord(PayOrderRecord orderRecord) {
        payOrderRecordMapper.insert(orderRecord);
    }
}
