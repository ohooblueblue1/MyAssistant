package com.zdd.assistant.util;

import java.util.HashMap;
import java.util.Iterator;

/**
 * Project Name: MyAssistant
 * File Name:    UrlUtil.java
 * ClassName:    UrlUtil
 *
 * Description: URL拼装工具类
 *
 * @author ZDD
 * @date 2017年02月18日 15:14
 */
public class UrlUtil {

    public static String getURL(String baseURL, HashMap<String, String> params) {

        // 添加url参数
        if (params != null) {
            Iterator<String> it = params.keySet().iterator();
            StringBuffer sb = null;
            while (it.hasNext()) {
                String key = it.next();
                String value = params.get(key);
                if (sb == null) {
                    sb = new StringBuffer();
                    sb.append("?");
                }
                else {
                    sb.append("&");
                }
                sb.append(key);
                sb.append("=");
                sb.append(value);
            }
            baseURL += sb.toString();
        }
        //返回拼接好参数的完整URL
        return baseURL;
    }

}
