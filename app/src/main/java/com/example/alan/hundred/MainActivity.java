package com.example.alan.hundred;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.AppCompatTextView;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import com.example.alan.hundred.base.RxBaseActivity;
import com.example.alan.hundred.fragment.HomeFragment;
import com.example.alan.hundred.fragment.SettingFragment;

import java.util.ArrayList;
import java.util.List;

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

    private HomeFragment mHomeFragment;
    private SettingFragment mSettingFragment;
    private int index = 0;
    private int currentTabIndex = 0;
    private List<Fragment> fragmentList;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        super.initView();
        initNavigationView();
        initFragment();
    }

    private void initNavigationView() {
        View viewHeader = navigationView.getHeaderView(0);
        AppCompatTextView userNmae = viewHeader.findViewById(R.id.tv_user_name);
        AppCompatTextView userLevel = viewHeader.findViewById(R.id.tv_user_level);
    }

    private void initFragment() {
        fragmentList = new ArrayList<>();
        mHomeFragment = HomeFragment.getInstance();
        mSettingFragment = SettingFragment.getInstance();
        fragmentList.add(mHomeFragment);
        fragmentList.add(mSettingFragment);
    }


    @Override
    public void initToolBar() {
    }

    @Override
    public void initEvent() {
        navigationView.setNavigationItemSelectedListener(this);
        // 添加显示第一个fragment
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fl_container, mHomeFragment)
                .show(mHomeFragment).commit();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        mDrawerLayout.closeDrawer(GravityCompat.START);
        switch (item.getItemId()) {
            case R.id.single_1:
                changeFragmentIndex(item, 0);
                return true;
            case R.id.item_settings:
                changeFragmentIndex(item,1);
                return true;
            default:
                break;
        }
        return false;
    }

    /**
     * Fragment切换
     */
    private void switchFragment() {
        FragmentTransaction trx = getSupportFragmentManager().beginTransaction();
        trx.hide(fragmentList.get(currentTabIndex));
        if (!fragmentList.get(index).isAdded()) {
            trx.add(R.id.fl_container, fragmentList.get(index));
        }
        trx.show(fragmentList.get(index)).commit();
        currentTabIndex = index;
    }


    /**
     * 切换Fragment的下标
     */
    private void changeFragmentIndex(MenuItem item, int currentIndex) {
        index = currentIndex;
        switchFragment();
        item.setChecked(true);
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

    /**
     * DrawerLayout侧滑菜单开关
     */
    public void toggleDrawer() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            mDrawerLayout.openDrawer(GravityCompat.START);
        }
    }
}
