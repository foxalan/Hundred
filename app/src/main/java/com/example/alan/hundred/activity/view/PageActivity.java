package com.example.alan.hundred.activity.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.example.alan.hundred.R;
import com.example.alan.hundred.activity.BaseHomeActivity;
import com.example.alan.hundred.fragment.page.PageFlipperFragment;
import com.example.alan.hundred.fragment.page.PageSpinnerFragment;
import com.example.alan.hundred.fragment.page.PageStackFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Function :
 * @Author : Alan
 * Modify Date : 27/9/17
 * Issue : TODO
 * Whether solve :
 */

public class PageActivity extends BaseHomeActivity {

    private TabLayout tl_page;
    private ViewPager vp_page;

    private String[] titles;

    private PageStackFragment pageStackFragment;
    private PageFlipperFragment pageFlipperFragment;
    private PageSpinnerFragment pageSpinnerFragment;

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
        return R.layout.activity_view_page;
    }

    @Override
    public void initViews() {
        tl_page = (TabLayout) findViewById(R.id.tl_page);
        vp_page = (ViewPager) findViewById(R.id.vp_page);

        pageFlipperFragment = PageFlipperFragment.getInstance();
        pageSpinnerFragment = PageSpinnerFragment.getInstance();
        pageStackFragment = PageStackFragment.getInstance();

    }

    @Override
    public void initData() {

        titles = getResources().getStringArray(R.array.pages);

        fragmentList.add(pageFlipperFragment);
        fragmentList.add(pageSpinnerFragment);
        fragmentList.add(pageStackFragment);

    }

    @Override
    public void initEvents() {

        vp_page.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
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

        tl_page.setupWithViewPager(vp_page);

    }
}
