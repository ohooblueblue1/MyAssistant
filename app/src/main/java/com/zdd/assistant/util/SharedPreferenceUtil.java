package com.zdd.assistant.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Project Name: MyAssistant
 * File Name:    SharedPreferenceUtil.java
 * ClassName:    SharedPreferenceUtil
 *
 * Description: TODO.
 *
 * @author ZDD
 * @date 2017年02月23日 17:42
 */
public class SharedPreferenceUtil
{

    private static final String SP_NAME = "sp_name";
    private static SharedPreferenceUtil sMSharedPreferenceUtil;
    private Context mAppContext;
    private SharedPreferences mSharedPreferences;
    private String info;

    private SharedPreferenceUtil(Context context){
        mAppContext = context.getApplicationContext();
    }

    //获取SpHelper的实例
    public static SharedPreferenceUtil getInstance(Context context){
        if(sMSharedPreferenceUtil == null){
            synchronized (SharedPreferenceUtil.class){
                if(sMSharedPreferenceUtil == null){
                    sMSharedPreferenceUtil = new SharedPreferenceUtil(context);
                }
            }
        }
        return sMSharedPreferenceUtil;
    }

    private SharedPreferences getSharePreferences(){
        if(mSharedPreferences == null){
            mSharedPreferences = mAppContext.getSharedPreferences(SP_NAME, Context.MODE_APPEND);
        }
        return mSharedPreferences;
    }

    public void setInfo(String info){
        this.info = info;
        getSharePreferences().edit().putString("info", info).apply();
    }

    public String getInfo(){
        if(info.equals("") || info.length() == 0){
            info = getSharePreferences().getString("info", "");
        }
        return info;
    }
}

