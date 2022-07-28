package com.edu.eduorderboot.rabbit;

import com.edu.eduorderboot.entity.SmsVo;
import com.edu.eduorderboot.sms.SmsService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @BelongsProject: springboot_edu
 * @Author: RG
 * @CreateTime: 2022/7/28 11:02 上午
 * @Description:
 */
@Component
public class OrderRever {

    @Autowired
    private SmsService smsService;

    @RabbitListener(queues = "${spring.rabbitmq.queue}")
    public void process(SmsVo smsVo) {

        System.out.println("得到通知，开始发送 = " + smsVo.getCourseName());

        // 调用发送短信
        smsService.sendSms(smsVo.getPhone());

    }

}
