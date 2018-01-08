package com.example.mydialog.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.Scroller;



/**
 * Function :用于测试滑动的view
 * Author : Alan
 * Modify Date : 19/10/17
 * Issue : TODO
 * Whether solve :
 */

public class VerticalView extends ViewGroup {

    private int mWidth;
    private int mHeight;

    private Scroller scroller;

    private int startX;
    private int lastX;
    private int touchSlop;

    public VerticalView(Context context) {
        this(context, null);
    }

    public VerticalView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public VerticalView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        scroller = new Scroller(context);

        touchSlop = ViewConfiguration.get(getContext()).getScaledTouchSlop();

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

        int count = getChildCount();

        for (int i = 0; i < count; i++) {
            int width = getChildAt(0).getMeasuredWidth();
            int height = getChildAt(0).getMeasuredHeight();

            getChildAt(i).layout(width * i, 0, width * (i + 1), height);
        }

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);

        int width_mode = MeasureSpec.getMode(widthMeasureSpec);
        int height_mode = MeasureSpec.getMode(heightMeasureSpec);

        if (width_mode == MeasureSpec.EXACTLY) {
            mWidth = width;
        } else {
            mWidth = 540;
        }

        if (height_mode == MeasureSpec.EXACTLY) {
            mHeight = height;
        } else {
            mHeight = 720;
        }

        int count = getChildCount();
        for (int i = 0; i < count; ++i) {
            View childView = getChildAt(i);
            measureChild(childView, widthMeasureSpec, heightMeasureSpec);
        }


        setMeasuredDimension(widthMeasureSpec, heightMeasureSpec);
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {

        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                startX = (int) ev.getRawX();
                break;
            case MotionEvent.ACTION_MOVE:

                lastX = (int) ev.getRawX();

                Log.e("TANG", lastX - startX + "======");
                if (Math.abs(lastX - startX) > touchSlop) {
                    return true;
                }


                break;
        }


        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {

            case MotionEvent.ACTION_MOVE:
                if (getScrollX() < 0) {
                    scrollTo(0, 0);
                }
                scrollBy(startX - lastX, 0);

                break;
            case MotionEvent.ACTION_UP:
                int position = (int) (getScrollX()*1.5/mWidth);
                scroller.startScroll(getScrollX(),0,position*mWidth,0);
                invalidate();
                break;
        }

        return super.onTouchEvent(event);
    }


    @Override
    public void computeScroll() {
        super.computeScroll();
        if (scroller.computeScrollOffset()){
            scrollTo(scroller.getCurrX(), scroller.getCurrY());
            invalidate();
        }

    }
}
