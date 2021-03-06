package com.example.adanvace.activity.view;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.example.adanvace.R;
import com.example.adanvace.viewgroup.VerticalLinearLayout;

/**
 * Function Name : TODO
 * Author : Alan
 * Modify Date : 2/8/17$
 * Input Parameter &
 */

public class VerticalActivity extends LatteActivity{

    private VerticalLinearLayout verticalLinearLayout;

    @Override
    public int getContentView() {
        return R.layout.activity_verterical;
    }

    @Override
    public void initView() {
        verticalLinearLayout = (VerticalLinearLayout) findViewById(R.id.vl_layout);

        verticalLinearLayout.setOnPageChangeListener(new VerticalLinearLayout.OnPageChangeListener() {
            @Override
            public void onPageChange(int currentPage) {
                Toast.makeText(VerticalActivity.this, "第"+(currentPage+1)+"页", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void initData() {

    }

    @Override
    public void initEvent() {

    }
}
