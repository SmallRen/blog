package com.renbaojia.blog.configuration.socket;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by smallRen on 2017/9/2.
 */
public class SpringWebSocketHandler extends TextWebSocketHandler {
    @Autowired
    HttpSession httpSession;
    private static final Map<String, WebSocketSession> users;
    private static Logger logger = LoggerFactory.getLogger(SpringWebSocketHandler.class);

    static {
        users = new HashMap<String, WebSocketSession>();
    }

    public SpringWebSocketHandler() {
        // TODO Auto-generated constructor stub
    }

    /**
     * 连接成功时候，会触发页面上onopen方法
     */
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        // TODO Auto-generated method stub
        String username = (String) session.getAttributes().get("WEBSOCKET_USERNAME");
        users.put(username, session);
        System.out.println("用户" + username + "已链接！");
        System.out.println("connect to the websocket success......当前数量:" + users.size());
        //这块会实现自己业务，比如，当用户登录后，会把离线消息推送给用户
        //session.sendMessage(returnMessage);
        //给所有人发送在线人数
        sendMessageToUsers(new TextMessage(String.valueOf(users.size())));
    }

    /**
     * 关闭连接时触发
     */
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        logger.debug("websocket connection closed......");
        String username = (String) session.getAttributes().get("WEBSOCKET_USERNAME");
        System.out.println("用户" + username + "已退出！");
        users.remove(username);
        System.out.println("剩余在线用户" + users.size());
        sendMessageToUsers(new TextMessage(String.valueOf(users.size())));
    }

    /**
     * js调用websocket.send时候，会调用该方法
     */
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        super.handleTextMessage(session, message);
        httpSession.removeAttribute("userinfo");
        //sendMessageToUser(username, message);
    }
    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        if (session.isOpen()) {
          //  session.close();
        }
        String username = (String) session.getAttributes().get("WEBSOCKET_USERNAME");
        logger.debug("websocket connection closed......");
        users.remove(username);
    }
    @Override
    public boolean supportsPartialMessages() {
        return false;
    }


    /**
     * 给某个用户发送消息
     *
     * @param userName
     * @param message
     */
    public void sendMessageToUser(String userName, TextMessage message) throws IOException {
        WebSocketSession webSocketSession = users.get(userName);
        if (webSocketSession != null) {
            System.out.println(webSocketSession.getUri());
            webSocketSession.sendMessage(message);
        } else {

            Iterator<WebSocketSession> iter = users.values().iterator();
          System.out.println("为空" + users.size());

        }
    }

    /**
     * 给所有在线用户发送消息
     *
     * @param message
     */
    public void sendMessageToUsers(TextMessage message) throws IOException {
        Iterator<WebSocketSession> iter = users.values().iterator();
        while (iter.hasNext()) {
            iter.next().sendMessage(message);

        }
    }

}