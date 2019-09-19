package com.example.adanvace.article.activity.anim;

import android.content.Intent;
import android.view.View;

import com.example.adanvace.R;
import com.example.adanvace.activity.view.BaseActivity;
import com.example.adanvace.article.activity.anim.bezier.BezierActivity;
import com.example.adanvace.article.activity.anim.vector.VectorActivity;

/**
 * @author alan
 * function:
 */
public class AnimActivity extends BaseActivity {


    @Override
    public int getContentView() {
        return R.layout.activity_article_anim;
    }

    @Override
    public void initView() {

    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_anim_vector:
                startActivity(new Intent(AnimActivity.this, VectorActivity.class));
                break;
            case R.id.bt_anim_bezier:
                startActivity(new Intent(AnimActivity.this, BezierActivity.class));
                break;
            default:
                break;
        }
    }
}
