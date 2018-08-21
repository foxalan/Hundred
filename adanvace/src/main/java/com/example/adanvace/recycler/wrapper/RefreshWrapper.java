package com.example.adanvace.recycler.wrapper;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.example.adanvace.recycler.viewholder.MyViewHolder;

/**
 * @author alan
 *         Date  2018/6/12.
 *         Function :
 *         Issue :
 */

public abstract class RefreshWrapper extends HeadAndFooterWrapper {

    private final static int REFRESH_TYPE = 199999;
    private final static int LOAD_TYPE = 199998;

    private boolean canRefresh = false;
    private boolean canLoad = false;

    private View mRefreshView = null;
    private View mLoadView = null;


    public RefreshWrapper(Context context) {
        super(context);
    }

    public void addRefreshView(Context context){
        mRefreshView = new View(context);
    }

    public void setRefreshView(Context context,View refreshView){
        addRefreshView(context);
        mRefreshView = refreshView;
    }

    public void addLoadView(Context context){
        mLoadView = new View(context);
    }

    public void setLoadView(Context context,View loadView){
        addLoadView(context);
        mLoadView = loadView;
    }

    public void setRefreshViewHeight(){
    }

    public abstract RecyclerView.ViewHolder onCreateHeaderVH(ViewGroup parent, int viewType);
    public abstract RecyclerView.ViewHolder onCreateFooterVH(ViewGroup parent, int viewType);
    public abstract RecyclerView.ViewHolder onCreateGeneralVH(ViewGroup parent, int viewType);
    public abstract void onBindHeaderVH(RecyclerView.ViewHolder holder, int position);
    public abstract void onBindFooterVH(RecyclerView.ViewHolder holder, int position);
    public abstract void onBindGeneralVH(RecyclerView.ViewHolder holder, int position);


    /**
     * 绑定HeadView和LoadView
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public RecyclerView.ViewHolder onCreateHeaderViewHolder(ViewGroup parent, int viewType) {
        if (viewType == REFRESH_TYPE){
            return new MyViewHolder(mRefreshView);
        }
        return onCreateHeaderVH(parent, viewType);
    }

    @Override
    public RecyclerView.ViewHolder onCreateFooterViewHolder(ViewGroup parent, int viewType) {
        if (viewType == LOAD_TYPE){
            return new MyViewHolder(mLoadView);
        }
        return onCreateFooterVH(parent, viewType);
    }



    @Override
    public RecyclerView.ViewHolder onCreateGeneralViewHolder(ViewGroup parent, int viewType) {
        return onCreateGeneralVH(parent,viewType);
    }

    @Override
    public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (!(position == 0 && canRefresh)) {
            onBindHeaderVH(holder, position);
        }
    }

    @Override
    public void onBindFooterViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (!(position == getFooterViewCount() - 1 && canLoad)){
            onBindFooterVH(holder,position);
        }
    }

    @Override
    public void onBindGeneralViewHolder(RecyclerView.ViewHolder holder, int position) {
        onBindGeneralVH(holder, position);
    }

}
