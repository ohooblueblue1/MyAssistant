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

    public static List<Activity> activities = new ArrayList<Activity>();

    public static void addActivity(Activity activity) {
        if(!activities.contains(activity)){
            activities.add(activity);
        }
    }

    public static void removeActivity(Activity activity) {
        activities.remove(activity);
    }

    public static void finishAll() {
        for (Activity activity : activities) {
            if (!activity.isFinishing()) {
                activity.finish();
            }
        }
    }

    public static void finishAllOther(Activity activity) {
        for (Activity activityToClose : activities) {
            if (!activityToClose.isFinishing()) {
                if(activityToClose != activity){
                    activityToClose.finish();
                }
            }
        }
    }

}

