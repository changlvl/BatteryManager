package com.morega.batterymanager.UI.Fragment;

import android.os.Bundle;

/**
 * Created by Morega03 on 2015/5/24.
 */
public abstract class BaseFragment extends android.support.v4.app.Fragment{


    @Override
    public void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setRetainInstance(true);
    }

}
