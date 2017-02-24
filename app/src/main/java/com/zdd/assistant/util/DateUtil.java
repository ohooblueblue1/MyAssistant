package com.zdd.assistant.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Project Name: MyAssistant
 * File Name:    DateUtil.java
 * ClassName:    DateUtil
 *
 * Description: 时间日期工具类
 *
 * @author ZDD
 * @date 2017年02月23日 17:41
 */
public class DateUtil
{

    public static StringBuilder getDate()
    {

        StringBuilder stringBuilder = new StringBuilder();
        Calendar now = Calendar.getInstance();
        stringBuilder.append(now.get(Calendar.YEAR) + "年");
        stringBuilder.append((int) (now.get(Calendar.MONTH) + 1) + "月");
        stringBuilder.append(now.get(Calendar.DAY_OF_MONTH) + "日");
        return stringBuilder;
    }


    /**
     * 传入日期字符串： 2017-02-19T14:20:00+08:00
     * 解析为：  2017-02-19
     * @param updateTime
     * @return
     */
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
