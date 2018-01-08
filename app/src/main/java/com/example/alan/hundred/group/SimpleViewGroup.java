package com.example.alan.hundred.group;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * Function :
 * Author : Alan
 * Modify Date : 9/10/17
 * Issue : 1.
 * Whether solve :
 * 1.决定该ViewGroup的LayoutParams
 * 2.在onMeasure中计算childView的测量值以及模式，以及设置自己的宽和高
 * 3.onLayout对其所有childView进行定位（设置childView的绘制区域）
 */

public class SimpleViewGroup extends ViewGroup {

    private int mWidth;
    private int mHeight;


    public SimpleViewGroup(Context context) {
        super(context);
    }

    public SimpleViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SimpleViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int widthSpec = MeasureSpec.getMode(widthMeasureSpec);
        int heightSpec = MeasureSpec.getMode(heightMeasureSpec);

        measureChildren(widthMeasureSpec, heightMeasureSpec);



        MarginLayoutParams layoutParams;

        if (widthSpec == MeasureSpec.EXACTLY) {
            mWidth = MeasureSpec.getSize(widthMeasureSpec);

        } else {
            layoutParams = (MarginLayoutParams) getChildAt(0).getLayoutParams();
            int width_top = getChildAt(0).getMeasuredWidth() +
                    layoutParams.leftMargin + layoutParams.rightMargin +
                    getChildAt(1).getMeasuredWidth();

            int width_bottom = getChildAt(2).getMeasuredWidth() + getChildAt(3).getMeasuredWidth();
            mWidth = Math.max(width_bottom, width_top);
        }


        if (heightSpec == MeasureSpec.EXACTLY) {
            mHeight = MeasureSpec.getSize(heightMeasureSpec);
        } else {
            int height_left = getChildAt(0).getMeasuredHeight() + getChildAt(2).getMeasuredHeight();
            int height_right = getChildAt(1).getMeasuredHeight() + getChildAt(3).getMeasuredHeight();
            mHeight = Math.max(height_left, height_right);
        }

        setMeasuredDimension(mWidth, mHeight);

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

        int cCount = getChildCount();
        int cWidth = 0;
        int cHeight = 0;
        MarginLayoutParams cParams = null;
        /**
         * 遍历所有childView根据其宽和高，以及margin进行布局
         */
        for (int i = 0; i < cCount; i++)
        {
            View childView = getChildAt(i);
            cWidth = childView.getMeasuredWidth();
            cHeight = childView.getMeasuredHeight();
            cParams = (MarginLayoutParams) childView.getLayoutParams();

            int cl = 0, ct = 0, cr = 0, cb = 0;

            switch (i)
            {
                case 0:
                    cl = cParams.leftMargin;
                    ct = cParams.topMargin;
                    break;
                case 1:
                    cl = getWidth() - cWidth - cParams.leftMargin
                            - cParams.rightMargin;
                    ct = cParams.topMargin;

                    break;
                case 2:
                    cl = cParams.leftMargin;
                    ct = getHeight() - cHeight - cParams.bottomMargin;
                    break;
                case 3:
                    cl = getWidth() - cWidth - cParams.leftMargin
                            - cParams.rightMargin;
                    ct = getHeight() - cHeight - cParams.bottomMargin;
                    break;

            }
            cr = cl + cWidth;
            cb = cHeight + ct;
            childView.layout(cl, ct, cr, cb);
        }

    }

    @Override
    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }
}
