package com.morega.batterymanager.UI;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

/**
 * Created by Morega03 on 2015/5/24.
 */
public abstract class BaseActivity extends FragmentActivity{
    @Override
    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);

        initViews();
        initData();
        initListeners();
    }
//    初始化界面
    protected abstract void initViews();

//    初始化数据
    protected abstract void initData();

//    初始化监听器
    protected abstract void initListeners();
}
