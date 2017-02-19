package com.zdd.assistant.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Project Name: MyAssistant
 * File Name:    DateUtil.java
 * <p>
 * Description: 时间日期工具类
 *
 * @author zdd
 * @date 2017年02月19日 19:07
 */
public class DateUtil {

    //传入日期字符串： 2017-02-19T14:20:00+08:00
    //解析为：  2017-02-19

    public static String getWeatherTimeString(String updateTime) {

        String s = updateTime.substring(0, 18);
        //2017-02-19T14:20:00
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        try {
            Date date = sdf.parse(s);
            s = android.text.format.DateFormat.format("HH:mm", date)
                                              .toString();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return s + "发布";
    }

}
