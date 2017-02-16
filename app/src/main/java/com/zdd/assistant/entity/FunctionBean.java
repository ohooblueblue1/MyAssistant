package com.zdd.assistant.entity;

/**
 * Project Name: MyAssistant
 * File Name:    FunctionBean.java
 * ClassName:    FunctionBean
 *
 * Description: 主页功能item实体类
 *
 * @author ZDD
 * @date 2017年02月16日 14:11
 *
 */
public class FunctionBean
{
    //功能图标ID
    private int mFunctionImgId;
    //功能名称
    private String mFunctionName;

    public FunctionBean(int functionImgId, String functionName)
    {
        mFunctionImgId = functionImgId;
        mFunctionName = functionName;
    }

    public int getFunctionImgId()
    {
        return mFunctionImgId;
    }

    public String getFunctionName()
    {
        return mFunctionName;
    }
}
