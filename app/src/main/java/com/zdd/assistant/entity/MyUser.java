package com.zdd.assistant.entity;

import com.zdd.assistant.entity.notepad.Diary;

import java.util.List;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobPointer;

/**
 * Project Name: MyAssistant
 * File Name:    MyUser.java
 * <p>
 * Description: 用户实体类
 *
 * @author zdd
 * @date 2017年02月11日 23:51
 */
public class MyUser extends BmobUser {


    private BmobPointer user;

    private List<Diary> diaryList;

    public List getDiaryList()
    {
        return diaryList;
    }

    public void setDiaryList(List diaryList)
    {
        this.diaryList = diaryList;
    }

    public BmobPointer getUser()
    {
        return user;
    }

    public void setUser(BmobPointer user)
    {
        this.user = user;
    }

}
