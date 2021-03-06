package com.morega.batterymanager.Adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.morega.batterymanager.UI.Fragment.BatteryStatusFragment;
import com.morega.batterymanager.UI.Fragment.FindFragment;
import com.morega.batterymanager.UI.Fragment.SpecialtyFragment;

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
                return new BatteryStatusFragment();
            case 1:
                return new FindFragment();
            case 2:
                return new SpecialtyFragment();
        }

        return null;
    }
}
