package com.renbaojia.blog.controller;


import com.qq.connect.QQConnectException;
import com.qq.connect.oauth.Oauth;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @version 1.0
 * @Created with IntelliJ IDEA.
 * @Author renbaojia
 * @Date 2018/11/25 19:04
 * @Description:
 */
@Controller
public class LoginController {


    @GetMapping("/qqLogin")
    public String qqLogin(HttpServletRequest request) {
        try {
            return "redirect:" + new Oauth().getAuthorizeURL(request);
        } catch (QQConnectException e) {
            e.printStackTrace();
        }
        return null;
    }


}
