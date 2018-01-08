package com.example.alan.hundred;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import com.example.alan.hundred.base.RxBaseActivity;
import com.example.alan.hundred.fragment.HomeFragment;

import butterknife.BindView;

/**
 * @author Alan
 */

public class MainActivity extends RxBaseActivity implements NavigationView.OnNavigationItemSelectedListener {


    @BindView(R.id.fl_container)
    FrameLayout fl_container;
    @BindView(R.id.navigation_view)
    NavigationView navigationView;
    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private ActionBarDrawerToggle mDrawerToggle;
    private HomeFragment homeFragment;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        super.initView();
        initDrawerToggleView();
        initNavigationView();
        initFragment();
    }


    private void initDrawerToggleView() {
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,toolbar, R.string.open, R.string.close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);

            }
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);

            }
        };
        mDrawerToggle.syncState();
        mDrawerLayout.addDrawerListener(mDrawerToggle);
    }

    private void initNavigationView() {
        View viewHeader = navigationView.getHeaderView(0);
        AppCompatTextView userNmae = viewHeader.findViewById(R.id.tv_user_name);
        AppCompatTextView userLevel = viewHeader.findViewById(R.id.tv_user_level);
    }

    private void initFragment() {
        homeFragment = HomeFragment.getInstance();
    }


    @Override
    public void initToolBar() {
        setSupportActionBar(toolbar);
    }

    @Override
    public void initEvent() {
        navigationView.setNavigationItemSelectedListener(this);
        // 添加显示第一个fragment
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fl_container, homeFragment)
                .show(homeFragment).commit();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.single_1:
                break;
            default:
                break;
        }
        return false;
    }

    /**
     * 监听back键处理DrawerLayout和SearchView
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (mDrawerLayout.isDrawerOpen(mDrawerLayout.getChildAt(1))) {
                mDrawerLayout.closeDrawers();
            } else {

            }
        }
        return true;
    }
}
