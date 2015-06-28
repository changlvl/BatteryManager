package com.morega.batterymanager.UI.Fragment;

import android.app.AlertDialog;
import android.app.PendingIntent;
import android.bluetooth.BluetoothAdapter;
import android.content.ComponentName;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
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

import com.morega.batterymanager.Info.StatusInfo;
import com.morega.batterymanager.R;
import com.morega.batterymanager.Utils.Touchable;
import com.morega.batterymanager.database.DataBaseHelper;
import com.morega.batterymanager.database.SQLiteInfo;
import com.umeng.analytics.MobclickAgent;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Calendar;
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
    private ArrayList<AnimationDrawable> repairList;
    private int wrongNum = (int)(Math.random() *15+1);
    private int wrongs[] = new int[wrongNum];
    private double repairNum = (double)wrongNum*4/15;
    @InjectView(R.id.location_button)
    ImageView locationButton;
    @InjectView(R.id.wifi_button) ImageView wifiButton;
    @InjectView(R.id.data_button) ImageView dataButton;
    @InjectView(R.id.bluetooth_button) ImageView bluetoothButton;
    @InjectView(R.id.heatpoint_button) ImageView heatpointButton;
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
    @InjectView(R.id.repair_11) ImageView repair11;
    @InjectView(R.id.repair_12) ImageView repair12;
    @InjectView(R.id.repair_13) ImageView repair13;
    @InjectView(R.id.repair_14) ImageView repair14;
    @InjectView(R.id.repair_15) ImageView repair15;
    @InjectView(R.id.repair_16) ImageView repair16;
    @InjectView(R.id.repair_17) ImageView repair17;
    @InjectView(R.id.repair_18) ImageView repair18;
    @InjectView(R.id.repair_19) ImageView repair19;
    @InjectView(R.id.repair_20) ImageView repair20;
    @InjectView(R.id.repair_21) ImageView repair21;
    @InjectView(R.id.repair_22) ImageView repair22;
    @InjectView(R.id.repair_23) ImageView repair23;
    @InjectView(R.id.repair_24) ImageView repair24;
    @InjectView(R.id.repair_25) ImageView repair25;
    @InjectView(R.id.repair_26) ImageView repair26;
    @InjectView(R.id.repair_27) ImageView repair27;
    @InjectView(R.id.repair_28) ImageView repair28;
    @InjectView(R.id.repair_29) ImageView repair29;
    @InjectView(R.id.repair_30) ImageView repair30;
    @InjectView(R.id.repair_31) ImageView repair31;
    @InjectView(R.id.repair_32) ImageView repair32;
    @InjectView(R.id.repair_33) ImageView repair33;
    @InjectView(R.id.repair_34) ImageView repair34;
    @InjectView(R.id.repair_35) ImageView repair35;
    @InjectView(R.id.repair_36) ImageView repair36;
    @InjectView(R.id.repair_37) ImageView repair37;
    @InjectView(R.id.repair_38) ImageView repair38;
    @InjectView(R.id.repair_39) ImageView repair39;
    @InjectView(R.id.repair_40) ImageView repair40;
    @InjectView(R.id.repair_41) ImageView repair41;
    @InjectView(R.id.repair_42) ImageView repair42;
    @InjectView(R.id.repair_43) ImageView repair43;
    @InjectView(R.id.repair_44) ImageView repair44;
    @InjectView(R.id.repair_45) ImageView repair45;
    @InjectView(R.id.repair_46) ImageView repair46;
    @InjectView(R.id.repair_47) ImageView repair47;
    @InjectView(R.id.repair_48) ImageView repair48;
    @InjectView(R.id.repair_49) ImageView repair49;
    @InjectView(R.id.repair_50) ImageView repair50;
    @InjectView(R.id.repair_51) ImageView repair51;
    @InjectView(R.id.repair_52) ImageView repair52;
    @InjectView(R.id.repair_53) ImageView repair53;
    @InjectView(R.id.repair_54) ImageView repair54;
    @InjectView(R.id.repair_55) ImageView repair55;
    @InjectView(R.id.repair_56) ImageView repair56;
    @InjectView(R.id.repair_57) ImageView repair57;
    @InjectView(R.id.repair_58) ImageView repair58;
    @InjectView(R.id.repair_59) ImageView repair59;
    @InjectView(R.id.repair_60) ImageView repair60;
    @InjectView(R.id.repair_61) ImageView repair61;
    @InjectView(R.id.repair_62) ImageView repair62;
    @InjectView(R.id.repair_63) ImageView repair63;
    @InjectView(R.id.repair_64) ImageView repair64;
    @InjectView(R.id.repair_65) ImageView repair65;
    @InjectView(R.id.repair_66) ImageView repair66;
    @InjectView(R.id.repair_67) ImageView repair67;
    @InjectView(R.id.repair_68) ImageView repair68;
    @InjectView(R.id.repair_69) ImageView repair69;
    @InjectView(R.id.repair_70) ImageView repair70;
    @InjectView(R.id.repair_71) ImageView repair71;
    @InjectView(R.id.repair_72) ImageView repair72;
    @InjectView(R.id.repair_73) ImageView repair73;
    @InjectView(R.id.repair_74) ImageView repair74;
    @InjectView(R.id.repair_75) ImageView repair75;
    @InjectView(R.id.repair_76) ImageView repair76;
    @InjectView(R.id.repair_77) ImageView repair77;
    @InjectView(R.id.repair_78) ImageView repair78;
    @InjectView(R.id.repair_79) ImageView repair79;
    @InjectView(R.id.repair_80) ImageView repair80;
    @InjectView(R.id.repair_81) ImageView repair81;
    @InjectView(R.id.repair_82) ImageView repair82;
    @InjectView(R.id.repair_83) ImageView repair83;
    @InjectView(R.id.repair_84) ImageView repair84;
    @InjectView(R.id.repair_85) ImageView repair85;
    @InjectView(R.id.repair_86) ImageView repair86;
    @InjectView(R.id.repair_87) ImageView repair87;
    @InjectView(R.id.repair_88) ImageView repair88;
    @InjectView(R.id.repair_89) ImageView repair89;
    @InjectView(R.id.repair_90) ImageView repair90;
    @InjectView(R.id.repair_91) ImageView repair91;
    @InjectView(R.id.repair_92) ImageView repair92;
    @InjectView(R.id.repair_93) ImageView repair93;
    @InjectView(R.id.repair_94) ImageView repair94;
    @InjectView(R.id.repair_95) ImageView repair95;
    @InjectView(R.id.repair_96) ImageView repair96;
    @InjectView(R.id.repair_97) ImageView repair97;
    @InjectView(R.id.repair_98) ImageView repair98;
    @InjectView(R.id.repair_99) ImageView repair99;
    @InjectView(R.id.repair_100) ImageView repair100;
    @InjectView(R.id.repair_101) ImageView repair101;
    @InjectView(R.id.repair_102) ImageView repair102;
    @InjectView(R.id.repair_103) ImageView repair103;
    @InjectView(R.id.repair_104) ImageView repair104;
    @InjectView(R.id.repair_105) ImageView repair105;
    @InjectView(R.id.repair_106) ImageView repair106;
    @InjectView(R.id.repair_107) ImageView repair107;
    @InjectView(R.id.repair_108) ImageView repair108;
    @InjectView(R.id.repair_109) ImageView repair109;
    @InjectView(R.id.repair_110) ImageView repair110;
    @InjectView(R.id.repair_111) ImageView repair111;
    @InjectView(R.id.repair_112) ImageView repair112;
    @InjectView(R.id.repair_113) ImageView repair113;
    @InjectView(R.id.repair_114) ImageView repair114;
    @InjectView(R.id.repair_115) ImageView repair115;
    @InjectView(R.id.repair_116) ImageView repair116;
    @InjectView(R.id.repair_117) ImageView repair117;
    @InjectView(R.id.repair_118) ImageView repair118;
    @InjectView(R.id.repair_119) ImageView repair119;
    @InjectView(R.id.repair_120) ImageView repair120;
    @InjectView(R.id.repair_121) ImageView repair121;
    @InjectView(R.id.repair_122) ImageView repair122;
    @InjectView(R.id.repair_123) ImageView repair123;
    @InjectView(R.id.repair_124) ImageView repair124;
    @InjectView(R.id.repair_125) ImageView repair125;
    @InjectView(R.id.repair_126) ImageView repair126;
    @InjectView(R.id.repair_127) ImageView repair127;
    @InjectView(R.id.repair_128) ImageView repair128;
    @InjectView(R.id.repair_129) ImageView repair129;
    @InjectView(R.id.repair_130) ImageView repair130;
    @InjectView(R.id.repair_131) ImageView repair131;

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
                    Touchable.setTouchable(true);
                    break;
                case 4:
                    startRepairButton.setClickable(false);
                    setDBNoWrongs();
                    AlertDialog.Builder successBuilder = new AlertDialog.Builder(getActivity());
                    successBuilder.setMessage("修复成功！经过修复，您的电池的续航能力提高了"+format(repairNum)+"!");
                    successBuilder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            initAnim();
                        }
                    });
                    successBuilder.create().show();
                    Touchable.setTouchable(true);
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
                            Touchable.setTouchable(false);
                            startRepairButton.setText("正在努力修复");
                            startRepairButton.setClickable(false);
                            dialog.dismiss();
                            for (int i = 0; i < wrongNum; i++) {
                                list.get(wrongs[i]).setBackgroundResource(R.drawable.drawable_anim_repair);
                                AnimationDrawable anim = (AnimationDrawable) list.get(wrongs[i]).getBackground();
                                repairList.add(anim);
                            }
                            int repairTime = 0;
                            Timer timer = new Timer();
                            //startRepairButton.setClickable(false);
                            for (int k = 0; k < wrongNum; k++) {
                                final int j = k;
                                TimerTask task = new TimerTask() {
                                    @Override
                                    public void run() {

                                        repairList.get(j).stop();
                                        repairList.get(j).start();
                                    }

                                };
                                timer.schedule(task, repairTime);
                                repairTime += 7000;
                            }
                            new Thread() {
                                @Override
                                public void run() {
                                    try {
                                        Thread.currentThread().sleep(7000 * wrongNum);
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                    mHandler.sendEmptyMessage(4);
                                }
                            }.start();
                        }
                    });

                    builder.create().show();
                    Touchable.setTouchable(true);
                    break;
                case 6:
                    startRepairButton.setText("开始检测");
                    startRepairButton.setClickable(true);
                    Touchable.setTouchable(true);
                    break;
            }
        }
    };
    private void setDBNoWrongs(){
        DataBaseHelper helper = new DataBaseHelper(getActivity(), SQLiteInfo.DB.DB_NAME);
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        Calendar calendar = Calendar.getInstance();
        values.put("has_wrongs","no");
        StatusInfo.setHas_wrongs("no");
        values.put("time_tick",String.valueOf(calendar.get(Calendar.MONTH))+"."+String.valueOf(calendar.get(Calendar.DAY_OF_MONTH)));
        db.insert("battery",null,values);
    }
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
                    locationButton.setBackgroundResource(R.drawable.gps_hui);
                    locationTag = false;
                }else {
                    openGPS(getActivity());
                    locationButton.setBackgroundResource(R.drawable.gps_l);
                    locationTag = true;
                }
                break;
            case R.id.wifi_button:
                if (wifiTag){
                    WifiManager wifiManager = (WifiManager) getActivity().getSystemService(Context.WIFI_SERVICE);
                    wifiManager.setWifiEnabled(!wifiTag);
                    wifiButton.setBackgroundResource(R.drawable.wifi_hui);
                    wifiTag = false;
                }else {
                    WifiManager wifiManager = (WifiManager) getActivity().getSystemService(Context.WIFI_SERVICE);
                    wifiManager.setWifiEnabled(!wifiTag);
                    wifiButton.setBackgroundResource(R.drawable.wifi_l);
                    wifiTag = true;
                }
                break;
            case R.id.data_button:
                if (dataTag){
                    setMobileData(getActivity(), false);
                    dataButton.setBackgroundResource(R.drawable.shuju_hui);
                    dataTag = false;
                }else {
                    setMobileData(getActivity(), true);
                    dataButton.setBackgroundResource(R.drawable.shuju_l);
                    dataTag = true;
                }
                break;
            case R.id.bluetooth_button:
                if (bluetoothTag){
                    BluetoothAdapter mBluetoothAdapter = BluetoothAdapter
                            .getDefaultAdapter();
                    mBluetoothAdapter.disable();
                    bluetoothButton.setBackgroundResource(R.drawable.lanya_hui);
                    bluetoothTag = false;
                }else {
                    BluetoothAdapter mBluetoothAdapter = BluetoothAdapter
                            .getDefaultAdapter();
                    mBluetoothAdapter.enable();
                    bluetoothButton.setBackgroundResource(R.drawable.lanya_l);
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

        initWrongs();
        sortWrongs();
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
        repairList = new ArrayList<>();
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
        list.add(repair11);
        list.add(repair12);
        list.add(repair13);
        list.add(repair14);
        list.add(repair15);
        list.add(repair16);
        list.add(repair17);
        list.add(repair18);
        list.add(repair19);
        list.add(repair20);
        list.add(repair21);
        list.add(repair22);
        list.add(repair23);
        list.add(repair24);
        list.add(repair25);
        list.add(repair26);
        list.add(repair27);
        list.add(repair28);
        list.add(repair29);
        list.add(repair30);
        list.add(repair31);
        list.add(repair32);
        list.add(repair33);
        list.add(repair34);
        list.add(repair35);
        list.add(repair36);
        list.add(repair37);
        list.add(repair38);
        list.add(repair39);
        list.add(repair40);
        list.add(repair41);
        list.add(repair42);
        list.add(repair43);
        list.add(repair44);
        list.add(repair45);
        list.add(repair46);
        list.add(repair47);
        list.add(repair48);
        list.add(repair49);
        list.add(repair50);
        list.add(repair51);
        list.add(repair52);
        list.add(repair53);
        list.add(repair54);
        list.add(repair55);
        list.add(repair56);
        list.add(repair57);
        list.add(repair58);
        list.add(repair59);
        list.add(repair60);
        list.add(repair61);
        list.add(repair62);
        list.add(repair63);
        list.add(repair64);
        list.add(repair65);
        list.add(repair66);
        list.add(repair67);
        list.add(repair68);
        list.add(repair69);
        list.add(repair70);
        list.add(repair71);
        list.add(repair72);
        list.add(repair73);
        list.add(repair74);
        list.add(repair75);
        list.add(repair76);
        list.add(repair77);
        list.add(repair78);
        list.add(repair79);
        list.add(repair80);
        list.add(repair81);
        list.add(repair82);
        list.add(repair83);
        list.add(repair84);
        list.add(repair85);
        list.add(repair86);
        list.add(repair87);
        list.add(repair88);
        list.add(repair89);
        list.add(repair90);
        list.add(repair91);
        list.add(repair92);
        list.add(repair93);
        list.add(repair94);
        list.add(repair95);
        list.add(repair96);
        list.add(repair97);
        list.add(repair98);
        list.add(repair99);
        list.add(repair100);
        list.add(repair101);
        list.add(repair102);
        list.add(repair103);
        list.add(repair104);
        list.add(repair105);
        list.add(repair106);
        list.add(repair107);
        list.add(repair108);
        list.add(repair109);
        list.add(repair110);
        list.add(repair111);
        list.add(repair112);
        list.add(repair113);
        list.add(repair114);
        list.add(repair115);
        list.add(repair116);
        list.add(repair117);
        list.add(repair118);
        list.add(repair119);
        list.add(repair120);
        list.add(repair121);
        list.add(repair122);
        list.add(repair123);
        list.add(repair124);
        list.add(repair125);
        list.add(repair126);
        list.add(repair127);
        list.add(repair128);
        list.add(repair129);
        list.add(repair130);
        list.add(repair131);


        registeAnim(container);


        return view;
    }
    private void initWrongs(){
        for (int i = 0;i<wrongNum;i++){
            int wrong = (int)(Math.random()*131);
            boolean sameWrong = false;
            for (int j = 0;j<wrongNum;j++){
                if (wrong==wrongs[j]){
                    sameWrong = true;
                }
            }
            if (!sameWrong){
                wrongs[i] = wrong;
            }else {
                wrongNum --;
            }
        }
    }
    private boolean compareWithWrong(int i){
        for (int j = 0;j<wrongNum;j++){
            if (i == wrongs[j]){
                return true;
            }
        }
        return false;
    }

    private void sortWrongs(){
        for (int i = 0;i<wrongNum;i++){
            int min = wrongs[i];
            for (int j = i;j<wrongNum;j++){
                if (wrongs[j]<min){
                    wrongs[i] = wrongs[j];
                    wrongs[j] = min;
                    min = wrongs[i];
                }
            }
        }
    }
    @Override
    public void onStart() {
        super.onStart();
        init();
    }

    //注册动画
    private void registeAnim(final ViewGroup container){
        if (StatusInfo.getHas_wrongs().equals("yes")){
            for (int i=0;i<131;i++){
                if (compareWithWrong(i)){
                    list.get(i).setBackgroundResource(R.drawable.drawable_anim_wrong);
                }else {
                    list.get(i).setBackgroundResource(R.drawable.drawable_anim);
                }
                AnimationDrawable anim = (AnimationDrawable) list.get(i).getBackground();
                animList.add(anim);
            }
        }else {
            for (int i=0;i<131;i++){
                list.get(i).setBackgroundResource(R.drawable.drawable_anim);
                AnimationDrawable anim = (AnimationDrawable) list.get(i).getBackground();
                animList.add(anim);
            }
        }
        startRepairButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Touchable.setTouchable(false);
                if (startRepairButton.getText().equals("修复")) {
                    Touchable.setTouchable(false);
                    startRepairButton.setText("正在努力修复");
                    startRepairButton.setClickable(false);

                    for (int i = 0; i < wrongNum; i++) {
                        list.get(wrongs[i]).setBackgroundResource(R.drawable.drawable_anim_repair);
                        AnimationDrawable anim = (AnimationDrawable) list.get(wrongs[i]).getBackground();
                        repairList.add(anim);
                    }
                    int repairTime = 0;
                    Timer timer = new Timer();
                    //startRepairButton.setClickable(false);
                    for (int k = 0; k < wrongNum; k++) {
                        final int j = k;
                        TimerTask task = new TimerTask() {
                            @Override
                            public void run() {

                                repairList.get(j).stop();
                                repairList.get(j).start();
                            }

                        };
                        timer.schedule(task, repairTime);
                        repairTime += 7000;
                    }
                    new Thread() {
                        @Override
                        public void run() {
                            try {
                                Thread.currentThread().sleep(7000*wrongNum);
                                mHandler.sendEmptyMessage(4);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }.start();
                } else {
                    startRepairButton.setClickable(false);
                    WifiManager wifiManager = (WifiManager) getActivity().getSystemService(Context.WIFI_SERVICE);
                    if (is3rd(getActivity()) || wifiManager.isWifiEnabled()) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                        builder.setMessage("为了保证检测和修复的质量，请不要在检测和修复过程中离开当前页面。");
                        builder.setTitle("提示");
                        builder.setNegativeButton("等会再说", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                mHandler.sendEmptyMessage(6);
                            }
                        });
                        builder.setPositiveButton("马上开始", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Touchable.setTouchable(false);
                                int time = 0;
                                Timer timer = new Timer();
                                //startRepairButton.setClickable(false);
                                for (int i = 0; i < 131; i++) {
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
                                if (StatusInfo.getHas_wrongs().equals("yes")) {
                                    new Thread() {
                                        @Override
                                        public void run() {
                                            try {
                                                Thread.currentThread().sleep(262000);
                                                Touchable.setTouchable(true);
                                            } catch (Exception e) {
                                                e.printStackTrace();
                                            }
                                            mHandler.sendEmptyMessage(5);
                                        }
                                    }.start();
                                } else {
                                    new Thread() {
                                        @Override
                                        public void run() {
                                            try {
                                                Thread.currentThread().sleep(262000);
                                                Touchable.setTouchable(true);
                                            } catch (Exception e) {
                                                e.printStackTrace();
                                            }

                                            mHandler.sendEmptyMessage(3);
                                        }
                                    }.start();
                                }
                            }
                        });
                        builder.create().show();
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
                        Touchable.setTouchable(true);
                    }
                }
            }
        });
    }
    //每次检查完成后，要初始化图片，以便下一次检测
    private void initAnim(){
        startRepairButton.setText("开始检测");
        startRepairButton.setClickable(true);
        animList = new ArrayList<>();
        for (int i=0;i<131;i++){
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
            locationButton.setBackgroundResource(R.drawable.gps_l);
        }else {
            locationButton.setBackgroundResource(R.drawable.gps_hui);
        }

        WifiManager wifiManager = (WifiManager) getActivity().getSystemService(Context.WIFI_SERVICE);
        wifiTag = wifiManager.isWifiEnabled();
        if (wifiTag){
            wifiButton.setBackgroundResource(R.drawable.wifi_l);
        }else {
            wifiButton.setBackgroundResource(R.drawable.wifi_hui);
        }

        dataTag = is3rd(getActivity());
        if (dataTag){
            dataButton.setBackgroundResource(R.drawable.shuju_l);
        }else {
            dataButton.setBackgroundResource(R.drawable.shuju_hui);
        }
        //判断蓝牙的初始状态
        bluetoothTag = isBluetoothOn(getActivity());
        if (bluetoothTag){
            bluetoothTag = true;
            bluetoothButton.setBackgroundResource(R.drawable.lanya_l);
        }else {
            bluetoothTag = false;
            bluetoothButton.setBackgroundResource(R.drawable.lanya_hui);
        }

        heatpointTag = isHeatpointTag(getActivity());
        if (heatpointTag){
            heatpointButton.setBackgroundResource(R.drawable.redian_l);
        }else {
            heatpointButton.setBackgroundResource(R.drawable.redian_hui);
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
    private String format(double size) {
        return String.format("%1$.2f%%", size);
        // return new BigDecimal("" + size).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
    }
    @Override
    public void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd("MainScreen");
    }

}
