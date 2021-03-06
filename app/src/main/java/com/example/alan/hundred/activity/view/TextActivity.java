package com.example.alan.hundred.activity.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.widget.TextView;

import com.example.alan.hundred.R;
import com.example.alan.hundred.activity.BaseActivity;

/**
 * Function :
 *
 * @Author : Alan
 * Modify Date : 22/9/17
 * Issue : TODO
 * Whether solve :
 */

public class TextActivity extends BaseActivity {

    private TextView tv_marquee;
    private TextView tv_toast;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public int getContentView() {
        return R.layout.activity_view_textview;
    }

    @Override
    public void initViews() {

        tv_marquee = (TextView) findViewById(R.id.tv_marquee);
        tv_toast = (TextView) findViewById(R.id.tv_toast);
    }

    @Override
    public void initData() {

        tv_toast.setText("1.设置走马灯效果时,要设置textIsSelectable,ellipsize,focusable,singleLine属性");
        tv_toast.setText(tv_toast.getText() + "\n" + "2.字体不能太大");
        tv_toast.setText(tv_toast.getText() + "\n" + "3.设置了MovementMethod才能滑动");
        tv_toast.setText(tv_toast.getText() + "\n" + "4.走马灯效果有时会停");

        tv_toast.setMovementMethod(ScrollingMovementMethod.getInstance());
        //   tv_toast.scrollTo(0,200);
    }

    @Override
    public void initEvents() {
        tv_marquee.requestFocus();

    }

    private final int FONT_10 = 0x110;
    private final int FONT_11 = 0x111;
    private final int FONT_12 = 0x112;
    private final int FONT_13 = 0x113;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        SubMenu subMenu = menu.addSubMenu("字体");

        subMenu.setHeaderIcon(R.drawable.ic_animation);
        subMenu.setHeaderTitle("字体大小");
        subMenu.add(0, FONT_10, 0, "FONT 20");
        subMenu.add(0, FONT_11, 0, "FONT 25");
        subMenu.add(0, FONT_12, 0, "FONT 30");
        subMenu.add(0, FONT_13, 0, "FONT 35");

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            finish();
        }

        switch (item.getItemId()) {
            case FONT_10:
                tv_toast.setTextSize(20);
                tv_marquee.setTextSize(20);
                break;
            case FONT_11:
                tv_toast.setTextSize(25);
                tv_marquee.setTextSize(25);
                break;
            case FONT_12:
                tv_toast.setTextSize(30);
                tv_marquee.setTextSize(30);
                break;
            case FONT_13:
                tv_toast.setTextSize(35);
                tv_marquee.setTextSize(35);
                break;
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
