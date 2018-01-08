package com.example.alan.hundred.application;

import android.app.Application;
import android.view.LayoutInflater;

import com.example.alan.hundred.view.MyToast;

/**
 * Function :
 * Author : Alan
 * Modify Date : 25/9/17
 * Issue : TODO
 * Whether solve :
 */

public class MyApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        MyToast.init(this, LayoutInflater.from(this));
    }
}
