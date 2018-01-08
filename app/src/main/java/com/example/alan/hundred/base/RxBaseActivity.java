package com.example.alan.hundred.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Function :Activity基类
 * Modify Date : 2018/1/8
 *
 * @Author : Alan
 * Issue : TODO
 * Whether Solve :
 */

public abstract class RxBaseActivity extends AppCompatActivity {


    /**
     * 得到当前的Layout
     *
     * @return
     */
    abstract int getLayoutId();

    /**
     * 初始化ToolBar
     */
    abstract void initToolBar();

    /**
     *
     * @param savedInstanceState
     */

    /**
     * 初始化事件
     */
    abstract void initEvent();

    private Unbinder unbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initWindow();
        setContentView(getLayoutId());
        unbinder = ButterKnife.bind(this);
        initView();
        initToolBar();
        initEvent();
    }

    protected void initWindow() {}

    protected void initView() {}

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
