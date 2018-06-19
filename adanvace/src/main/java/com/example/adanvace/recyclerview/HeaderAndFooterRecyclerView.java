package com.example.adanvace.recyclerview;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

import com.example.adanvace.recycler.wrapper.HeadAndFooterWrapper;

/**
 * @author alan
 *         Date  2018/6/17.
 *         Function : 重写RecyclerView  中的setAdapter()方法，动态添加Header And Footer
 *         Issue :
 */

public class HeaderAndFooterRecyclerView extends RecyclerView {

    public HeaderAndFooterRecyclerView(Context context) {
        super(context);
    }

    public HeaderAndFooterRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public HeaderAndFooterRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }


    @Override
    public void setAdapter(Adapter adapter) {
        if (adapter instanceof HeadAndFooterWrapper){
            super.setAdapter((HeadAndFooterWrapper)adapter);
        }else {
            super.setAdapter(adapter);
        }
    }
}
