package com.example.morega03.batterymanager.UI;

import android.app.ActionBar;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.view.ViewPager;

import com.example.morega03.batterymanager.Adapter.MyViewPagerAdapter;
import com.example.morega03.batterymanager.R;

import butterknife.ButterKnife;


public class MainActivity extends BaseActivity{
//    private TextView hello;
//    private String BetteryMessage;
//    //一个标识符，判断第几个时间
//    private int volumeFlag = 0;
//    //一个中间变量
//    private int time1 = 0;
//    //电池容量
//    private double volume = 1500;
//    private int level1 = 0;
    private MyViewPagerAdapter myViewPagerAdapter;
    public static MainActivity mainActivity;
    FragmentManager fragmentManager;
    private ActionBar actionBar;
    private ViewPager mViewPager;
    ActionBar.TabListener mTabListener;
    private ActionBar.Tab mBatteryStatusTab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);

//        IntentFilter intentFilter = new IntentFilter();
//        //字面意思应该是发生变化，其实好像是只要找得到电池，就符合这个状态
//        intentFilter.addAction(Intent.ACTION_BATTERY_CHANGED);
//        //低电量
//        intentFilter.addAction(Intent.ACTION_BATTERY_LOW);
//        //电量充足
//        intentFilter.addAction(Intent.ACTION_BATTERY_OKAY);

//        BroadcastReceiver batteryChangedReceiver = new BroadcastReceiver(){
//            @Override
//            public void onReceive(Context context,Intent intent){
//                String BatteryAction = intent.getAction();
//                if (BatteryAction.equals(Intent.ACTION_BATTERY_CHANGED)){
//                    String BatteryLevel = BatteryUtils.getBatteryPercentage(intent);
//                    String BatteryStatus = BatteryUtils.getBatteryStatus(MainActivity.this.getResources(), intent);
//                    BetteryMessage = context.getResources().getString(R.string.power_usage_level_and_status,BatteryLevel,BatteryStatus);
//                    //获取开始时的时间
//                    if (intent.getIntExtra("status", BatteryManager.BATTERY_STATUS_UNKNOWN)
//                            == BatteryManager.BATTERY_STATUS_CHARGING
//                            &&volumeFlag == 0){
//                        time1 = ComputeForVolume.FirstTime();
//                        level1 =ComputeForVolume.getLevel(intent.getIntExtra("level",0),intent.getIntExtra("scale",100));
//                        volumeFlag++;
//                        System.out.println(1);
//                        //做一个复杂的判断，当电量充入百分之一时
//                    }else if (intent.getIntExtra("status", BatteryManager.BATTERY_STATUS_UNKNOWN)
//                            == BatteryManager.BATTERY_STATUS_CHARGING
//                            &&volumeFlag == 1
//                            &&ComputeForVolume.getLevel(intent.getIntExtra("level",0),intent.getIntExtra("scale",100))
//                            ==(level1+1)){
//                        if (ComputeForVolume.ComputeVolume(time1,intent)>volume&&ComputeForVolume.ComputeVolume(time1,intent)<5000){
//                            volume = ComputeForVolume.ComputeVolume(time1,intent);
//                        }
//                        System.out.println(2);
//                        volumeFlag=0;
//                    }
//                    hello.setText(String.valueOf(volume));
//                }
//            }
//        };
//        registerReceiver(batteryChangedReceiver,intentFilter);
        super.onCreate(savedInstanceState);
    }


    @Override
    public void initData() {
        mainActivity = this;
        fragmentManager = getFragmentManager();
    }

    @Override
    public void initViews() {

        //init simple views
        ButterKnife.inject(this);

        actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(false);

        myViewPagerAdapter = new MyViewPagerAdapter(getSupportFragmentManager(),
                getApplicationContext());
        mViewPager = (ViewPager) findViewById(R.id.viewPager);
        mViewPager.setAdapter(myViewPagerAdapter);


    }

    @Override
    public void initListeners() {
        mTabListener = new ActionBar.TabListener() {
            @Override
            public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {

                mViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {

            }

            @Override
            public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {

            }
        };

        mBatteryStatusTab = actionBar.newTab().setText("info").setTabListener(mTabListener);
        actionBar.addTab(mBatteryStatusTab);

        final ActionBar.Tab appearTab = actionBar.newTab().setText(R.string.find_tab_name).setTabListener(mTabListener);
        actionBar.addTab(appearTab);

        ActionBar.Tab specialtyTab = actionBar.newTab().setText(R.string.specialty_tab_name).setTabListener(mTabListener);
        actionBar.addTab(specialtyTab);


        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                actionBar.setSelectedNavigationItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }



    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }


}
