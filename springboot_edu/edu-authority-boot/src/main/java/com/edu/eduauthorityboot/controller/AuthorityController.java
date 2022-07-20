package com.edu.eduauthorityboot.controller;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.edu.eduauthorityboot.entity.UserDTO;
import com.edu.eduauthorityboot.service.UserService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @BelongsProject: springboot_edu
 * @Author: RG
 * @CreateTime: 2022/7/19 3:35 下午
 * @Description:
 */
@RestController
@RequestMapping("/user")
@CrossOrigin
public class AuthorityController {

    @Autowired
    private UserService userService;

    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    @RequestMapping("/login")
    public UserDTO login(String phone,String password) {
        return userService.login(phone,password);
    }

    @RequestMapping("/checkToken")
    public UserDTO checkToken(String token) {
        return userService.checkToken(token);
    }

    @RequestMapping("/logout")
    public void logout(String token) {
        // 将redis中的token删除
        System.out.println("token = " + token);
        redisTemplate.delete(token);
    }

    @Value("${ali.sms.signName}")
    private String signName;
    @Value("${ali.sms.templateCode}")
    private String templateCode;
    @Value("${ali.sms.accessKeyId}")
    private String accessKeyId;
    @Value("${ali.sms.accessKeySecret}")
    private String accessKeySecret;

    @RequestMapping("/sendSms")
    public Object sendSms(String phoneNumber) {
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);

        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        request.setSysMethod(MethodType.POST);
        request.setSysDomain("dysmsapi.aliyuncs.com");
        request.setSysVersion("2017-05-25");
        request.setSysAction("SendSms");
        request.putQueryParameter("RegionId", "cn-hangzhou");
        request.putQueryParameter("PhoneNumbers", phoneNumber);
        request.putQueryParameter("SignName", signName);
        request.putQueryParameter("TemplateCode", templateCode);
        String vcode = "";
        for(int i = 0; i<6; i++){
            vcode = vcode + (int)(Math.random()*9);
        }
        request.putQueryParameter("TemplateParam", "{\"code\":\"" + vcode + "\"}");
        try {
            CommonResponse response = client.getCommonResponse(request);
            String jsonStr = response.getData();
            System.out.println("jsonStr = " + jsonStr);
            JSONObject jsonObject = JSONObject.parseObject(jsonStr);
            if("OK".equals(jsonObject.get("Message"))){
                // 返回 手机号和验证码的 json给前端
                jsonObject.put("phoneNumber", phoneNumber);
                jsonObject.put("smsCode", vcode);
                System.out.println("验证码 = " + jsonObject);
                return jsonObject;
            }
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
        return null;

    }

    @RequestMapping("/loginPhoneSms")
    public UserDTO loginPhoneSms(String phoneNumber) {
        return userService.loginPhoneSms(phoneNumber);
    }

}
