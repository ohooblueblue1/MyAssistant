package com.zdd.myassistant.activity;

import android.os.Bundle;

import com.zdd.myassistant.R;
import com.zdd.myassistant.base.BaseActivity;

/**
 * Project Name: MyAssistant
 * File Name:    SplashActivity.java
 * <p>
 * Description: 启动引导页
 *
 * @author zdd
 * @date 2017年02月11日 13:13
 */
public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
    }
}
