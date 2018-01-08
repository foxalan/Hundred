package com.example.alan.hundred.activity.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ProgressBar;

import com.example.alan.hundred.R;
import com.example.alan.hundred.activity.BaseHomeActivity;
import com.example.alan.hundred.util.L;

/**
 * Function :
 * Author : Alan
 * Modify Date : 28/9/17
 * Issue : TODO
 * Whether solve :
 */

public class ProgressActivity extends BaseHomeActivity {

    private ProgressBar pb_list;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public int getContentView() {
        return R.layout.activity_view_progress;
    }

    @Override
    public void initViews() {
        pb_list = (ProgressBar) findViewById(R.id.pb_list);

    }

    @Override
    public void initData() {

    }

    @Override
    public void initEvents() {
        pb_list.setProgress(50);
        L.d(pb_list.getProgress()+"===========");
    }
}
