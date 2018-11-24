package com.renbaojia.blog.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.renbaojia.blog.model.User;
import com.renbaojia.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
/**
 * @Created with IntelliJ IDEA.
 * @Author renbaojia
 * @Date: 2018-11-25 04:27
 * @version: 1.0
 * @Description: user操作控制器
 */
@Controller
public class UserController extends BaseController{

    @Autowired
    UserService userService;

    @ResponseBody
    @RequestMapping("/testSql")
    public boolean login(User user) {
        return userService.login(user);

    }
    @ResponseBody
    @RequestMapping("/allUser")
    public PageInfo<User> allUser(@RequestParam Integer pageSize) {
        PageHelper.startPage(pageSize,2);
        List<User> all = userService.all(pageSize);
        return new PageInfo<>(all);

    }

}
