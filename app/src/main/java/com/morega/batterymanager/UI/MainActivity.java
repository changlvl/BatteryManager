package com.morega.batterymanager.UI;

import android.app.ActionBar;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.widget.Toast;

import com.morega.batterymanager.R;
import com.morega.batterymanager.Adapter.MyViewPagerAdapter;
import com.umeng.analytics.AnalyticsConfig;
import com.umeng.analytics.MobclickAgent;

import butterknife.ButterKnife;


public class MainActivity extends BaseActivity {
//    private TextView hello;
//    private String BetteryMessage;
//    //一个标识符，判断第几个时间
//    private int volumeFlag = 0;
//    //一个中间变量
//    private int time1 = 0;
//    //电池容量
//    private double volume = 1500;
//    private int level1 = 0;

    private long exitTime = 0;
    private MyViewPagerAdapter myViewPagerAdapter;
    public static MainActivity mainActivity;
    FragmentManager fragmentManager;
    private ActionBar actionBar;
    private ViewPager mViewPager;
    ActionBar.TabListener mTabListener;
    private ActionBar.Tab mBatteryStatusTab;


//    public void onArticleSelected(){
//        RankingListFragment fragment = new RankingListFragment();
//
//        android.support.v4.app.FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//
//        transaction.replace(R.id.id_content,fragment,"rankingList");
//        transaction.addToBackStack(null);
//        transaction.commit();
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);

        super.onCreate(savedInstanceState);
//        UmengUpdateAgent.setUpdateOnlyWifi(false);
//        UmengUpdateAgent.update(this);
        MobclickAgent.updateOnlineConfig(MainActivity.this);
        AnalyticsConfig.enableEncrypt(false);
        myViewPagerAdapter = new MyViewPagerAdapter(getSupportFragmentManager(),
                getApplicationContext());
        mViewPager = (ViewPager) findViewById(R.id.viewPager);
        mViewPager.setAdapter(myViewPagerAdapter);

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
        actionBar.setDisplayOptions(0,ActionBar.DISPLAY_SHOW_HOME|ActionBar.DISPLAY_SHOW_TITLE);
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
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);//统计时长
        MobclickAgent.onPageStart("SplashScreen");//统计页面(仅有Activity的应用中SDK自动调用，不需要单独写)
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //this.finish();
        MobclickAgent.onPageEnd("SplashScreen");// （仅有Activity的应用中SDK自动调用，不需要单独写）保证 onPageEnd 在onPause
        // 之前调用,因为 onPause 中会保存信息
        MobclickAgent.onPause(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        //this.finish();
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN){
            if((System.currentTimeMillis()-exitTime) > 2000){
                Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}