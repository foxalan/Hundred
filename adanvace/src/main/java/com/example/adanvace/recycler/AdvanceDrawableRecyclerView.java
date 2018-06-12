package com.example.adanvace.recycler;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.example.adanvace.recycler.wrapper.RefreshWrapper;

/**
 * @author alan
 *         Date  2018/6/12.
 *         Function :
 *         Issue :
 */

public class AdvanceDrawableRecyclerView extends RecyclerView {

    private Context context;
    private View refreshView;

    public AdvanceDrawableRecyclerView(Context context) {
        this(context,null);
    }

    public AdvanceDrawableRecyclerView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public AdvanceDrawableRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.context = context;
        init(context);

    }

    /**
     * 初始化View
     * @param context
     */
    private void init(Context context) {

    }

    @Override
    public void setAdapter(Adapter adapter) {
        super.setAdapter(adapter);
        if (adapter instanceof RefreshWrapper){
            ((RefreshWrapper) adapter).setRefreshView(context,refreshView);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {

        return super.onTouchEvent(e);
    }
}
