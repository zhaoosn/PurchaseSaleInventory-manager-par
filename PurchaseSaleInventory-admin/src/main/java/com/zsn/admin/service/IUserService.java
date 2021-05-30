package com.zsn.admin.service;

import com.zsn.admin.pojo.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author zhaoosn
 * @since 2021-05-30
 */
public interface IUserService extends IService<User> {

    /**
     * 用户登录方法
     * @param userName
     * @param password
     * @return
     */
    User login(String userName, String password);

    /**
     * 根据用户名查询用户记录
     * @param userName
     * @return
     */
    public User findUserByUserName(String userName);

    void updateUserInfo(User user);

    public void updateUserPassword(String userName, String oldPassword, String newPassword, String confirmPassword);
}
