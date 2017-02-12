package com.zdd.myassistant.activity.before;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.zdd.myassistant.R;
import com.zdd.myassistant.activity.main.MainActivity;
import com.zdd.myassistant.base.BaseActivity;

import java.util.function.ToLongBiFunction;

import cn.bmob.v3.BmobUser;

/**
 * Project Name: MyAssistant
 * File Name:    SplashActivity.java
 * <p>
 * Description: 启动欢迎页
 *
 * @author zdd
 * @date 2017年02月11日 13:13
 */
public class SplashActivity extends BaseActivity {

    private final int TO_LOGIN = 1;
    private final int TO_MAIN = 2;

    //欢迎界面显示时长
    private static final long SPLASH_DUR_TIME = 500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        checkIsUserInfoExisit();
    }

    /**
     * 判断是否存在本地登录信息
     * 存在 -->进入主界面
     * 不存在-->进入登陆界面
     */
    private void checkIsUserInfoExisit() {
        if (BmobUser.getCurrentUser() != null) {
            mHandler.sendEmptyMessageDelayed(TO_MAIN, SPLASH_DUR_TIME);
        } else {
            mHandler.sendEmptyMessageDelayed(TO_LOGIN, SPLASH_DUR_TIME);
        }
    }


    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case TO_LOGIN:
                    //启动登录界面
                    LoginActivity.actionStart(SplashActivity.this);
                    finish();
                    break;

                case TO_MAIN:
                    //启动主界面
                    MainActivity.actionStart(SplashActivity.this);
                    finish();
                    break;
                default:
                    break;
            }
        }
    };


}
