package com.example.adanvace.activity.view;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;

/**
 * @author alan
 * Function Activity
 *  1.状态栏
 *  2.
 */
public abstract class BaseActivity extends AppCompatActivity {

    private int defaultStatusBarColor = Color.WHITE;
    private int defaultStatusBarTextColor = Color.WHITE;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        setStatusBarColor(getStatusBarColor(),getStatusBarTextColor());
        setContentView(getContentView());
        requestPermission();
        initView();
        initData();
        initEvent();
    }

    /**
     * 请求权限
     */
    protected void requestPermission(){

    }

    /**
     * 布局
     * @return int
     */
    public abstract @LayoutRes int getContentView();

    public abstract void initView();

    public abstract void initData();

    public abstract void initEvent();

    protected void setStatusBarColor(int statusBarColor, int textColor) {
        //判断版本
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            // 设置状态栏底色颜色
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().setStatusBarColor(statusBarColor);

            // 如果亮色，设置状态栏文字为黑色
            if (isLightColor(textColor)) {
                getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            } else {
                getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);
            }
        }
    }

    /**
     * 是否是亮色
     * @param textColor
     * @return
     */
    private boolean isLightColor(int textColor) {
        return true;
    }

    protected  int getStatusBarColor(){
        return defaultStatusBarColor;
    }

    protected int getStatusBarTextColor(){
        return defaultStatusBarTextColor;
    }
}
