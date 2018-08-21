package com.example.adanvace.recycler.wrapper;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.ViewGroup;

import com.example.adanvace.recycler.bean.DataBean;
import com.example.adanvace.recycler.viewholder.MyViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * @author alan
 *         Date  2018/6/12.
 *         Function :
 *         Issue :
 */

public abstract class HeadAndFooterWrapper extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int BASE_ITEM_TYPE_FOOTER = 2000000;
    //footerView默认type
    private static final int BASE_ITEM_TYPE_HEADER = 1000000;
    // headerView默认type
    private static final int BASE_ITEM_TYPE_GENERAL= 0;
    // 一般view默认type 0也是android源码中的默认的type值

    private List<DataBean> mHeaders;
    private List<DataBean> mFooters;
    private List<DataBean> mGeneralData;


    public HeadAndFooterWrapper(Context context) {
        super();
        init(context);
    }

    private void init(Context context) {

        mHeaders = new ArrayList<>();
        mFooters = new ArrayList<>();
        mGeneralData = new ArrayList<>();

    }

    /**
     * 设置ViewType
     *
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {

        if (isHeadPosition(position)) {
            return mHeaders.get(position).getType();
        }

        if (isFooterPosition(position)) {
            return mFooters.get(position).getType();
        }

        return mGeneralData.get(position - getHeaderViewCount()).getType();
    }

    /**
     * 绑定HeadView
     *
     * @param parent
     * @param viewType
     * @return
     */
    public abstract RecyclerView.ViewHolder onCreateHeaderViewHolder(ViewGroup parent, int viewType);

    public abstract RecyclerView.ViewHolder onCreateFooterViewHolder(ViewGroup parent, int viewType);

    public abstract RecyclerView.ViewHolder onCreateGeneralViewHolder(ViewGroup parent, int viewType);

    public abstract void onBindHeaderViewHolder(RecyclerView.ViewHolder holder, int position);

    public abstract void onBindFooterViewHolder(RecyclerView.ViewHolder holder, int position);

    public abstract void onBindGeneralViewHolder(RecyclerView.ViewHolder holder, int position);



    @Override
    public int getItemCount() {
        return getShowedViewCount();
    }


    private boolean isHeaderViewType(int type) {
        boolean result = false;
        if (mHeaders.size() > 0) {
            for (DataBean dataBean : mHeaders) {
                if (dataBean.getType() == type) {
                    return true;
                }
            }
        }
        return result;
    }

    private boolean isFooterViewType(int type) {
        boolean result = false;
        if (mFooters.size() > 0) {
            for (DataBean dataBean : mFooters) {
                if (dataBean.getType() == type) {
                    return true;
                }
            }
        }
        return result;
    }


    private boolean isHeadPosition(int position) {
        return getHeaderViewCount() > position;
    }

    private boolean isFooterPosition(int position) {
        return ((getHeaderViewCount() + getInnerItemCount()) <= position) && position < getShowedViewCount();
    }


    private int getHeaderViewCount() {
        return mHeaders.size();
    }

    private int getShowedViewCount() {
        return getInnerItemCount() + getHeaderViewCount() + getFooterViewCount();
    }

    public int getFooterViewCount() {
        return mFooters.size();
    }

    private int getInnerItemCount() {
        return mGeneralData.size();
    }


    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        //为了兼容GridLayout
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            final GridLayoutManager gridLayoutManager = (GridLayoutManager) layoutManager;
            final GridLayoutManager.SpanSizeLookup spanSizeLookup = gridLayoutManager.getSpanSizeLookup();

            gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    if (isHeadPosition(position)) {
                        return gridLayoutManager.getSpanCount();
                    } else if (isFooterPosition(position)) {
                        return gridLayoutManager.getSpanCount();
                    }
                    if (spanSizeLookup != null){

                        return spanSizeLookup.getSpanSize(position);
                    }

                    return 1;
                }
            });
            gridLayoutManager.setSpanCount(gridLayoutManager.getSpanCount());
        }

    }

    @Override
    public void onViewAttachedToWindow(RecyclerView.ViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        int position = holder.getLayoutPosition();
        if (isHeadPosition(position) || isFooterPosition(position)) {
            ViewGroup.LayoutParams lp = holder.itemView.getLayoutParams();

            if (lp != null
                    && lp instanceof StaggeredGridLayoutManager.LayoutParams) {

                StaggeredGridLayoutManager.LayoutParams p =
                        (StaggeredGridLayoutManager.LayoutParams) lp;

                p.setFullSpan(true);
            }
        }
    }

    public void deleteGeneralItem(int position){
        if (position < 0){
            throw new IllegalArgumentException("position can not be less than 0!");
        }
        if(mGeneralData.size() == 0){

            return;
        }
        if(mGeneralData.size() -1 < position){
            mGeneralData.remove(mGeneralData.size() - 1);
        }else {
            mGeneralData.remove(position);
        }
        notifyDataSetChanged();
    }

    public void deleteHeader(int position ,boolean invalidate){
        mHeaders.remove(position);
        if (invalidate){
            notifyDataSetChanged();
        }
    }

    public void deleteFooter(int position ,boolean invalidate){
        mFooters.remove(position);
        if (invalidate){
            notifyDataSetChanged();
        }
    }

    public void addHeader(Object data){
        addHeader(data,BASE_ITEM_TYPE_HEADER);
    }

    public void addHeader(Object data,int type){
        mHeaders.add(new DataBean(data,type));
    }

    public void addFooter(Object data){
        addFooter(data,BASE_ITEM_TYPE_FOOTER);
    }

    public void addFooter(Object data,int type){
        mFooters.add(new DataBean(data,type));
    }

    public void addGeneral(Object data){
        addGeneral(data,BASE_ITEM_TYPE_GENERAL);
    }

    public void addGeneral(Object data,int type){
        mGeneralData.add(new DataBean(data,type));
    }
}

