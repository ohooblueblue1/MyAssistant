package com.zdd.assistant.entity.robot;

/**
 * Project Name: MyAssistant
 * File Name:    Result.java
 * <p>
 * Description: 返回的消息实体
 *
 * @author zdd
 * @date 2017年02月21日 0:06
 */
public class Result {
    private int code;
    private String text;

    public int getCode()
    {
        return code;
    }

    public void setCode(int code)
    {
        this.code = code;
    }

    public String getText()
    {
        return text;
    }

    public void setText(String text)
    {
        this.text = text;
    }
}
