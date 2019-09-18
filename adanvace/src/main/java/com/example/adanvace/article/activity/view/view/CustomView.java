package com.example.adanvace.article.activity.view.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;

import com.example.adanvace.util.L;

/**
 * @author alan
 * function:
 */
public class CustomView extends View {

    public CustomView(Context context) {
        super(context);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        L.distribution("view dispatchKeyEvent");
        return super.dispatchTouchEvent(event);
    }

    /**
     *
     * @param event
     * @return true 消费这次事件
     * false 将事件往上传
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        L.distribution("view onToucheEv");
        return false;
    }
}
