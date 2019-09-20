package com.example.adanvace.article.activity.anim.demo;

import android.view.View;

import com.example.adanvace.R;
import com.example.adanvace.activity.view.BaseActivity;

/**
 * @author alan
 * function:
 */
public class AnimDemoActivity extends BaseActivity {

    private HeartLayout heartLayout;

    @Override
    public int getContentView() {
        return R.layout.activity_article_anim_demo;
    }

    @Override
    public void initView() {
        heartLayout = (HeartLayout) findViewById(R.id.hl_heart);

        heartLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                heartLayout.addFavor();
            }
        });
    }



}
