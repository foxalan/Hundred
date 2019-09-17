package com.example.adanvace.article.activity.lifecycle;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.adanvace.R;
import com.example.adanvace.activity.view.BaseActivity;
import com.example.adanvace.util.L;

/**
 * @author alan
 * function:
 */
public class LifeCycleNormalActivity extends BaseActivity {

    private static final String TAG = "LifeCycleNormalActivity";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        L.e(TAG, "onCreate");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        L.e(TAG, "onRestart");
    }

    @Override
    protected void onStart() {
        super.onStart();
        L.e(TAG, "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        L.e(TAG, "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        L.e(TAG, "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        L.e(TAG, "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        L.e(TAG, "onDestroy");
    }

    @Override
    public int getContentView() {
        return R.layout.activity_article_life_cycle_second;
    }

    @Override
    public void initView() {

    }

}
