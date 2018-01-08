package com.example.alan.hundred.activity;

import android.graphics.ImageFormat;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.Window;

/**
 * Function :
 * Author : Alan
 * Modify Date : 23/9/17
 * Issue : TODO
 * Whether solve :
 */

public abstract class BaseHomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(getContentView());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initViews();
        initData();
        initEvents();
    }

    public abstract int getContentView();

    public abstract void initViews();

    public abstract void initData();

    public abstract void initEvents();

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home){
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
