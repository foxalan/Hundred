package com.example.alan.hundred.activity.animation;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.example.alan.hundred.R;
import com.example.alan.hundred.activity.BaseHomeActivity;

/**
 * Function :
 * Author : Alan
 * Modify Date : 11/10/17
 * Issue : TODO
 * Whether solve :
 */

public class AnimActivity extends BaseHomeActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public int getContentView() {
        return R.layout.activity_animation_base;
    }

    @Override
    public void initViews() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void initEvents() {

    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_anim_simple:
                startActivity(new Intent(AnimActivity.this, AnimationActivity.class));
                break;
            case R.id.bt_anim_bezier:
                startActivity(new Intent(AnimActivity.this, BezierAnimActivity.class));
                break;
            case R.id.bt_anim_vector:
                startActivity(new Intent(AnimActivity.this, VectorActivity.class));
                break;
            case R.id.bt_anim_demo:
                startActivity(new Intent(AnimActivity.this, DemoAnimActivity.class));
                break;
            default:
                break;


        }
    }
}
