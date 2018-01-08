package com.example.alan.hundred;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.example.alan.hundred.base.RxBaseActivity;

import butterknife.BindView;

/**
 * @author Alan
 */

public  class MainActivity extends RxBaseActivity implements NavigationView.OnNavigationItemSelectedListener {


    @BindView(R.id.fl_container)
    FrameLayout fl_container;

    @BindView(R.id.navigation_view)
    NavigationView navigationView;


    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }



    @Override
    public void initToolBar() {

    }

    @Override
    public void initEvent() {
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.item_sigle_1:
        }
        return false;
    }
}
