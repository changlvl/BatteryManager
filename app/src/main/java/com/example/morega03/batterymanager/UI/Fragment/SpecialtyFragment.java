package com.example.morega03.batterymanager.UI.Fragment;

import android.app.PendingIntent;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.morega03.batterymanager.R;

import java.lang.reflect.Method;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Morega03 on 2015/5/24.
 */
public class SpecialtyFragment extends BaseFragment implements View.OnClickListener,View.OnLongClickListener{

    private boolean locationTag = false;
    private boolean wifiTag = false;
    private boolean dataTag = false;
    private boolean bluetoothTag = false;
    private boolean heatpointTag = false;
    private boolean morePowerSavingTag = false;
    @InjectView(R.id.location_button) Button locationButton;
    @InjectView(R.id.wifi_button) Button wifiButton;
    @InjectView(R.id.data_button) Button dataButton;
    @InjectView(R.id.bluetooth_button) Button bluetoothButton;
    @InjectView(R.id.heatpoint_button) Button heatpointButton;
    @InjectView(R.id.more_power_saving_button) Button morePowerSavingButton;

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
                startActivity(new Intent(Settings.ACTION_DATA_ROAMING_SETTINGS));
                break;
            case R.id.bluetooth_button:
                startActivity(new Intent(Settings.ACTION_BLUETOOTH_SETTINGS));
                break;
            case R.id.heatpoint_button:
                startActivity(new Intent(Settings.ACTION_WIRELESS_SETTINGS));
                break;
            case R.id.more_power_saving_button:
                startActivity(new Intent(Settings.ACTION_LOCALE_SETTINGS));
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
                    setMobileData(getActivity(),false);
                    dataButton.setText("off");
                    dataButton.setBackgroundColor(Color.GRAY);
                    dataTag = false;
                }else {
                    setMobileData(getActivity(),true);
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
            case R.id.more_power_saving_button:
                if (morePowerSavingTag){
                    morePowerSavingButton.setText("off");
                    morePowerSavingButton.setBackgroundColor(Color.GRAY);
                    morePowerSavingTag = false;
                }else {
                    morePowerSavingButton.setText("on");
                    morePowerSavingButton.setBackgroundColor(Color.BLUE);
                    morePowerSavingTag = true;
                }
                break;
        }
    }

    private static class FragmentHolder{
        private static final SpecialtyFragment INSTANCE = new SpecialtyFragment();

    }

    public static final SpecialtyFragment getInstance(){
        return FragmentHolder.INSTANCE;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
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
        morePowerSavingButton.setOnClickListener(this);


        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        init();
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

    //判断是否开启数据连接    注意：当Wifi开启的时候，数据连接会显示未打开
    public static boolean is3rd(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkINfo = cm.getActiveNetworkInfo();
        if (networkINfo != null
                && networkINfo.getType() == ConnectivityManager.TYPE_MOBILE) {
            return true;
        }
        return false;
    }

    /**
     * 判断GPS是否开启，GPS或者AGPS开启一个就认为是开启的
     * @param context
     * @return true 表示开启
     */
    public static  boolean GPSisOPen( Context context) {
        LocationManager locationManager
                = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        // 通过GPS卫星定位，定位级别可以精确到街（通过24颗卫星定位，在室外和空旷的地方定位准确、速度快）
        boolean gps = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        // 通过WLAN或移动网络(3G/2G)确定的位置（也称作AGPS，辅助GPS定位。主要用于在室内或遮盖物（建筑群或茂密的深林等）密集的地方定位）
        boolean network = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        if (gps || network) {
            return true;
        }

        return false;
    }

    /**
     * 强制帮用户打开GPS
     * @param context
     */
    public static final void openGPS(Context context) {
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
