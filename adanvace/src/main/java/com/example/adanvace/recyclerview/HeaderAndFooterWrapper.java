package com.example.adanvace.recyclerview;

import android.content.Context;
import android.support.v4.util.SparseArrayCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.adanvace.recycler.wrapper.HeadAndFooterWrapper;

import java.util.List;

/**
 * @author alan
 *         Date  2018/6/17.
 *         Function : 动态的为RecyclerView添加HeaderView 和FooterView
 *         Issue :
 */

public  class HeaderAndFooterWrapper extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int HEADER_TYPE = 10000;
    private static final int FOOTER_TYPE = 20000;
    private static final int BASE_ITEM_TYPE_HEADER = 100000;
    private static final int BASE_ITEM_TYPE_FOOTER = 200000;

    private SparseArrayCompat<View> mHeaderViews = new SparseArrayCompat<>();
    private SparseArrayCompat<View> mFootViews = new SparseArrayCompat<>();
    private RecyclerView.Adapter mInnerAdapter;


    public HeaderAndFooterWrapper(RecyclerView.Adapter adapter)
    {
        mInnerAdapter = adapter;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mHeaderViews.get(viewType) != null)
        {
            ViewHolder holder = ViewHolder.createViewHolder(parent.getContext(), mHeaderViews.get(viewType));
            return holder;

        } else if (mFootViews.get(viewType) != null)
        {
           ViewHolder holder = ViewHolder.createViewHolder(parent.getContext(), mFootViews.get(viewType));

            return holder;
        }
        return mInnerAdapter.onCreateViewHolder(parent, viewType);

    }

//    public abstract RecyclerView.ViewHolder onCreateHeaderViewHolder(ViewGroup parent, int viewType);
//    public abstract RecyclerView.ViewHolder onCreateFooterViewHolder(ViewGroup parent, int viewType);
//    public abstract RecyclerView.ViewHolder onGeneralViewHolder(ViewGroup parent, int viewType);

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (isHeaderViewPos(position))
        {
            return;
        }
        if (isFooterViewPos(position))
        {
            return;
        }
        mInnerAdapter.onBindViewHolder(holder, position - getHeadersCount());
    }

//    /**
//     * 绑定Header ViewHolder
//     *
//     * @param holder
//     * @param position
//     */
//    public abstract void onBindHeaderViewHolder(RecyclerView.ViewHolder holder, int position);
//
//    /**
//     * 绑定Footer ViewHolder
//     *
//     * @param holder
//     * @param position
//     */
//    public abstract void onBindFooterViewHolder(RecyclerView.ViewHolder holder, int position);
//
//    /**
//     * 绑定常规布局
//     * @param holder
//     * @param position
//     */
//    public abstract void onBindGeneralViewHolder(RecyclerView.ViewHolder holder, int position);

    @Override
    public int getItemCount() {
         return getHeadersCount() + getFootersCount() + getRealItemCount();
    }

    /**
     * todo
     *
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {

        if (isHeaderViewPos(position))
        {
            return mHeaderViews.keyAt(position);
        } else if (isFooterViewPos(position))
        {
            return mFootViews.keyAt(position - getHeadersCount() - getRealItemCount());
        }
        return mInnerAdapter.getItemViewType(position - getHeadersCount());
    }

    /**
     * 判断当前位置是否是HeaderView
     *
     * @param position
     * @return
     */
    private boolean isHeaderViewPos(int position)
    {
        return position < getHeadersCount();
    }

    /**
     * 判断当前位置是否是FooterView
     *
     * @param position
     * @return
     */
    private boolean isFooterViewPos(int position)
    {
        return position >= getHeadersCount() + getRealItemCount();
    }


    public int getHeadersCount()
    {
        return mHeaderViews.size();
    }

    public int getFootersCount()
    {
        return mFootViews.size();
    }

    private int getRealItemCount()
    {
        return mInnerAdapter.getItemCount();
    }


    public void addHeaderView(View view)
    {
        mHeaderViews.put(mHeaderViews.size() + BASE_ITEM_TYPE_HEADER, view);
    }

    public void addFootView(View view)
    {
        mFootViews.put(mFootViews.size() + BASE_ITEM_TYPE_FOOTER, view);
    }



    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView)
    {
        mInnerAdapter.onAttachedToRecyclerView(recyclerView);

        final RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager)
        {
            final GridLayoutManager gridLayoutManager = (GridLayoutManager) layoutManager;
            final GridLayoutManager.SpanSizeLookup spanSizeLookup = gridLayoutManager.getSpanSizeLookup();

            gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup()
            {
                @Override
                public int getSpanSize(int position)
                {
                    int viewType = getItemViewType(position);
                    if (mHeaderViews.get(viewType) != null)
                    {
                        return ((GridLayoutManager) layoutManager).getSpanCount();
                    } else if (mFootViews.get(viewType) != null)
                    {
                        return ((GridLayoutManager) layoutManager).getSpanCount();
                    }
                    if (spanSizeLookup != null)
                        return spanSizeLookup.getSpanSize(position);
                    return 1;
                }
            });
            gridLayoutManager.setSpanCount(gridLayoutManager.getSpanCount());
        }
    }




}
