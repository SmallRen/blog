package com.renbaojia.blog.controller;

import com.renbaojia.blog.model.User;
import com.renbaojia.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController extends BaseController{

    @Autowired
    UserService userService;

    @ResponseBody
    @RequestMapping("/testSql")
    private boolean login(User user) {
        return userService.login(user);

    }

}
