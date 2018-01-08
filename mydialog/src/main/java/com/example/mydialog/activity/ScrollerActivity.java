package com.example.mydialog.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.example.mydialog.R;

/**
 * Function :滑动
 * Author : Alan
 * Modify Date : 19/10/17
 * Issue : TODO
 * Whether solve :
 */

public class ScrollerActivity extends Activity {
    private View vt_view;

    private Button bt_scroller;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vertical);

        initViews();
        initEvent();
    }


    private void initViews() {

        vt_view = findViewById(R.id.vt_view);
        bt_scroller = (Button) findViewById(R.id.bt_scroller);

    }


    private void initEvent() {


        vt_view.scrollBy(100, 100);

        bt_scroller.setEnabled(false);

        bt_scroller.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            //    bt_scroller.scrollTo(200,200);
                bt_scroller.scrollBy(20,20);
            }
        });
    }
}
