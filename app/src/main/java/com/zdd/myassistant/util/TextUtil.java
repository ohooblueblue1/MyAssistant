package com.zdd.myassistant.util;

import android.text.TextUtils;
import android.widget.EditText;

/**
 * Project Name: MyAssistant
 * File Name:    TextUtil.java
 * <p>
 * Description: 文字工具类
 *
 * @author zdd
 * @date 2017年02月11日 23:36
 */
public class TextUtil {

    /**
     * EditText判空
     *
     * @param editText
     * @return 文字是否为空
     */
    public static boolean isEmpty(EditText editText) {
        return TextUtils.equals("", editText.getText().toString());
    }


}
