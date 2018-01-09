package com.example.alan.hundred.activity.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.example.alan.hundred.R;
import com.example.alan.hundred.activity.BaseHomeActivity;
import com.example.alan.hundred.fragment.login.BiLoginFragment;
import com.example.alan.hundred.fragment.login.LoginOutsideFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Function :
 * @Author : Alan
 * Modify Date : 23/9/17
 * Issue : TODO
 * Whether solve :
 */

public class EditActivity extends BaseHomeActivity {

    private TabLayout mTableLayout;
    private ViewPager mViewPager;

    private String[] titles;
    private List<Fragment> fragmentList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //去掉阴影
        if (getSupportActionBar() != null) {
            getSupportActionBar().setElevation(0);
        }
    }

    @Override
    public int getContentView() {
        return R.layout.activity_view_edittext;
    }

    @Override
    public void initViews() {
        mTableLayout = (TabLayout) findViewById(R.id.tl_edit);
        mViewPager = (ViewPager) findViewById(R.id.vp_edit);
    }

    @Override
    public void initData() {
        titles = getResources().getStringArray(R.array.edit);

        LoginOutsideFragment loginOutsideFragment = LoginOutsideFragment.getInstance();
        BiLoginFragment biLoginFragment = BiLoginFragment.getInstance();

        fragmentList.add(loginOutsideFragment);
        fragmentList.add(biLoginFragment);
    }

    @Override
    public void initEvents() {

        mViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragmentList.get(position);
            }

            @Override
            public int getCount() {
                return fragmentList.size();
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return titles[position];
            }
        });

        mTableLayout.setupWithViewPager(mViewPager);
    }
}
