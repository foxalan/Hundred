package com.example.alan.hundred.activity.animation;

import android.app.Activity;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageView;

import com.example.alan.hundred.R;
import com.example.alan.hundred.activity.BaseHomeActivity;


/**
 * Function Name :矢量动画,受版本影响用的比较少
 * Author : Eddie
 * Modify Date :
 * Input Parameter &
 * 1.在Drawable下建一个VERTOR
 * 2.新建ANIMATOR 使用objectAnimator设置属性
 * 3.在Drawable下建一个联合剂 animated-vector
 * 4.在ImageView 中设置 app:srcCompat ="@drawable/name_logo"
 * 5.在程序中AnimatedVectorDrawable 控制动画效果
 *
 * 在build.gradle 中设置vectorDrawables.useSupportLibrary = true
 */

public class VectorActivity extends BaseHomeActivity{
    private AnimatedVectorDrawable mAnimatedVectorDrawable;
    private ImageView iv_vector_anim;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



       // mAnimatedVectorDrawable = (AnimatedVectorDrawable) iv_vector_anim.getDrawable();
       // mAnimatedVectorDrawable.start();
       //   iv_vector_anim.setImageDrawable(mAnimatedVectorDrawable);

    }

    @Override
    public int getContentView() {
        return R.layout.activity_animator_vector;
    }

    @Override
    public void initViews() {
        iv_vector_anim = (ImageView) findViewById(R.id.iv_vector_anim);
        mAnimatedVectorDrawable = (AnimatedVectorDrawable) ContextCompat.getDrawable(getApplication(),R.drawable.name_logo);

    }

    @Override
    public void initData() {

    }

    @Override
    public void initEvents() {

    }

    public void onClick(View view){
        if (view.getId() == R.id.iv_vector_anim){

            if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP){
                iv_vector_anim.setImageDrawable(mAnimatedVectorDrawable);
                mAnimatedVectorDrawable.start();
            }

        }
    }
}
