package com.renbaojia.blog.service.impl;


import com.renbaojia.blog.dao.UserMapper;
import com.renbaojia.blog.model.User;
import com.renbaojia.blog.model.UserExample;
import com.renbaojia.blog.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Created with IntelliJ IDEA.
 * @Author renbaojia
 * @Date: 2018-11-25 04:27
 * @version: 1.0
 * @Description: user实现类
 */
@Service
public class UserImpl implements UserService {
    protected static final Logger logger = LoggerFactory.getLogger(UserImpl.class);
    @Autowired
    UserMapper userMapper;

    @Override
    public boolean login(User user) {
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andUsernameEqualTo(user.getUsername());
        criteria.andPasswordEqualTo(user.getPassword());
        List<User> users = userMapper.selectByExample(userExample);
        logger.debug(users.size() + "");
        return users.size() == 1 ? true : false;
    }

    @Override
    public List<User> all(Integer pageSize) {
        return userMapper.selectByExample(null);

    }

    @Override
    public User getUser(String username) {
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andUsernameEqualTo(username);
        List<User> users = userMapper.selectByExample(userExample);
        if (users.size() == 1) {
            return users.get(0);
        }
        return null;
    }
}
