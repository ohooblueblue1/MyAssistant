package com.zdd.myassistant.entity.event;

/**
 * Project Name: MyAssistant
 * File Name:    RegisterSuccessEvent.java
 * <p>
 * Description: 事件--注册成功
 *
 * @author zdd
 * @date 2017年02月12日 17:30
 */
public class RegisterSuccessEvent {

    //注册成功的账号
    private String mAccount;

    public RegisterSuccessEvent(String account){
        mAccount = account;
    }

    public String getAccount() {
        return mAccount;
    }

}
