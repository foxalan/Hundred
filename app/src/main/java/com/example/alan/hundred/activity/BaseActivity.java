package com.example.alan.hundred.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Function :
 * Author : Alan
 * Modify Date : 13/9/17
 * Issue : TODO
 * Whether solve :
 */

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentView());

        initViews();
        initData();
        initEvents();
    }

    public abstract int getContentView();

    public abstract void initViews();

    public abstract void initData();

    public abstract void initEvents();
}
