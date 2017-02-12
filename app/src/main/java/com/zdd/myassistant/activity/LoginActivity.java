package com.zdd.myassistant.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.xwray.passwordview.PasswordView;
import com.zdd.myassistant.R;
import com.zdd.myassistant.base.BaseActivity;
import com.zdd.myassistant.entity.MyUser;
import com.zdd.myassistant.entity.event.RegisterSuccessEvent;
import com.zdd.myassistant.util.TextUtil;
import com.zdd.myassistant.util.ToastUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

/**
 * Project Name: MyAssistant
 * File Name:    LoginActivity.java
 * ClassName:    LoginActivity
 * <p>
 * Description: 登录界面
 *
 * @author zdd
 * @date 2017年02月06日 21:13
 */
public class LoginActivity extends BaseActivity implements View.OnClickListener {

    private TextView mBtnToRegister;
    private EditText mEdtAccount;
    private PasswordView mPwVPassword;

    private Button mBtnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //注册EventBus
        EventBus.getDefault().register(this);
        initView();
    }

    @Override
    protected void onDestroy() {
        //反注册EventBus
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    private void initView() {
        mBtnLogin = (Button) findViewById(R.id.btn_login);
        mBtnLogin.setOnClickListener(this);
        mBtnToRegister = (TextView) findViewById(R.id.tv_to_register);
        mBtnToRegister.setOnClickListener(this);

        mEdtAccount = (EditText) findViewById(R.id.edt_user_name);
        mPwVPassword = (PasswordView) findViewById(R.id.edt_password);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_to_register:
                //跳转到注册页面
                RegisterActivity.actionStart(this);
                break;

            case R.id.btn_login:
                tryToLogin();
                break;
        }
    }

    /**
     * 尝试登录
     */
    private void tryToLogin() {
        //登录信息不合法直接return
        if (!isValid()) {
            return;
        }
        //网络不可用直接return
        if (!checkIsNetAvailable()) {
            return;
        }
        showProgressDialog("登录中，请稍后~");
        //进行登录
        MyUser myUser = new MyUser();
        myUser.setUsername(mEdtAccount.getText().toString());
        myUser.setPassword(mPwVPassword.getText().toString());
        myUser.login(new SaveListener<MyUser>() {
            @Override
            public void done(MyUser myUser, BmobException e) {
                if (e == null) {
                    onLoginSuccess();
                } else {
                    onLoginFailure();
                }
            }
        });
    }


    /**
     * 登录信息合法性检查
     *
     * @return 是否合法
     */
    private boolean isValid() {
        if (TextUtil.isEmpty(mEdtAccount)) {
            ToastUtil.showToast(this, "您还未输入用户名哦~");
            return false;
        }
        if (TextUtil.isEmpty(mPwVPassword)) {
            ToastUtil.showToast(this, "您还未输入密码~");
            return false;
        }
        return true;
    }


    /**
     * 登录成功的回调
     */
    private void onLoginSuccess() {

    }


    /**
     * 登录失败的回调
     */
    private void onLoginFailure() {

    }

    /**
     * 注册成功返回登录页的回调
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onRegisterSuccessEvent(RegisterSuccessEvent event) {
        //注册成功后更新登录界面
        mPwVPassword.setText("");
        mEdtAccount.setText(event.getAccount());
    }

}
