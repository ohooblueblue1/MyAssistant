package com.zdd.assistant.entity.notepad;

import cn.bmob.v3.BmobObject;

/**
 * Project Name: MyAssistant
 * File Name:    Diary.java
 * ClassName:    Diary
 *
 * Description: 日记实体类
 *
 * @author ZDD
 * @date 2017年02月23日 17:17
 */
public class Diary extends BmobObject
{
    private String date;
    private String title;
    private String content;


    public Diary(String date, String title, String content)
    {
        this.date = date;
        this.title = title;
        this.content = content;
    }


    public String getDate()
    {
        return date;
    }

    public void setDate(String date)
    {
        this.date = date;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getContent()
    {
        return content;
    }

    public void setContent(String content)
    {
        this.content = content;
    }

}
