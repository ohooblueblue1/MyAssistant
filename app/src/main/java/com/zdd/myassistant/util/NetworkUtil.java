package com.zdd.myassistant.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.zdd.myassistant.app.MyApplication;

/**
 * Project Name: MyAssistant
 * File Name:    NetworkUtil.java
 * <p>
 * Description: 网络连接工具类
 *
 * @author zdd
 * @date 2017年02月12日 14:11
 */
public class NetworkUtil {


    /**
     * @return 网络是否可用
     */
    public static boolean isAvailable() {
        Context context = MyApplication.getAppContext();
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
            if (mNetworkInfo != null) {
                return mNetworkInfo.isAvailable();
            }
        }
        return false;
    }


}
