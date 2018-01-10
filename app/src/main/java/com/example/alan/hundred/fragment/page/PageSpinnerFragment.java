package com.example.alan.hundred.fragment.page;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.alan.hundred.R;
import com.example.alan.hundred.fragment.BaseSupportFragment;

/**
 * Function :
 * @Author : Alan
 * Modify Date : 27/9/17
 * Issue : TODO
 * Whether solve :
 */

public class PageSpinnerFragment extends BaseSupportFragment {


    private  static PageSpinnerFragment spinnerFragment;

    public static PageSpinnerFragment getInstance(){
        if (spinnerFragment == null){
            spinnerFragment = new PageSpinnerFragment();
        }

        return spinnerFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_page_spinner,container,false);

        return rootView;
    }
}
