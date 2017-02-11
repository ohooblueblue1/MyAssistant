package com.zdd.myassistant.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.zdd.myassistant.R;
import com.zdd.myassistant.base.BaseActivity;

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
    //private EditText mEdtPassword;
    private Button mBtnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();
    }

    private void initView() {
        mBtnToRegister = (TextView) findViewById(R.id.tv_to_register);
        mBtnToRegister.setOnClickListener(this);

        mEdtAccount = (EditText) findViewById(R.id.edt_user_name);
        //mEdtPassword = (EditText) findViewById(R.id.edt_password);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_to_register:
                //跳转到注册页面
                RegisterActivity.actionStart(this);
                break;
        }
    }
}
