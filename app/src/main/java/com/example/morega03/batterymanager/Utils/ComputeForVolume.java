package com.example.morega03.batterymanager.Utils;

import android.content.Intent;
import android.os.BatteryManager;

import java.util.Calendar;

/**
 * Created by Morega03 on 2015/5/23.
 * 计算电池的容量，在充电的过程中
 */
public class ComputeForVolume {
    public static int FirstTime(){
        int time1;
        Calendar calendar1 = Calendar.getInstance();
        int hour1 = calendar1.get(Calendar.HOUR_OF_DAY);
        int minute1 = calendar1.get(Calendar.MINUTE);
        int seconds1 = calendar1.get(Calendar.SECOND);
        time1 = hour1*60*60 + minute1*60 + seconds1;
        return time1;
    }
    public static double ComputeVolume(int time1,Intent intent){
        double volume;
        int amp = 500;
        Calendar calendar2 = Calendar.getInstance();
        int hour2 = calendar2.get(Calendar.HOUR_OF_DAY);
        int minute2 = calendar2.get(Calendar.MINUTE);
        int seconds2 = calendar2.get(Calendar.SECOND);
        int time2 = hour2*60*60 + minute2*60 + seconds2;
        int plugType = intent.getIntExtra("plugged", 0);
        if (plugType == BatteryManager.BATTERY_PLUGGED_AC){
            amp = 1000;
        }else if (plugType == BatteryManager.BATTERY_PLUGGED_USB){
            amp = 500;
        }
        volume = (time2-time1)*amp*100/60/60;
        return volume;
    }
    public static int getLevel(int level , int scale){
        return (level*100)/scale;
    }
}
