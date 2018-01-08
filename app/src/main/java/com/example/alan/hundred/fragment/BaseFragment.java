package com.example.alan.hundred.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;


public abstract class BaseFragment extends Fragment {

    public static final int LIST_FRAGMENT_MESSAGE = 0;
    public static final int LIST_FRAGMENT_CONTACT = 1;
    public static final int LIST_FRAGMENT_STATE = 2;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }


}
