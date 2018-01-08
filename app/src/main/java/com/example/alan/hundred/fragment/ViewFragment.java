package com.example.alan.hundred.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.alan.hundred.R;

/**
 * Function :
 * Author : Alan
 * Modify Date : 21/9/17
 * Issue : TODO
 * Whether solve :
 */

public class ViewFragment extends BaseFragment {

    private static ViewFragment viewFragment;

    public static ViewFragment getInstance(){
        if (viewFragment == null){
            viewFragment= new ViewFragment();
        }

        return viewFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.activity_home,container,false);

        return rootView;
    }
}
