package com.edu.eduauthorityboot.tools;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.edu.eduauthorityboot.entity.User;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @BelongsProject: springboot_edu
 * @Author: RG
 * @CreateTime: 2022/7/19 1:29 下午
 * @Description:
 */
public class JwtUtil {
    private static final long EXPIRE_TIME = 15 * 60 * 1000;
    private static final String TOKEN_SECRET = "laosunshigedashuaige666";  //secret秘钥：自定义

    /**
     * 生成签名，15分钟过期
     * @param **username**
     * @param **password**
     * @return
     */
    public static String createToken(User user) {
        try {
            // 设置过期时间
            Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
            // 私钥和加密算法
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            // 设置头部信息
            Map<String, Object> header = new HashMap<>(2);
            header.put("typ", "JWT");
            header.put("alg", "HS256");
            // 返回token字符串
            return JWT.create()
                    .withHeader(header)  // 第一部分
                    .withClaim("nickname", user.getName())  // 第二部分
                    .withClaim("userid", user.getId())
                    .withClaim("password", user.getPassword())
                    .withClaim("portrait", user.getPortrait())
                    .withExpiresAt(date)    //设置过期时间
                    .sign(algorithm);   // 第三部分
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 检验token是否正确
     * @param **token**
     * @return
     */
    public static int isVerify(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET); //使用HMAC256加密算法，生成签名
            JWTVerifier verifier = JWT.require(algorithm).build();
            verifier.verify(token); // 解析token
            return 0;// 校验通过
        } catch (TokenExpiredException e) {
            e.printStackTrace();
            System.out.println("令牌过期");
            return 1; // 令牌过期
        } catch (JWTDecodeException e) {
            e.printStackTrace();
            System.out.println("令牌格式错误！或为空令牌！");
            return 2;// 校验失败,token令牌就是错误的
        } catch (JWTVerificationException e) {
            e.printStackTrace();
            System.out.println("校验失败,token令牌就是错误的");
            return 3;// 校验失败,token令牌就是错误的
        }
    }
    /**
     *从token解析出 用户编号 信息
     * @param token
     * @return
     */
    public static int parseTokenUserid(String token) {
        DecodedJWT jwt = JWT.decode(token);
        return jwt.getClaim("userid").asInt();
    }

    /**
     *从token解析出 昵称 信息
     * @param token
     * @return
     */
    public static String parseTokenNickname(String token) {
        DecodedJWT jwt = JWT.decode(token);
        return jwt.getClaim("nickname").asString();
    }

    /**
     *从token解析出 头像 信息
     * @param token
     * @return
     */
    public static String parseTokenPortrait(String token) {
        DecodedJWT jwt = JWT.decode(token);
        return jwt.getClaim("portrait").asString();
    }

    /**
     *从token解析出 密码 信息
     * @param token
     * @return
     */
    public static String parseTokenPassword(String token) {
        DecodedJWT jwt = JWT.decode(token);
        return jwt.getClaim("password").asString();
    }
}
