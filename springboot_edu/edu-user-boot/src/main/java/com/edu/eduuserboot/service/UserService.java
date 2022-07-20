package com.edu.eduuserboot.service;

/**
 * @BelongsProject: springboot_edu
 * @Author: RG
 * @CreateTime: 2022/7/20 5:22 下午
 * @Description:
 */
public interface UserService {

    /**
     * 修改用户信息
     * @param userId    用户编号
     * @param newName   新昵称
     * @param imgFileId 新的头像地址
     */
    void updateUser(Integer userId,String newName,String imgFileId);

    /**
     * 修改密码
     * @param userId    用户编号
     * @param newPwd    新密码
     */
    void updatePassword(Integer userId,String newPwd);

}
