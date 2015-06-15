package com.morega.batterymanager.Utils;

import android.app.ActivityManager;
import android.content.Context;

import java.util.List;

/**
 * Created by Morega03 on 2015/6/1.
 */
public class ActivityUtils {


    public static boolean isRunningApp(Context context,String packageName){
        boolean isRunning = false;
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> list = am.getRunningTasks(100);
        for (ActivityManager.RunningTaskInfo info : list) {
            if (info.topActivity.getPackageName().equals(packageName) && info.baseActivity.getPackageName().equals(packageName)) {
                isRunning = true;
                // find it, break
                break;
            }
        }
        return isRunning;
    }
}
