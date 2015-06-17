package com.morega.batterymanager.Utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.morega.batterymanager.UI.MainActivity;

/**
 * Created by Morega03 on 2015/5/24.
 */
public class BatteryBroadCastReceiver extends BroadcastReceiver{

    private MainActivity mainActivity;

    public BatteryBroadCastReceiver(MainActivity activity){
        this.mainActivity = activity;
    }

    public BatteryBroadCastReceiver(){

    }

    @Override
    public void onReceive(Context context, Intent intent){
    }
}
