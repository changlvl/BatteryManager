package com.example.morega03.batterymanager.UI.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.morega03.batterymanager.R;

import butterknife.ButterKnife;

/**
 * Created by Morega03 on 2015/5/24.
 */
public class SpecialtyFragment extends BaseFragment{

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



        return view;
    }
}
