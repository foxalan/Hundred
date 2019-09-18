package com.example.adanvace.article.activity.view;

import android.view.KeyEvent;
import android.view.MotionEvent;

import com.example.adanvace.R;
import com.example.adanvace.activity.view.BaseActivity;
import com.example.adanvace.util.L;

/**
 * @author alan
 * function: 事件分发试
 */
public class ViewDistributionActivity extends BaseActivity {

    @Override
    public int getContentView() {
        return R.layout.activity_article_view_distribution;
    }

    @Override
    public void initView() {

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        L.distribution("activity group dispatchKeyEvent");
        return super.dispatchTouchEvent(ev);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        L.distribution("activity on touch event");
        return super.onTouchEvent(event);
    }
}
