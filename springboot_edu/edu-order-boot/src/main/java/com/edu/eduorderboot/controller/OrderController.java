package com.edu.eduorderboot.controller;

import com.edu.eduorderboot.entity.PayOrder;
import com.edu.eduorderboot.entity.PayOrderRecord;
import com.edu.eduorderboot.entity.UserCourseOrder;
import com.edu.eduorderboot.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * @BelongsProject: springboot_edu
 * @Author: RG
 * @CreateTime: 2022/7/26 10:45 上午
 * @Description:
 */
@RestController
@RequestMapping("/order")
@CrossOrigin
public class OrderController {

    @Autowired
    private OrderService orderService;

    @RequestMapping("/saveOrder")
    public String saveOrder(String orderNo, String userId, String courseId, String activityCourseId, String sourceType,Double price) {
        // 保存支付订单
        orderService.saveOrder(orderNo,userId,courseId,activityCourseId,sourceType);
        // 保存订单的记录信息
        PayOrderRecord record = new PayOrderRecord();
        record.setOrderNo(orderNo);
        record.setType("CREATE");
        record.setFromStatus("0");
        record.setToStatus("1");
        record.setPaidAmount(price);
        record.setCreatedBy(userId);
        record.setCreatedAt(new Date());
        System.out.println("创建订单记录 = " + orderNo);
        orderService.saveOrderRecord(record);

        return orderNo;
    }

    @RequestMapping("/updateOrder")
    public Integer updateOrder(String orderNo, Integer status, String userId, String courseId, String courseName, Double price) {
        Integer integer = orderService.updateOrder(orderNo, status);
        if (integer == 1) {
            // 创建支付成功的订单信息
            PayOrder payOrder = new PayOrder();
            payOrder.setOrderNo(orderNo);
            payOrder.setUserId(userId);
            payOrder.setProductId(courseId);
            payOrder.setProductName(courseName);
            payOrder.setAmount(price);
            payOrder.setCount(1);
            payOrder.setCurrency("cny");
            payOrder.setChannel("weChat");
            payOrder.setStatus(2);
            payOrder.setOrderType(1);
            payOrder.setSource(3);
            payOrder.setClientIp("192.168.1.1");
            payOrder.setCreatedTime(new Date());
            payOrder.setUpdatedTime(new Date());

            orderService.saveOrderInfo(payOrder);

            // 记录支付操作的日志
            // 保存订单的记录信息
            PayOrderRecord record = new PayOrderRecord();
            record.setOrderNo(orderNo);
            record.setType("PAY");
            record.setFromStatus("1");
            record.setToStatus("2");
            record.setPaidAmount(price);
            record.setCreatedBy(userId);
            record.setCreatedAt(new Date());
            System.out.println("创建订单记录 = " + orderNo);
            orderService.saveOrderRecord(record);

        }
        return integer;
    }

    @RequestMapping("/deleteOrder/{orderNo}")
    public Integer deleteOrder(@PathVariable("orderNo") String orderNo) {
        return orderService.deleteOrder(orderNo);
    }

    @RequestMapping("/getOrderByUserId/{userId}")
    public List<UserCourseOrder> getOrderByUserId(@PathVariable("userId") String userId) {
        return orderService.getOrderByUserId(userId);
    }

}
