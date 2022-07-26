package com.edu.edupayboot.commons;

/**
 * @BelongsProject: springboot_edu
 * @Author: RG
 * @CreateTime: 2022/7/26 10:13 上午
 * @Description:
 */
public class PayConfig {
    //企业公众号ID
    public static String appId="1";
    //财付通平台的商户帐号
    public static String partner="2";
    //财付通平台的商户密钥
    public static String partnerKey="3";
    //回调URL
    public static String notifyUrl="http://localhost:8006/pay/wxCallback";
}
