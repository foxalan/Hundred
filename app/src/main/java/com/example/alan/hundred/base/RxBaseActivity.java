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
    public abstract int getLayoutId();

    /**
     * 初始化ToolBar
     */
    public abstract void initToolBar();

    /**
     *
     * @param savedInstanceState
     */

    /**
     * 初始化事件
     */
    public abstract void initEvent();

    private Unbinder unbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initWindow();
        setContentView(getLayoutId());
        initToolBar();
        unbinder = ButterKnife.bind(this);
        initView();
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
