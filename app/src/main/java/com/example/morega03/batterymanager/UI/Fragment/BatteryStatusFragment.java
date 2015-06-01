package com.example.morega03.batterymanager.UI.Fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.net.wifi.WifiManager;
import android.os.BatteryManager;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.morega03.batterymanager.R;
import com.example.morega03.batterymanager.UI.ShowNotifyStatus;
import com.example.morega03.batterymanager.UI.View.LevelProgressView;
import com.example.morega03.batterymanager.Utils.ComputeForVolume;

import java.util.Calendar;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Morega03 on 2015/5/24.
 */
public class BatteryStatusFragment extends BaseFragment{
    /**
     * ������״̬��Fragment
     */
    //电池技术
    private String technology = null;
    //电池温度
    private int temperature = 0;
    //电池充放电状态
    private int status = 0;
    //电量
    private int level = 0;
    //电池健康状态
    private int health = 0;
    //电量上限
    private int scale = 100;
    //充电方式
    private int plugged = 0;
    //电压
    private int voltage = 0;
    //一个用于记录是否待机的标识符
    private int volumeflag_out = 0;
    //一个用于记录状态变化的标识符
    private int volumeflag = 0;
    //假设的初始充电容量
    private double volume = 2000;
    //假设的初始放电容量
    private final double volume_out = 2000;
    //剩余可用的时间
    private double remainTime = 0;
    //用于记录时间的标志
    private int time1 = 0;
    //用于记录电量显示的变化
    private int level1 = 0;
    BroadcastReceiver batteryChangedReceiver;
    @InjectView(R.id.level) TextView levelView;
    @InjectView(R.id.battery_status_level) ImageView batteryStatusLevel;
    @InjectView(R.id.battery_status_temperature) TextView batteryStatusTemperature;
    @InjectView(R.id.charging_or_not) TextView chargingOrNot;
    @InjectView(R.id.status_from_remain_level) TextView statusFromRemainLevel;
    @InjectView(R.id.reamin_usable_time) TextView remainUsableTime;
    @InjectView(R.id.phone_type) TextView phoneType;
    @InjectView(R.id.connect_status) TextView connectStatus;
    @InjectView(R.id.battey_status) TextView batteryStatus;
    @InjectView(R.id.temperature_status) TextView temperatureStatus;
    @InjectView(R.id.battery_voltage) TextView batteryVoltage;
    @InjectView(R.id.battery_technology) TextView batteryTechnology;

    //���췽�����������õ���
    public BatteryStatusFragment(){

    }
    //�����һ�û������
    private static class FragmentHolder{
        private static final BatteryStatusFragment INSTANCE = new BatteryStatusFragment();

    }

    public static final BatteryStatusFragment getInstance(){
        return FragmentHolder.INSTANCE;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater , ViewGroup container,
                             Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_battery_status,container,false);

        ButterKnife.inject(this, view);
        IntentFilter intentFilter = new IntentFilter();
        //字面意思应该是发生变化，其实好像是只要找得到电池，就符合这个状态
        intentFilter.addAction(Intent.ACTION_BATTERY_CHANGED);
        //低电量
        intentFilter.addAction(Intent.ACTION_BATTERY_LOW);
        //电量充足
        intentFilter.addAction(Intent.ACTION_BATTERY_OKAY);

        batteryChangedReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String BatteryAction = intent.getAction();
                if (BatteryAction.equals(Intent.ACTION_BATTERY_CHANGED)) {
                    status = intent.getIntExtra("status",0);
                    health = intent.getIntExtra("health",0);
                    level = intent.getIntExtra("level",0);
                    scale = intent.getIntExtra("scale",100);
                    plugged = intent.getIntExtra("plugged",0);
                    temperature = intent.getIntExtra("temperature",0);
                    voltage = intent.getIntExtra("voltage",0);
                    technology = intent.getStringExtra("technology");

                }
                setHealth();
                setStatus();
                setTemperature();
                batteryVoltage.setText(String.valueOf((double) voltage / 1000) + "V");
                batteryTechnology.setText(technology);
                String type = Build.MODEL +" (Android " + Build.VERSION.RELEASE + ")";
                phoneType.setText(type);
                setRemainTime(intent);
                System.out.print("volumeflag_out:" + volumeflag_out);
                setLevel(intent);
                ShowNotifyStatus.showNotifyStatus(getActivity(),intent);

            }
        };
        getActivity().registerReceiver(batteryChangedReceiver, intentFilter);




        return view;
    }

    private void setLevel(Intent intent){
        LevelProgressView progressView = new LevelProgressView(getActivity());
        progressView.setCurrentCount(ComputeForVolume.getLevel(intent.getIntExtra("level", 0), intent.getIntExtra("scale", 100)));
        progressView.setMaxCount(100);
        Bitmap bitmap = Bitmap.createBitmap(250, 100, Bitmap.Config.ARGB_4444);
        progressView.draw(new Canvas(bitmap));
        batteryStatusLevel.setImageBitmap(bitmap);
        levelView.setText("当前电量：" + ComputeForVolume.getLevel(intent.getIntExtra("level", 0), intent.getIntExtra("scale", 100)) + "%");
    }
    private void setTemperature(){
        temperatureStatus.setText(String.valueOf((double) temperature / 10) + "\u2103");
        batteryStatusTemperature.setText(String.valueOf((double) temperature / 10) + "\u2103");
        if (temperature/10 < 30){
            batteryStatusTemperature.setBackgroundColor(Color.GREEN);
        }else if (temperature/10 <40){
            batteryStatusTemperature.setBackgroundColor(Color.YELLOW);
        }else if (temperature/10 >= 40){
            batteryStatusTemperature.setBackgroundColor(Color.RED);
        }
    }
    private void setHealth(){
        switch (health){
            case 0:
                batteryStatus.setText("error");
                break;
            case BatteryManager.BATTERY_HEALTH_UNKNOWN:
                batteryStatus.setText("Unknown");
                break;
            case BatteryManager.BATTERY_HEALTH_OVERHEAT:
                batteryStatus.setText("Overheat");
                break;
            case BatteryManager.BATTERY_HEALTH_OVER_VOLTAGE:
                batteryStatus.setText("Over voltage");
                break;
            case BatteryManager.BATTERY_HEALTH_DEAD:
                batteryStatus.setText("Dead");
                break;
            case BatteryManager.BATTERY_HEALTH_COLD:
                batteryStatus.setText("Cold");
                break;
            case BatteryManager.BATTERY_HEALTH_GOOD:
                batteryStatus.setText("Good");
                break;
        }
    }

    private void setStatus(){
        String sstatus = null ;
        switch (status){
            case BatteryManager.BATTERY_STATUS_DISCHARGING:
                sstatus = "放电中";
                break;
            case BatteryManager.BATTERY_STATUS_FULL:
                sstatus = "已充满";
                break;
            case BatteryManager.BATTERY_STATUS_NOT_CHARGING:
                sstatus = "未充电";
                break;
            case BatteryManager.BATTERY_STATUS_UNKNOWN:
                sstatus = "未知";
                break;
            case BatteryManager.BATTERY_STATUS_CHARGING:
                sstatus = "正在充电：";
                switch (plugged){
                    case BatteryManager.BATTERY_PLUGGED_AC:
                        sstatus += "AC直连";
                        break;
                    case BatteryManager.BATTERY_PLUGGED_USB:
                        sstatus += "USB";
                        break;
                    case BatteryManager.BATTERY_PLUGGED_WIRELESS:
                        sstatus += "无线充电";
                        break;
                }
                break;
        }
        connectStatus.setText(sstatus);
    }

    private void setRemainTime(Intent intent){

        if (status==BatteryManager.BATTERY_STATUS_CHARGING){
            timeForCharging(volume, ComputeForVolume.getLevel(intent.getIntExtra("level", 0), intent.getIntExtra("scale", 100)));
            chargingOrNot.setText(R.string.needtime_of_charging);
            volumeflag_out = 0;
            if (volumeflag == 0 ){
                time1 = ComputeForVolume.FirstTime();
                level1 =ComputeForVolume.getLevel(intent.getIntExtra("level", 0), intent.getIntExtra("scale", 100));
                volumeflag++;
            }else if (volumeflag == 1 && ComputeForVolume.getLevel
                    (intent.getIntExtra("level",0),intent.getIntExtra("scale",100)) ==(level1+1)){
                if (ComputeForVolume.ComputeVolume(time1,intent)>1500){
                    volume = ComputeForVolume.ComputeVolume(time1,intent);
                    System.out.println(volume);
                    volumeflag = 0;
                    timeForCharging(volume,level1+1);
                }
            }
        }else if (status == BatteryManager.BATTERY_STATUS_FULL){
            chargingOrNot.setText("已充满");
            remainUsableTime.setText("");
        }else {
            if (volumeflag_out==0){
                chargingOrNot.setText(R.string.remain_time);
                //TODO replace volume_out to a value in type
                time1 = ComputeForVolume.FirstTime();
                level1 =ComputeForVolume.getLevel(intent.getIntExtra("level", 0), intent.getIntExtra("scale", 100));
                timeForAwait(volume_out,level1);
                volumeflag_out++;
            }else if ( volumeflag_out == 1 && ComputeForVolume.getLevel
                    (intent.getIntExtra("level",0),intent.getIntExtra("scale",100)) ==(level1-1)){
                chargingOrNot.setText(R.string.remain_usable_time);
                remainTime = ComputeUsingTime(time1, ComputeForVolume.getLevel
                        (intent.getIntExtra("level", 0), intent.getIntExtra("scale", 100)));
                timeToShow(remainTime);
                time1 = ComputeForVolume.FirstTime();
                level1 =ComputeForVolume.getLevel(intent.getIntExtra("level", 0), intent.getIntExtra("scale", 100));
                volumeflag_out = 2;
            }else if (volumeflag_out == 2){
                timeToShow(remainTime);
                if (ComputeForVolume.getLevel
                        (intent.getIntExtra("level",0),intent.getIntExtra("scale",100)) ==(level1-1)){
                    volumeflag_out = 1;
                }
                volumeflag_out = 1;
            } else {
                timeForAwait(volume_out, ComputeForVolume.getLevel(intent.getIntExtra("level", 0), intent.getIntExtra("scale", 100)));
            }

//            Toast.makeText(getActivity(),String.valueOf(volumeflag_out),Toast.LENGTH_SHORT).show();
        }
    }
    //剩余可用时间
    private double ComputeUsingTime(int time1,int level){
        double time;
        Calendar calendar2 = Calendar.getInstance();
        int hour2 = calendar2.get(Calendar.HOUR_OF_DAY);
        int minute2 = calendar2.get(Calendar.MINUTE);
        int seconds2 = calendar2.get(Calendar.SECOND);
        int time2 = hour2*60*60 + minute2*60 + seconds2;
        time = (time2-time1)*level;
        return time;

    }
    //time转换为可显示时间
    private void timeToShow(double time){
        int hour = (int)time/60/60;
        int minute = (int)(time-hour*60*60)/60;
        remainUsableTime.setText(String.valueOf(hour)+"小时"+String.valueOf(minute)+"分钟");
    }
    //剩余待机时间
    private void timeForAwait(double volume_out,double level){
        double time;
        double using = 0.0281481481;
        if (SpecialtyFragment.GPSisOPen(getActivity())){
            using += 0.007;
        }
        if (SpecialtyFragment.is3rd(getActivity())){
            using += 0.001;
        }
        if (SpecialtyFragment.isBluetoothOn(getActivity())){
            using += 0.0006;
        }
        WifiManager wifiManager = (WifiManager) getActivity().getSystemService(Context.WIFI_SERVICE);
        if (wifiManager.isWifiEnabled()){
            using += 0.0013;
        }
        if (SpecialtyFragment.isHeatpointTag(getActivity())){
            using += 0.01;
        }
        time = volume_out*level/100/using;
        int hour = (int)time/60/60;
        int minute = (int)(time-hour*60*60)/60;
        remainUsableTime.setText(String.valueOf(hour)+"小时"+String.valueOf(minute)+"分钟");
    }

    //剩余充电时间
    private void timeForCharging(double volume,double level){
        double time = 0;
        if (plugged == BatteryManager.BATTERY_PLUGGED_AC){
            time = volume*(100-level)/100*60*60/1000;
        }else if (plugged == BatteryManager.BATTERY_PLUGGED_USB){
            time = volume*(100-level)/100*60*60/500;
        }
        int hour = (int)time/60/60;
        int minute = (int)(time-hour*60*60)/60;
        remainUsableTime.setText(String.valueOf(hour)+"小时"+String.valueOf(minute)+"分钟");
    }
}
