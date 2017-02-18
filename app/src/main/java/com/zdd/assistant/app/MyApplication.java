package com.zdd.assistant.app;

import android.app.Application;
import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import cn.bmob.v3.Bmob;

/**
 * Project Name: MyAssistant
 * File Name:    MyApplication.java
 * <p>
 * Description: 自定义Application
 *
 * @author zdd
 * @date 2017年02月12日 0:03
 */
public class MyApplication extends Application
{

    public static final String BMOB_APPLICATION_ID = "d4564776a5246d78d181e41bd447ee1d";

    private static Context sContext;
    private static RequestQueue mRequestQueue;


    @Override
    public void onCreate()
    {
        super.onCreate();

        sContext = getApplicationContext();
        //配置bmob后端云
        Bmob.initialize(this, BMOB_APPLICATION_ID);
        //配置Volley
        mRequestQueue = Volley.newRequestQueue(this);
    }


    /**
     * @return 应用程序上下文对象
     */
    public static Context getAppContext()
    {
        return sContext;
    }

    /**
     * @return Volley请求队列
     */
    public static RequestQueue getRequestQueue()
    {
        return mRequestQueue;
    }


    // TODO: 项目模块
    // 天气 -- 心知天气   http://www.thinkpage.cn/doc
    // 记事本 -- bmob后端云   http://docs.bmob.cn/data/Android/a_faststart/doc/index.html#初始化BmobSDK
    // 菜谱 -- 天狗健康菜谱   http://www.tngou.net/doc/cook/67
    // 聊天机器人 -- 图灵机器人  http://www.tuling123.com/member/apistrore/index.jhtml
    // 词典 or 指南针

}
