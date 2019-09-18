package com.example.adanvace.article.activity.view.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;

import com.example.adanvace.util.L;

/**
 * @author alan
 * function:
 */
@SuppressLint("ViewConstructor")
public class CustomViewGroup extends LinearLayout {


    public CustomViewGroup(Context context) {
        super(context);
    }

    public CustomViewGroup(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomViewGroup(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     *
     * @param ev
     * @return
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        L.distribution("view group dispatchKeyEvent");
        return super.dispatchTouchEvent(ev);
    }


    /**
     *
     * @param ev
     * @return  true 拦截这次事件
     */
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        L.distribution("view group onInterceptTouchEvent");
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        L.distribution("view group on touch event");
        return super.onTouchEvent(event);
    }

}
