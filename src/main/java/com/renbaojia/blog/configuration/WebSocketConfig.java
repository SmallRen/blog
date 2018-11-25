package com.renbaojia.blog.configuration;

import com.renbaojia.blog.configuration.socket.SpringWebSocketHandler;
import com.renbaojia.blog.configuration.socket.SpringWebSocketHandlerInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/**
 * @version 1.0
 * @Created with IntelliJ IDEA.
 * @Author renbaojia
 * @Date 2018/11/25 14:39
 * @Description:
 */
@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {


    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        //绑定前端连接端点url
        registry.addHandler(new SpringWebSocketHandler(),"/websocket/socketServer.do").addInterceptors(new SpringWebSocketHandlerInterceptor());
    }
}
