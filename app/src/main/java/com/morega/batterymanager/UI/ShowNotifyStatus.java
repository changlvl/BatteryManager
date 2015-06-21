package com.morega.batterymanager.UI;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.BatteryManager;
import android.widget.RemoteViews;

import com.morega.batterymanager.R;
import com.morega.batterymanager.Utils.ComputeForVolume;

/**
 * Created by Morega03 on 2015/6/1.
 */
public class ShowNotifyStatus {
    //显示状态栏
    public static void showNotifyStatus(Context context,Intent batteryIntent){
        int status = batteryIntent.getIntExtra("status",0);
        int temperature = batteryIntent.getIntExtra("temperature",0)/10;
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        Notification.Builder mBuilder = new Notification.Builder(context);
        RemoteViews mRemoteViews = new RemoteViews(context.getPackageName(), R.layout.status_notify);
        if (status== BatteryManager.BATTERY_STATUS_CHARGING){
            mRemoteViews.setTextViewText(R.id.notify_status, "充电进行中");
            mRemoteViews.setTextViewText(R.id.notify_advice, "请暂时停用手机，提升充电效率和降低温度");
        }else if (status == BatteryManager.BATTERY_STATUS_FULL){
            mRemoteViews.setTextViewText(R.id.notify_status, "电池已充满");
            mRemoteViews.setTextViewText(R.id.notify_advice, "请拔掉充电器节约能源和保护电池");
        }else {
            if (ComputeForVolume.getLevel(batteryIntent.getIntExtra("level", 0), batteryIntent.getIntExtra("scale", 100))<=20){
                mRemoteViews.setImageViewResource(R.id.notify_level, Color.RED);
                mRemoteViews.setTextViewText(R.id.notify_status, "电池电量不足");
                mRemoteViews.setTextViewText(R.id.notify_advice, "这样下去很快要自动关机了噢");
            }else if (ComputeForVolume.getLevel(batteryIntent.getIntExtra("level", 0), batteryIntent.getIntExtra("scale", 100))>=60){
                mRemoteViews.setImageViewResource(R.id.notify_level, Color.GREEN);
                mRemoteViews.setTextViewText(R.id.notify_status, "电池正常使用");
                mRemoteViews.setTextViewText(R.id.notify_advice, "充电充得饱饱的，情况一切正常");
            }else {
                mRemoteViews.setImageViewResource(R.id.notify_level, Color.YELLOW);
                mRemoteViews.setTextViewText(R.id.notify_status, "电池正常使用");
                mRemoteViews.setTextViewText(R.id.notify_advice, "人在江湖跑，电量总不饱");
            }
        }

        if (temperature>40){
            mRemoteViews.setTextViewText(R.id.notify_status, "警告！！温度过高！！");
            mRemoteViews.setTextViewText(R.id.notify_advice, "这样用手机很容易爆炸噢！！");
            //TODO  音效
        }

        mRemoteViews.setImageViewResource(R.id.notify_temperature, R.drawable.abc_btn_radio_to_on_mtrl_015);


        Intent intent = new Intent(context,MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context,0,intent,0);
        mBuilder.setContent(mRemoteViews)
                .setContentIntent(pendingIntent)
                .setPriority(Notification.PRIORITY_MAX)
                .setOngoing(true)
                .setSmallIcon(R.drawable.ic_launcher);
        if (status == BatteryManager.BATTERY_STATUS_FULL){
            //添加一个提示充满电的声音
        }
        if (temperature>40){
//            mBuilder.setSound()  添加一个警报
        }
        notificationManager.notify(0, mBuilder.build());
    }
}
