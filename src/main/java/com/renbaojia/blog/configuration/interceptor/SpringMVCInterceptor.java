package com.renbaojia.blog.configuration.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @version 1.0
 * @Created with IntelliJ IDEA.
 * @Author renbaojia 1406423298@qq.ocom
 * @Date 2018-11-25 04:27
 * @Description 拦截器配置，拦截所有请求
 */
public class SpringMVCInterceptor implements HandlerInterceptor {
    private Logger logger = LoggerFactory.getLogger(SpringMVCInterceptor.class);

    //在业务处理器处理请求之前被调用。预处理，可以进行编码、安全控制、权限校验等处理；
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        //创建session会话，把sessionId添加进session,用于websocket定位用户发送消息
        HttpSession session = httpServletRequest.getSession();
        String sessionId = session.getId();
        logger.info("加入用户的sessionId为：" + sessionId);
        //把sessionId作为用户的唯一标识
        session.setAttribute("SESSION_USERNAME", sessionId);
        return true;
    }

    //在业务处理器处理请求执行完成后，生成视图之前执行。后处理（调用了Service并返回ModelAndView，但未进行页面渲染）
    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    //在DispatcherServlet完全处理完请求后被调用，可用于清理资源等。返回处理（已经渲染了页面）；
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}