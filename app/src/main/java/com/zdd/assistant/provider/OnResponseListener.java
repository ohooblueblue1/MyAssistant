package com.zdd.assistant.provider;

/**
 * Project Name: MyAssistant
 * File Name:    OnResponseListener.java
 * ClassName:    OnResponseListener
 *
 * Description: 数据加载监听
 *
 * @author ZDD
 * @date 2017年02月18日 16:01
 *
 * Copyright (c) 2017年, 4399 Network CO.ltd. All Rights Reserved.
 */
public interface OnResponseListener
{
    /*
     * 加载前的回调，做预处理
     */
    void onBefore();

    /*
     * 加载成功的回调
     */
    void onSuccess(Object response);

    /*
     * 加载失败的回调
     */
    void onFailure();

}
