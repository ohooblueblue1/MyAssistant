package com.zdd.assistant.base;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.zdd.assistant.util.ActivityCollector;
import com.zdd.assistant.util.NetworkUtil;
import com.zdd.assistant.util.ToastUtil;

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

    private ProgressDialog mProgressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        //添加到Activity管理类中
        ActivityCollector.addActivity(this);
    }

    @Override
    protected void onDestroy() {
        //从Activity管理类中移除当前Activity
        ActivityCollector.removeActivity(this);
        super.onDestroy();
    }


    public boolean checkIsNetAvailable() {
        if (NetworkUtil.isAvailable()) {
            return true;
        }
        ToastUtil.showToast(this, "网络开小差了，请检查~");
        return false;
    }

    public void showProgressDialog(String msg) {
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage(msg);
        mProgressDialog.setCancelable(false);
        mProgressDialog.show();
    }

    public void dismissProgressDialog() {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
    }

}
