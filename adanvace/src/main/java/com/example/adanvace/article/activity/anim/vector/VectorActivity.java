package com.example.adanvace.article.activity.anim.vector;


import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;

import android.view.View;
import android.widget.ImageView;


import com.example.adanvace.R;
import com.example.adanvace.activity.view.BaseActivity;

/**
 * @author alan
 * function:
 */
public class VectorActivity extends BaseActivity {

    @Override
    public int getContentView() {
        return R.layout.activity_article_anim_vector;
    }

    @Override
    public void initView() {

    }

    public void anim(View view) {
        ImageView imageView = (ImageView) view;
        Drawable drawable = imageView.getDrawable();
        if (drawable instanceof Animatable) {
            ((Animatable) drawable).start();
        }
    }

}
