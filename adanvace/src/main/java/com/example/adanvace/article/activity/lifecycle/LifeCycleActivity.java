package com.example.adanvace.article.activity.lifecycle;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.example.adanvace.R;
import com.example.adanvace.activity.view.BaseActivity;
import com.example.adanvace.util.L;

/**
 * @author alan
 * function: Activity 的正常启动
 *
 * 1.  onCreate onDestory 创建与销毁
 *     onStart onStop 可见
 *     onResume onPause 可操作
 *2. 一个Activity启动另外一个Activity时 ，先执行第一个Activity的onPause 在执行第二个Activity的onResume
 *3.onSaveInstanceState来保存当前Activity的状态，这个方法调用的时机是在onStop之前
 *   onRestoreInstanceState的调用时机应该在onStart之后
 */
public class LifeCycleActivity extends BaseActivity {

    private static final String TAG = "LifeCycleActivity";

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
        return R.layout.activity_article_life_cycle;
    }

    public void normalSkip(View view){
        Intent intent = new Intent(LifeCycleActivity.this,LifeCycleNormalActivity.class);
        startActivity(intent);
    }

    /**
     * 保存界面状态
     * @param view
     */
    public void onSaveInstance(View view){
        if (getRequestedOrientation() != ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }else {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        L.e(TAG, "onSaveInstanceState");
        outState.putString("test","alan");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        L.e(TAG, "onRestoreInstanceState"+"========="+savedInstanceState.getString("test"));
    }

    public void launchMode(View view){

    }

    @Override
    public void initView() {

    }

}
