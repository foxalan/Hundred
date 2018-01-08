package com.example.alan.hundred.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;

import com.example.alan.hundred.R;

/**
 * Function :
 * Author : Alan
 * Modify Date : 21/9/17
 * Issue : TODO
 * Whether solve :
 */

public class AnimationFragment extends BaseFragment {

    private static AnimationFragment animationFragment;

    public static AnimationFragment getInstance(){
        if (animationFragment == null){
            animationFragment = new AnimationFragment();
        }

        return animationFragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.activity_download,container,false);

        return rootView;
    }
}
