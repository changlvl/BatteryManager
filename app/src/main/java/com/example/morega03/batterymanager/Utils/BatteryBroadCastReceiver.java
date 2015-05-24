package com.example.morega03.batterymanager.Utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.example.morega03.batterymanager.UI.MainActivity;

/**
 * Created by Morega03 on 2015/5/24.
 */
public class BatteryBroadCastReceiver extends BroadcastReceiver{

    MainActivity mainActivity;

    public BatteryBroadCastReceiver(MainActivity activity){
        this.mainActivity = activity;
    }

    @Override
    public void onReceive(Context context, Intent intent){
        String BatteryAction = intent.getAction();
        if (BatteryAction.equals(Intent.ACTION_BATTERY_CHANGED)){

        }
    }
}
