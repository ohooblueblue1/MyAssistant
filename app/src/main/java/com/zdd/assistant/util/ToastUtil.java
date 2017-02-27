package com.zdd.assistant.util;

import android.content.Context;
import android.widget.Toast;

/**
 * Project Name: MyAssistant
 * File Name:    ToastUtil.java
 * <p>
 * Description: Toast工具类，全局使用同一个Toast实例
 *
 * @author zdd
 * @date 2017年02月11日 17:52
 */
public class ToastUtil {
    private static Toast mToast;

    public static void showToast(Context context, String msg) {
        if (mToast == null) {
            mToast = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
        } else {
            mToast.setText(msg);
        }
        mToast.show();
    }

    public static void cancelToast(){
        if(mToast == null){
            return;
        }
        mToast.cancel();
    }

}
