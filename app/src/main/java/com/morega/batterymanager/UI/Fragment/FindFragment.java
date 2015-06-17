package com.morega.batterymanager.UI.Fragment;

import com.morega.batterymanager.R;
import com.umeng.analytics.MobclickAgent;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * Created by Morega03 on 2015/5/24.
 */
public class FindFragment extends BaseFragment {

    private static class FragmentHolder{
        private static final FindFragment INSTANCE = new FindFragment();

    }

    public static FindFragment getInstance(){
        return FragmentHolder.INSTANCE;
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
        View view = inflater.inflate(R.layout.fragment_find,container,false);

        ButterKnife.inject(this, view);



        return view;
    }
    @Override
    public void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd("MainScreen");
    }
}
