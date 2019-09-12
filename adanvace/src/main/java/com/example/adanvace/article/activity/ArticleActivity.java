package com.example.adanvace.article.activity;

import android.content.Intent;
import android.view.View;

import com.example.adanvace.R;
import com.example.adanvace.activity.view.BaseActivity;
import com.example.adanvace.article.activity.lifecycle.LifeCycleActivity;

/**
 * @author alan
 * function:
 */
public class ArticleActivity extends BaseActivity implements View.OnClickListener {


    @Override
    public int getContentView() {
        return R.layout.activity_article;
    }

    @Override
    public void initView() {
        findViewById(R.id.bt_life).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.bt_life:
                intent = new Intent(ArticleActivity.this, LifeCycleActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
