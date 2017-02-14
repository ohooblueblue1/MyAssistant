package com.zdd.assistant.util;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Project Name: MyAssistant
 * File Name:    ActivityCollector.java
 * <p>
 * Description: Activity管理类
 *
 * @author zdd
 * @date 2017年02月11日 16:42
 */
public class ActivityCollector {

    //存放所有Activity
    public static List<Activity> sActivities = new ArrayList<Activity>();

    public static void addActivity(Activity activity) {
        sActivities.add(activity);
    }

    public static void removeActivity(Activity activity) {
        sActivities.remove(activity);
    }

    public static void finishAll() {
        for (Activity activity : sActivities) {
            if (!activity.isFinishing()) {
                activity.finish();
            }
        }
    }

    public static void finishAllOther(Activity activity) {
        for (Activity activityToClose : sActivities) {
            if (!activityToClose.isFinishing() && activityToClose != activity) {
                activityToClose.finish();
            }
        }
    }

}

