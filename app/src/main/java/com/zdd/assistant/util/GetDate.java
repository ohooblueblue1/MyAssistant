package com.zdd.assistant.util;

import java.util.Calendar;

/**
 * Project Name: MyAssistant
 * File Name:    GetDate.java
 * ClassName:    GetDate
 *
 * Description: TODO.
 *
 * @author ZDD
 * @date 2017年02月23日 17:41
 */
public class GetDate
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
}
