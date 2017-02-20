package com.zdd.assistant.entity.robot;

/**
 * Project Name: MyAssistant
 * File Name:    Message.java
 * ClassName:    Message
 *
 * Description: 聊天信息实体类
 *
 * @author ZDD
 * @date 2017年02月20日 20:11
 *
 * Copyright (c) 2017年, 4399 Network CO.ltd. All Rights Reserved.
 */
public class Message
{
    /**
     * 标识发出的消息
     */
    public static final int TYPE_SEND = 1;
    /**
     * 标识收到的消息
     */
    public static final int TYPE_RECEIEVE = 2;

    /**
     * 信息类型
     */
    private int mType;

    /**
     * 消息内容
     */
    private String mContent;

    public int getType() {
        return mType;
    }

    public void setType(int type) {
        mType = type;
    }

    public String getContent() {
        return mContent;
    }

    public void setContent(String content) {
        mContent = content;
    }
}
