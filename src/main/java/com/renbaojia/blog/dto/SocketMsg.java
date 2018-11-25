package com.renbaojia.blog.dto;

/**
 * @version 1.0
 * @Created with IntelliJ IDEA.
 * @Author renbaojia
 * @Date 2018/11/25 16:13
 * @Description:
 */
public class SocketMsg {
    private String type;
    private String text;

    public SocketMsg() {
    }

    public SocketMsg(String type, String text) {
        this.type = type;
        this.text = text;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
