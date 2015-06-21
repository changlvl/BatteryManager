package com.morega.batterymanager.UI.Fragment;

import android.app.AlertDialog;
import android.app.PendingIntent;
import android.bluetooth.BluetoothAdapter;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.morega.batterymanager.R;
import com.umeng.analytics.MobclickAgent;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Morega03 on 2015/5/24.
 */
public class SpecialtyFragment extends BaseFragment implements View.OnClickListener,View.OnLongClickListener{
//    OnHeadlineSelectedListener mCallback;
    private boolean locationTag = false;
    private boolean wifiTag = false;
    private boolean dataTag = false;
    private boolean bluetoothTag = false;
    private boolean heatpointTag = false;
    private ArrayList<ImageView> list;
    private ArrayList<AnimationDrawable> animList;
    private boolean hasWrong = true;
    private int wrong = (int)(Math.random() * 10);

    @InjectView(R.id.location_button) Button locationButton;
    @InjectView(R.id.wifi_button) Button wifiButton;
    @InjectView(R.id.data_button) Button dataButton;
    @InjectView(R.id.bluetooth_button) Button bluetoothButton;
    @InjectView(R.id.heatpoint_button) Button heatpointButton;
    @InjectView(R.id.repair_1) ImageView repair1;
    @InjectView(R.id.repair_2) ImageView repair2;
    @InjectView(R.id.repair_3) ImageView repair3;
    @InjectView(R.id.repair_4) ImageView repair4;
    @InjectView(R.id.repair_5) ImageView repair5;
    @InjectView(R.id.repair_6) ImageView repair6;
    @InjectView(R.id.repair_7) ImageView repair7;
    @InjectView(R.id.repair_8) ImageView repair8;
    @InjectView(R.id.repair_9) ImageView repair9;
    @InjectView(R.id.repair_10) ImageView repair10;
    @InjectView(R.id.start_repair_button) Button startRepairButton;

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg){
            switch (msg.what){
                case 1:
                    initAnim();
                    break;
                case 2:
                    startRepairButton.setText("修复");
                    startRepairButton.setClickable(true);
                    break;
                case 3:
                    initAnim();
                    Toast.makeText(getActivity(), "您的电池非常健康哦", Toast.LENGTH_SHORT).show();
                    break;
                case 4:
                    startRepairButton.setClickable(false);
                    AlertDialog.Builder successBuilder = new AlertDialog.Builder(getActivity());
                    successBuilder.setMessage("修复成功！经过修复，您的电池的续航能力提高了2%!");
                    successBuilder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            initAnim();
                        }
                    });
                    successBuilder.create().show();
                    break;
                case 5:
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setMessage("您的电池存在一些小问题，修复成功后将提高电池的续航能力");
                    builder.setTitle("提示");
                    builder.setNegativeButton("算了", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            mHandler.sendEmptyMessage(2);
                        }
                    });
                    builder.setPositiveButton("修复", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            startRepairButton.setText("正在努力修复");
                            startRepairButton.setClickable(false);
                            dialog.dismiss();
                            list.get(wrong).setBackgroundResource(R.drawable.drawable_anim_repair);
                            AnimationDrawable anim = (AnimationDrawable) list.get(wrong).getBackground();
                            anim.stop();
                            anim.start();
                            new Thread(){
                                @Override
                                public void run(){
                                    try {
                                        Thread.currentThread().sleep(7000);
                                    }catch (Exception e){
                                        e.printStackTrace();
                                    }
                                    mHandler.sendEmptyMessage(4);
                                }
                            }.start();
                        }
                    });

                    builder.create().show();
                    hasWrong = false;
            }
        }
    };
    @Override
    public boolean onLongClick(View v) {
        switch (v.getId()){
            case R.id.location_button:
                startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                break;
            case R.id.wifi_button:
                startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
                break;
            case R.id.data_button:
                if (Build.VERSION.SDK_INT >= 21) {
                    Intent intent = new Intent();
                    intent.setComponent(new ComponentName(
                            "com.android.settings",
                            "com.android.settings.Settings$DataUsageSummaryActivity"));
                    startActivity(intent);
                }else {
                    startActivity(new Intent(Settings.ACTION_DATA_ROAMING_SETTINGS));
                }
                break;
            case R.id.bluetooth_button:
                startActivity(new Intent(Settings.ACTION_BLUETOOTH_SETTINGS));
                break;
            case R.id.heatpoint_button:
                startActivity(new Intent(Settings.ACTION_WIRELESS_SETTINGS));
                break;
        }

        return false;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.location_button:
                if (locationTag){
                    openGPS(getActivity());
                    locationButton.setText("off");
                    locationButton.setBackgroundColor(Color.GRAY);
                    locationTag = false;
                }else {
                    openGPS(getActivity());
                    locationButton.setText("on");
                    locationButton.setBackgroundColor(Color.BLUE);
                    locationTag = true;
                }
                break;
            case R.id.wifi_button:
                if (wifiTag){
                    WifiManager wifiManager = (WifiManager) getActivity().getSystemService(Context.WIFI_SERVICE);
                    wifiManager.setWifiEnabled(!wifiTag);
                    wifiButton.setText("off");
                    wifiButton.setBackgroundColor(Color.GRAY);
                    wifiTag = false;
                }else {
                    WifiManager wifiManager = (WifiManager) getActivity().getSystemService(Context.WIFI_SERVICE);
                    wifiManager.setWifiEnabled(!wifiTag);
                    wifiButton.setText("on");
                    wifiButton.setBackgroundColor(Color.BLUE);
                    wifiTag = true;
                }
                break;
            case R.id.data_button:
                if (dataTag){
                    setMobileData(getActivity(), false);
                    dataButton.setText("off");
                    dataButton.setBackgroundColor(Color.GRAY);
                    dataTag = false;
                }else {
                    setMobileData(getActivity(), true);
                    dataButton.setText("on");
                    dataButton.setBackgroundColor(Color.BLUE);
                    dataTag = true;
                }
                break;
            case R.id.bluetooth_button:
                if (bluetoothTag){
                    BluetoothAdapter mBluetoothAdapter = BluetoothAdapter
                            .getDefaultAdapter();
                    mBluetoothAdapter.disable();
                    bluetoothButton.setText("off");
                    bluetoothButton.setBackgroundColor(Color.GRAY);
                    bluetoothTag = false;
                }else {
                    BluetoothAdapter mBluetoothAdapter = BluetoothAdapter
                            .getDefaultAdapter();
                    mBluetoothAdapter.enable();
                    bluetoothButton.setText("on");
                    bluetoothButton.setBackgroundColor(Color.BLUE);
                    bluetoothTag = true;
                }
                break;
            case R.id.heatpoint_button:
                if (heatpointTag){
                    startActivity(new Intent(Settings.ACTION_WIRELESS_SETTINGS));
                    init();
                }else {
                    startActivity(new Intent(Settings.ACTION_WIRELESS_SETTINGS));
                    init();
                }
                break;
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }
    @Override
    public void onResume() {
        super.onResume();
        MobclickAgent.onPageStart("MainScreen"); //统计页面
    }

    @Override
    public View onCreateView(LayoutInflater inflater , ViewGroup container,
                             Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_specialty,container,false);

        ButterKnife.inject(this, view);


        init();
        locationButton.setOnClickListener(this);
        locationButton.setOnLongClickListener(this);
        wifiButton.setOnClickListener(this);
        wifiButton.setOnLongClickListener(this);
        dataButton.setOnClickListener(this);
        dataButton.setOnLongClickListener(this);

        bluetoothButton.setOnClickListener(this);
        bluetoothButton.setOnLongClickListener(this);
        heatpointButton.setOnClickListener(this);
        heatpointButton.setOnLongClickListener(this);
        list = new ArrayList<>();
        animList = new ArrayList<>();
        list.add(repair1);
        list.add(repair2);
        list.add(repair3);
        list.add(repair4);
        list.add(repair5);
        list.add(repair6);
        list.add(repair7);
        list.add(repair8);
        list.add(repair9);
        list.add(repair10);

        registeAnim();


        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        init();
    }
    //注册动画
    private void registeAnim(){

        for (int i=0;i<10;i++){
            if (i == wrong){
                list.get(i).setBackgroundResource(R.drawable.drawable_anim_wrong);
            }else {
                list.get(i).setBackgroundResource(R.drawable.drawable_anim);
            }
            AnimationDrawable anim = (AnimationDrawable) list.get(i).getBackground();
            animList.add(anim);
        }
        startRepairButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (startRepairButton.getText().equals("修复")){
                    startRepairButton.setText("正在努力修复");
                    startRepairButton.setClickable(false);
                    list.get(wrong).setBackgroundResource(R.drawable.drawable_anim_repair);
                    AnimationDrawable anim = (AnimationDrawable) list.get(wrong).getBackground();
                    anim.stop();
                    anim.start();
                    new Thread(){
                        @Override
                        public void run(){
                            try {
                                Thread.currentThread().sleep(7000);
                                mHandler.sendEmptyMessage(4);
                            }catch (Exception e){
                                e.printStackTrace();
                            }
                        }
                    }.start();
                }
                else{
                    startRepairButton.setClickable(false);
                    WifiManager wifiManager = (WifiManager) getActivity().getSystemService(Context.WIFI_SERVICE);
                    if (is3rd(getActivity()) || wifiManager.isWifiEnabled()) {
                        int time = 0;
                        Timer timer = new Timer();
                        //startRepairButton.setClickable(false);
                        for (int i = 0; i < 10; i++) {
                            final int j = i;
                            TimerTask task = new TimerTask() {
                                @Override
                                public void run() {

                                    animList.get(j).stop();
                                    animList.get(j).start();
                                }

                            };
                            timer.schedule(task, time);
                            time += 2000;
                            startRepairButton.setText("正在检查电池");
                        }
                        if (hasWrong) {
                            final int i = wrong;
                            new Thread(){
                                @Override
                                public void run(){
                                    try {
                                        Thread.currentThread().sleep(20000);
                                    }catch (Exception e){
                                        e.printStackTrace();
                                    }
                                    mHandler.sendEmptyMessage(5);
                                }
                            }.start();
                        } else {
                            new Thread(){
                                @Override
                                public void run(){
                                    try {
                                        Thread.currentThread().sleep(20000);
                                    }catch (Exception e){
                                        e.printStackTrace();
                                    }

                                    mHandler.sendEmptyMessage(3);
                                }
                            }.start();
                        }
                    } else {
                        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                        builder.setMessage("只有在联通网络状态下才能修复电池噢！~");
                        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                        builder.create().show();
                    }
                }
            }
        });
    }
    //每次检查完成后，要初始化图片，以便下一次检测
    private void initAnim(){
        startRepairButton.setText("start");
        startRepairButton.setClickable(true);
        animList = new ArrayList<>();
        for (int i=0;i<10;i++){
            list.get(i).setBackgroundResource(R.drawable.ic_launcher);
            list.get(i).setBackgroundResource(R.drawable.drawable_anim);
            AnimationDrawable anim = (AnimationDrawable) list.get(i).getBackground();
            animList.add(anim);
        }
    }

    //设置几个Button的初始状态
    private void init(){
        locationTag = GPSisOPen(getActivity());
        if (locationTag){
            locationButton.setText("on");
            locationButton.setBackgroundColor(Color.BLUE);
        }else {
            locationButton.setText("off");
            locationButton.setBackgroundColor(Color.GRAY);
        }

        WifiManager wifiManager = (WifiManager) getActivity().getSystemService(Context.WIFI_SERVICE);
        wifiTag = wifiManager.isWifiEnabled();
        if (wifiTag){
            wifiButton.setText("on");
            wifiButton.setBackgroundColor(Color.BLUE);
        }else {
            wifiButton.setText("off");
            wifiButton.setBackgroundColor(Color.GRAY);
        }

        dataTag = is3rd(getActivity());
        if (dataTag){
            dataButton.setText("on");
            dataButton.setBackgroundColor(Color.BLUE);
        }else {
            dataButton.setText("off");
            dataButton.setBackgroundColor(Color.GRAY);
        }
        //判断蓝牙的初始状态
        bluetoothTag = isBluetoothOn(getActivity());
        if (bluetoothTag){
            bluetoothTag = true;
            bluetoothButton.setText("on");
            bluetoothButton.setBackgroundColor(Color.BLUE);
        }else {
            bluetoothTag = false;
            bluetoothButton.setText("off");
            bluetoothButton.setBackgroundColor(Color.GRAY);
        }

        heatpointTag = isHeatpointTag(getActivity());
        if (heatpointTag){
            heatpointButton.setText("on");
            heatpointButton.setBackgroundColor(Color.BLUE);
        }else {
            heatpointButton.setText("off");
            heatpointButton.setBackgroundColor(Color.GRAY);
        }
    }


    public static boolean isHeatpointTag(Context context){
        WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        try {
            Method method = wifiManager.getClass().getMethod("getWifiApState");
            int i = (int)method.invoke(wifiManager);
            System.out.println("heat:" + i);
            if (i == 13){
                return true;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }


    //判断蓝牙的初始状态
    public static boolean isBluetoothOn(Context context){
        BluetoothAdapter mBluetoothAdapter = BluetoothAdapter
                .getDefaultAdapter();
        if (mBluetoothAdapter == null) {
            Toast.makeText(context, "本机没有找到蓝牙硬件或驱动！", Toast.LENGTH_SHORT).show();
        }
        return mBluetoothAdapter.isEnabled();
    }


    //开关数据连接
    public static void setMobileData(Context pContext, boolean pBoolean) {
        if (Build.VERSION.SDK_INT>=21){
            Intent intent = new Intent();
            intent.setComponent(new ComponentName(
                    "com.android.settings",
                    "com.android.settings.Settings$DataUsageSummaryActivity"));
            pContext.startActivity(intent);

        }else {
            try {

                ConnectivityManager mConnectivityManager = (ConnectivityManager) pContext.getSystemService(Context.CONNECTIVITY_SERVICE);

                Class ownerClass = mConnectivityManager.getClass();

                Class[] argsClass = new Class[1];
                argsClass[0] = boolean.class;

                Method method = ownerClass.getMethod("setMobileDataEnabled", argsClass);

                method.invoke(mConnectivityManager, pBoolean);

            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                System.out.println("移动数据设置错误: " + e.toString());
            }
        }

    }
    //判断是否开启数据连接    注意：当Wifi开启的时候，数据连接会显示未打开
    public static boolean is3rd(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkINfo = cm.getActiveNetworkInfo();
        return networkINfo != null
            && networkINfo.getType() == ConnectivityManager.TYPE_MOBILE;
    }

    /**
     * 判断GPS是否开启，GPS或者AGPS开启一个就认为是开启的
     * @return true 表示开启
     */
    public static  boolean GPSisOPen( Context context) {
        LocationManager locationManager
                = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        // 通过GPS卫星定位，定位级别可以精确到街（通过24颗卫星定位，在室外和空旷的地方定位准确、速度快）
        boolean gps = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        // 通过WLAN或移动网络(3G/2G)确定的位置（也称作AGPS，辅助GPS定位。主要用于在室内或遮盖物（建筑群或茂密的深林等）密集的地方定位）
        boolean network = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        return gps || network;

    }

    /**
     * 强制帮用户打开GPS
     */
    public static void openGPS(Context context) {
        if (Build.VERSION.SDK_INT>=21){
            context.startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
        }
        else {
            Intent GPSIntent = new Intent();
            GPSIntent.setClassName("com.android.settings",
                    "com.android.settings.widget.SettingsAppWidgetProvider");
            GPSIntent.addCategory("android.intent.category.ALTERNATIVE");
            GPSIntent.setData(Uri.parse("custom:3"));
            try {
                PendingIntent.getBroadcast(context, 0, GPSIntent, 0).send();
            } catch (PendingIntent.CanceledException e) {
                e.printStackTrace();
            }
        }

    }
    @Override
    public void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd("MainScreen");
    }

}
