package com.zdd.assistant.entity.event;

/**
 * Project Name: MyAssistant
 * File Name:    StartUpdateDiaryEvent.java
 * ClassName:    StartUpdateDiaryEvent
 *
 * Description: TODO.
 *
 * @author ZDD
 * @date 2017年02月23日 17:33
 *
 * Copyright (c) 2017年, 4399 Network CO.ltd. All Rights Reserved.
 */
public class StartUpdateDiaryEvent
{

    private int position;

    public StartUpdateDiaryEvent(int position)
    {
        this.position = position;
    }

    public void setPosition(int position)
    {
        this.position = position;
    }

    public int getPosition()
    {
        return position;
    }
}