package com.renbaojia.blog.service.impl;

import com.renbaojia.blog.dao.UserMapper;
import com.renbaojia.blog.model.User;
import com.renbaojia.blog.model.UserExample;
import com.renbaojia.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserImpl implements UserService {
    @Autowired
    UserMapper userMapper;
    @Override
    public boolean login(User user) {
        UserExample userExample=new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andUsernameEqualTo(user.getUsername());
        criteria.andPasswordEqualTo(user.getPassword());
        List<User> users = userMapper.selectByExample(userExample);
        System.out.println(users.size());
        return users.size()==1?true:false;
    }
}
