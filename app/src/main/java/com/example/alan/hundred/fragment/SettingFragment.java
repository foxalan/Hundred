package com.example.alan.hundred.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.alan.hundred.MainActivity;
import com.example.alan.hundred.R;
import com.example.alan.hundred.base.RxBaseFragment;

import butterknife.BindView;

/**
 * Function :
 * Modify Date : 2018/1/9
 *
 * @Author : Alan
 * Issue : TODO
 * Whether Solve :
 */

public class SettingFragment extends RxBaseFragment {


    @BindView(R.id.toolbar)
    Toolbar mToolBar;

    private static SettingFragment mSettingFragment;

    public static SettingFragment getInstance() {
        if (mSettingFragment == null) {
            mSettingFragment = new SettingFragment();
        }
        return mSettingFragment;
    }

    @Override
    public void finishCreateView(Bundle state) {
        mToolBar.setTitle("设置");
        mToolBar.setNavigationIcon(R.drawable.ic_navigation_drawer);
        mToolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Activity activity = getBaseActivity();
                if (activity instanceof MainActivity) {
                    ((MainActivity) activity).toggleDrawer();
                }
            }
        });
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_setting;
    }
}
