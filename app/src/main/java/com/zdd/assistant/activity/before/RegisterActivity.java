package com.zdd.assistant.activity.before;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.xwray.passwordview.PasswordView;
import com.zdd.assistant.R;
import com.zdd.assistant.base.BaseActivity;
import com.zdd.assistant.entity.MyUser;
import com.zdd.assistant.entity.event.RegisterSuccessEvent;
import com.zdd.assistant.util.TextUtil;
import com.zdd.assistant.util.ToastUtil;

import org.greenrobot.eventbus.EventBus;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

/**
 * Project Name: MyAssistant
 * File Name:    RegisterActivity.java
 * ClassName:    RegisterActivity
 * <p>
 * Description: 注册界面
 *
 * @author zdd
 * @date 2017年02月05日 21:13
 */
public class RegisterActivity extends BaseActivity implements View.OnClickListener {

    private EditText mEdtUserName;
    private PasswordView mPwvPassword;
    private PasswordView mPwvPasswordConfirm;

    private Button mBtnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initView();
    }

    private void initView() {
        //初始化Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mEdtUserName = (EditText) findViewById(R.id.edt_user_name);
        mPwvPassword = (PasswordView) findViewById(R.id.edt_password);
        mPwvPasswordConfirm = (PasswordView) findViewById(R.id.edt_confirm_password);

        mBtnRegister = (Button) findViewById(R.id.btn_register);
        mBtnRegister.setOnClickListener(this);
    }

    //Toolbar菜单点击事件监听
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == android.R.id.home) {
            //按下返回导航按钮，结束当前Activity
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, RegisterActivity.class);
        context.startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btn_register:
                tryToRegister();
                break;
        }
    }

    /**
     * 尝试注册
     */
    private void tryToRegister() {
        //注册信息不合法直接return
        if (!isValid()) {
            return;
        }
        //网络不可用直接return
        if (!checkIsNetAvailable()) {
            return;
        }
        showProgressDialog("注册中，请稍后~");
        //进行注册
        MyUser myUser = new MyUser();
        myUser.setUsername(mEdtUserName.getText()
                                       .toString());
        myUser.setPassword(mPwvPassword.getText()
                                       .toString());
        myUser.signUp(new SaveListener<MyUser>() {
            @Override
            public void done(MyUser myUser, BmobException e) {
                if (e == null) {
                    onRegisterSuccess();
                } else {
                    onRegisterFailure();
                }
            }
        });
    }

    /**
     * 注册信息合法性检查
     *
     * @return 是否合法
     */
    private boolean isValid() {
        if (TextUtil.isEmpty(mEdtUserName)) {
            ToastUtil.showToast(this, "您还未输入用户名哦~");
            return false;
        }
        if (TextUtil.isEmpty(mPwvPassword)) {
            ToastUtil.showToast(this, "您还未输入密码~");
            return false;
        }
        if (TextUtil.isEmpty(mPwvPasswordConfirm)) {
            ToastUtil.showToast(this, "请再次输入密码以确认~");
            return false;
        }
        if (!mPwvPassword.getText()
                         .toString()
                         .equals(mPwvPasswordConfirm.getText()
                                                    .toString())) {
            ToastUtil.showToast(this, "两次密码输入不一致，请检查后重新输入~");
            return false;
        }
        return true;
    }

    /**
     * 注册成功的回调
     */
    private void onRegisterSuccess() {
        dismissProgressDialog();
        ToastUtil.showToast(RegisterActivity.this, "注册成功，请登录~");
        //通知登录界面
        EventBus.getDefault()
                .post(new RegisterSuccessEvent(mEdtUserName.getText()
                                                           .toString()));
        finish();
    }

    private void onRegisterFailure() {
        dismissProgressDialog();
        ToastUtil.showToast(RegisterActivity.this, "注册失败");
    }

}
