package com.example.morega03.batterymanager.UI.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.morega03.batterymanager.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Morega03 on 2015/5/24.
 */
public class BatteryStatusFragment extends BaseFragment{
    /**
     * 构造电池状态的Fragment
     */

    @InjectView(R.id.remain_usable_time) TextView remainUsableTime;
    @InjectView(R.id.phone_type) TextView phoneType;
    @InjectView(R.id.connect_status) TextView connectStatus;
    @InjectView(R.id.battey_status) TextView batteryStatus;
    @InjectView(R.id.temperature_status) TextView temperatureStatus;
    @InjectView(R.id.battery_voltage) TextView batteryVoltage;
    @InjectView(R.id.battery_technology) TextView batteryTechnology;

    //构造方法，并不会用到它
    public BatteryStatusFragment(){

    }
    //单例，我还没有掌握
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



        return view;
    }

    public void updataBatteryDatas(){

    }
}
