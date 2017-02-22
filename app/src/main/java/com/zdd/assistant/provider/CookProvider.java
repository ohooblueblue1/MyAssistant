package com.zdd.assistant.provider;

/**
 * Project Name: MyAssistant
 * File Name:    CookProvider.java
 * ClassName:    CookProvider
 *
 * Description: 菜谱信息提供者
 *
 * @author ZDD
 * @date 2017年02月21日 20:12
 */
public class CookProvider
{
    //菜谱分类
    public static final String URL_COOK_CATEGORY = "http://www.tngou.net/api/cook/classify";

    //根据分类id获取该分类下菜谱列表
    public static final String URL_COOK_LIST = "http://www.tngou.net/api/cook/list?id=";

    public void searchCook(String id, final OnResponseListener listener)
    {
        listener.onBefore();
        
    }


}
