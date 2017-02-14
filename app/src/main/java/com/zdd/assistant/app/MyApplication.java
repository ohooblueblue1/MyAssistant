package com.zdd.assistant.app;

import android.app.Application;
import android.content.Context;

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
public class MyApplication extends Application {

    public static final String APPLICATION_ID = "d4564776a5246d78d181e41bd447ee1d";
    private static Context sContext;

    @Override
    public void onCreate() {
        super.onCreate();

        sContext = getApplicationContext();
        configBmob();
    }

    /**
     * 配置bmob后端云
     */
    private void configBmob() {
        //提供以下两种方式进行初始化操作：
        //第一：默认初始化
        Bmob.initialize(this, APPLICATION_ID);

        //第二:设置BmobConfig,允许设置请求超时时间、文件分片上传时每片的大小、文件的过期时间(单位为秒)，
        //BmobConfig config =new BmobConfig.Builder(this)
        ////设置appkey
        //.setApplicationId("Your Application ID")
        ////请求超时时间（单位为秒）：默认15s
        //.setConnectTimeout(30)
        ////文件分片上传时每片的大小（单位字节），默认512*1024
        //.setUploadBlockSize(1024*1024)
        ////文件的过期时间(单位为秒)：默认1800s
        //.setFileExpiration(2500)
        //.build();
        //Bmob.initialize(config);
    }

    /**
     * @return 应用程序上下文对象
     */
    public static Context getAppContext() {
        return sContext;
    }

}
