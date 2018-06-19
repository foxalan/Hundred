package com.example.adanvace.recyclerview;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;

/**
 * @author alan
 *         Date  2018/6/17.
 *         Function :
 *         Issue :
 */

public class LoadMoreWrapper extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final int ITEM_TYPE_LOAD_MORE = Integer.MAX_VALUE - 2;
    private View mLoadMoreView;
    private int mLoadMoreLayoutId;
    private OnLoadMoreListener mOnLoadMoreListener;

    private boolean hasLoadMore()
    {
        return mLoadMoreView != null || mLoadMoreLayoutId != 0;
    }

    private boolean isShowLoadMore(int position)
    {
        return hasLoadMore() && (position >= mInnerAdapter.getItemCount());
    }


    public View getLoadMoreView() {
        return mLoadMoreView;
    }



    private RecyclerView.Adapter mInnerAdapter;

    public LoadMoreWrapper(RecyclerView.Adapter adapter){
        this.mInnerAdapter = adapter;
    }



    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == ITEM_TYPE_LOAD_MORE)
        {
            ViewHolder holder;
            if (mLoadMoreView != null)
            {
                holder = ViewHolder.createViewHolder(parent.getContext(), mLoadMoreView);
            } else
            {
                holder = ViewHolder.createViewHolder(parent.getContext(), parent, mLoadMoreLayoutId);
            }
            return holder;
        }
        return mInnerAdapter.onCreateViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (isShowLoadMore(position))
        {
            if (mOnLoadMoreListener != null)
            {
                mOnLoadMoreListener.onLoadMoreRequested();
            }
            return;
        }
        mInnerAdapter.onBindViewHolder(holder, position);
    }

    @Override
    public int getItemCount() {
         return mInnerAdapter.getItemCount() + (hasLoadMore() ? 1 : 0);
    }

    @Override
    public int getItemViewType(int position) {

        if (isShowLoadMore(position))
        {
            return ITEM_TYPE_LOAD_MORE;
        }
        return mInnerAdapter.getItemViewType(position);
    }

    public interface OnLoadMoreListener{
        void onLoadMoreRequested();
    }

    public LoadMoreWrapper setOnLoadMoreListener(OnLoadMoreListener loadMoreListener)
    {
        if (loadMoreListener != null)
        {
            mOnLoadMoreListener = loadMoreListener;
        }
        return this;
    }

    public LoadMoreWrapper setLoadMoreView(View loadMoreView)
    {
        mLoadMoreView = loadMoreView;
        return this;
    }

    public LoadMoreWrapper setLoadMoreView(int layoutId)
    {
        mLoadMoreLayoutId = layoutId;
        return this;
    }


    @Override
    public void onViewAttachedToWindow(RecyclerView.ViewHolder holder)
    {
        mInnerAdapter.onViewAttachedToWindow(holder);

        if (isShowLoadMore(holder.getLayoutPosition()))
        {
            setFullSpan(holder);
        }
    }

    private void setFullSpan(RecyclerView.ViewHolder holder)
    {
        ViewGroup.LayoutParams lp = holder.itemView.getLayoutParams();

        if (lp != null
                && lp instanceof StaggeredGridLayoutManager.LayoutParams)
        {
            StaggeredGridLayoutManager.LayoutParams p = (StaggeredGridLayoutManager.LayoutParams) lp;

            p.setFullSpan(true);
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView)
    {
        WrapperUtils.onAttachedToRecyclerView(mInnerAdapter, recyclerView, new WrapperUtils.SpanSizeCallback()
        {
            @Override
            public int getSpanSize(GridLayoutManager layoutManager, GridLayoutManager.SpanSizeLookup oldLookup, int position)
            {
                if (isShowLoadMore(position))
                {
                    return layoutManager.getSpanCount();
                }
                if (oldLookup != null)
                {
                    return oldLookup.getSpanSize(position);
                }
                return 1;
            }
        });
    }
}
