package com.renbaojia.blog.service;

import com.renbaojia.blog.model.User;

import java.util.List;

/**
 * @Created with IntelliJ IDEA.
 * @Author renbaojia
 * @Date: 2018-11-25 04:27
 * @version: 1.0
 * @Description: user接口类
 */
public interface UserService {
    /**
     * 用户登录
     *
     * @param user
     * @return
     */
    public boolean login(User user);

    /**
     * 返回所有用户信息
     *
     * @param pageSize
     * @return
     */
    public List<User> all(Integer pageSize);

    /**查找用户是否存在
     * @param username
     * @return
     */
    User getUser(String username);

    /**
     * 根据openid查询用户是否存在
     * @param openID
     * @return
     */
    User selectByOpenID(String openID);
}
