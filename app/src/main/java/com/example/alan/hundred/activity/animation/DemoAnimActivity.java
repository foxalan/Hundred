package com.example.alan.hundred.activity.animation;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.drawable.AnimationDrawable;
import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

import com.example.alan.hundred.R;
import com.example.alan.hundred.activity.BaseHomeActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Function :
 * Author : Alan
 * Modify Date : 12/10/17
 * Issue : TODO
 * Whether solve :
 */

public class DemoAnimActivity extends BaseHomeActivity {

    private int[] iv_ids = {R.id.iv_expression_1, R.id.iv_expression_2, R.id.iv_expression_3, R.id.iv_expression_4};
    private List<ImageView> imageViewList = new ArrayList<>();
    private boolean isBack = true;

    private ImageView iv_anim;
    private AnimationDrawable anim;
    private boolean isStop = true;

    private static final String TAG = "tang";


    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_expression_1:
                Log.d(TAG, "1--------------------------------------------");
                break;
            case R.id.iv_expression_2:
                Log.d(TAG, "2--------------------------------------------");
                break;
            case R.id.iv_expression_3:
                Log.d(TAG, "3--------------------------------------------");
                break;
            case R.id.iv_expression_4:
                Log.d(TAG, "4--------------------------------------------");
                break;
            case R.id.iv_expression_5:
                setAnimator();
                break;
        }
    }

    private void setAnimator() {
        if (isBack) {
            startAnimator();
            isBack = false;
        } else {
            startBackAnimator();
            isBack = true;
        }
    }

    private void startBackAnimator() {
        for (int j = 0; j < iv_ids.length; j++) {
            float x = (float) (Math.cos(j * (Math.PI / 2) / 3) * 300);
            float y = (float) (Math.sin(j * (Math.PI / 2) / 3) * 300);

            ObjectAnimator animator1 = ObjectAnimator.ofFloat(imageViewList.get(j), "translationY", -y, 0F);
            ObjectAnimator animator2 = ObjectAnimator.ofFloat(imageViewList.get(j), "translationX", x, 0F);
            ObjectAnimator animator3 = ObjectAnimator.ofFloat(
                    imageViewList.get(j), "rotation", 0f, 360f);
            AnimatorSet set = new AnimatorSet();
            set.playTogether(animator1, animator2, animator3);
            set.setDuration(200);
            set.setInterpolator(new LinearInterpolator());
            // set.setStartDelay(500 + 500 * j);
            set.start();
        }
    }

    private void startAnimator() {

        for (int j = 0; j < iv_ids.length; j++) {
            float x = (float) (Math.cos(j * (Math.PI / 2) / 3) * 300);
            float y = (float) (Math.sin(j * (Math.PI / 2) / 3) * 300);

            ObjectAnimator animator1 = ObjectAnimator.ofFloat(imageViewList.get(j), "translationY", 0F, -y);
            ObjectAnimator animator2 = ObjectAnimator.ofFloat(imageViewList.get(j), "translationX", 0F, x);
            ObjectAnimator animator3 = ObjectAnimator.ofFloat(
                    imageViewList.get(j), "rotation", 0f, 360f);
            AnimatorSet set = new AnimatorSet();
            set.playTogether(animator1, animator2, animator3);
            set.setDuration(200);
            set.setInterpolator(new LinearInterpolator());
            //  set.setStartDelay(500 + 500 * j);
            set.start();
        }
    }



    @Override
    public int getContentView() {
        return R.layout.activity_animation_demo;
    }

    @Override
    public void initViews() {

        for (int i = 0; i < iv_ids.length; i++) {
            ImageView iv = (ImageView) findViewById(iv_ids[i]);
            imageViewList.add(iv);
        }


    }

    @Override
    public void initData() {

    }

    @Override
    public void initEvents() {

    }
}
