package com.zdd.assistant.activity.before;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.xwray.passwordview.PasswordView;
import com.zdd.assistant.R;
import com.zdd.assistant.activity.main.MainActivity;
import com.zdd.assistant.base.BaseActivity;
import com.zdd.assistant.entity.MyUser;
import com.zdd.assistant.entity.event.RegisterSuccessEvent;
import com.zdd.assistant.util.TextUtil;
import com.zdd.assistant.util.ToastUtil;

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
        dismissProgressDialog();
        ToastUtil.showToast(this, "登录成功~");
        //获取登录成功后的本地用户信息
        //通过MyUser user = BmobUser.getCurrentUser(MyUser.class)获取自定义用户信息
        //启动主界面
        MainActivity.actionStart(this);
    }


    /**
     * 登录失败的回调
     */
    private void onLoginFailure() {
        dismissProgressDialog();
        ToastUtil.showToast(this, "登录失败~");
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

    public static void actionStart(Context context){
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
    }

}
