package com.luo.usercenter.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.luo.usercenter.model.User;
import org.apache.ibatis.annotations.Param;

import javax.servlet.http.HttpServletRequest;

/**
 * @author lkx
 * {@code @description} 用户服务
 * {@code @description} 针对表【user】的数据库操作Service
 * {@code @createDate} 2022-10-11 16:13:14
 */
public interface UserService extends IService<User> {
    /**
     * 用户注册
     *
     * @param userAccount   用户账户
     * @param userPassword  用户密码
     * @param checkPassword 校验密码
     * @param planetCode    星球编号
     * @return 新用户  id
     */
    long userRegister(String userAccount, String userPassword, String checkPassword, String planetCode);

    /**
     * 登录
     * @param userAccount 用户账号
     * @param userPassword 用户密码
     * @param request 请求
     * @return 返回脱敏的用户信息
     */
    User userLogin(@Param("userAccount") String userAccount, @Param("userPassword") String userPassword, @Param("request") HttpServletRequest request);

    /**
     * 用户脱敏
     * @param originUser 用户对象
     * @return 脱敏的用户对象
     */
    User getSafetyUser(User originUser);

    /**
     * 用户注销
     * @param request 请求
     * @return int 返回数字
     */
    int userLogout(HttpServletRequest request);
}
