package com.zdd.myassistant.base;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;

import com.zdd.myassistant.util.ActivityCollector;

/**
 * Project Name: MyAssistant
 * File Name:    BaseActivity.java
 * <p>
 * Description: Activity基类
 *
 * @author zdd
 * @date 2017年02月05日 21:11
 */
public class BaseActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        //添加到Activity管理类中
        ActivityCollector.addActivity(this);
    }

    @Override
    protected void onDestroy() {
        //从Activity管理类中移除当前Activity
        ActivityCollector.removeActivity(this);
        super.onDestroy();
    }


    //// TODO: 2017/2/12 封装网络状态检查
    public void checkNetState() {

    }


}
