package com.example.morega03.batterymanager.Adapter;

import android.support.v4.app.FragmentManager;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.morega03.batterymanager.UI.Fragment.BatteryStatusFragment;
import com.example.morega03.batterymanager.UI.Fragment.FindFragment;
import com.example.morega03.batterymanager.UI.Fragment.SpecialtyFragment;

/**
 * Created by Morega03 on 2015/5/24.
 */
public class MyViewPagerAdapter extends FragmentPagerAdapter{
    Context context;

    public MyViewPagerAdapter(FragmentManager fm, Context context){
        super(fm);
        this.context = context;
    }

    @Override
    public int getCount(){
        return 3;
    }

    @Override
    public Fragment getItem(int position){
        switch (position){
            case 0:
                return BatteryStatusFragment.getInstance();
            case 1:
                return FindFragment.getInstance();
            case 2:
                return SpecialtyFragment.getInstance();
        }

        return null;
    }
}
