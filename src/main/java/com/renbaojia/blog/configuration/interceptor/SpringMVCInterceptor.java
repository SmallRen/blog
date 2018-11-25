package com.renbaojia.blog.configuration.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by smallRen on 2017/11/2.
 */

public class SpringMVCInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        String requestURI = httpServletRequest.getRequestURI();
        System.out.println(requestURI);
        StringBuffer requestURI1 = httpServletRequest.getRequestURL();
        System.out.println(requestURI1);
        //创建session会话，把sessionId添加进session,用于websocket查找用户
        HttpSession session = httpServletRequest.getSession();
        String requestedSessionId = httpServletRequest.getRequestedSessionId();
        System.out.println("拦截器获取的"+requestedSessionId);
        session.setAttribute("SESSION_USERNAME", requestedSessionId);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
