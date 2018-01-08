package com.example.alan.hundred.fragment;

import android.annotation.SuppressLint;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;



public abstract class BaseFragment extends Fragment {

    public static final int LIST_FRAGMENT_MESSAGE = 0;
    public static final int LIST_FRAGMENT_CONTACT = 1;
    public static final int LIST_FRAGMENT_STATE = 2;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @SuppressLint("CommitTransaction")
    public FragmentManager getTransaction() {

        return getFragmentManager();
    }
}
