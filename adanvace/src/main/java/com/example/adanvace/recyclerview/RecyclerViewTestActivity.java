package com.example.adanvace.recyclerview;

import android.os.Handler;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.adanvace.R;
import com.example.adanvace.activity.view.BaseActivity;
import com.example.adanvace.adapter.CustomRecyclerViewAdapter;
import com.example.adanvace.adapter.viewholder.CustomRecyclerViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * @author alan
 *         Date  2018/6/17.
 *         Function :
 *         Issue :
 */

public class RecyclerViewTestActivity extends BaseActivity {

    private RecyclerView mRecyclerView;
    private HeaderAndFooterWrapper mHeaderAndFooterWrapper;
    private LoadMoreWrapper mLoadMoreWrapper;

    private List<String> mData = new ArrayList<>();
    private CustomRecyclerViewAdapter<String> mAdapter;

    @Override
    public int getContentView() {
        return R.layout.activity_recyclerview_test;
    }

    @Override
    public void initView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.ryc_head_foot);

        ImageView imageView = new ImageView(this);
        List<View> headerView = new ArrayList<>();
        headerView.add(imageView);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        initHeaderAndFooter();


    }

    private void initHeaderAndFooter()
    {

        for (int i = 'A'; i <= 'z'; i++)
        {
            mData.add((char) i + "");
        }

        mAdapter = new CustomRecyclerViewAdapter<String>(this, mData,R.layout.item_recycler_text,null) {
            @Override
            public void onBindViewHolder(CustomRecyclerViewHolder holder, int position) {
                TextView textView = holder.getView(R.id.tv_item_recycler);
                textView.setText(mData.get(position));
             //   holder.setText(R.id.tv_item_recycler,mData.get(position));
            }
        };

        mHeaderAndFooterWrapper = new HeaderAndFooterWrapper(mAdapter);

        TextView t1 = new TextView(this);
        t1.setText("Header 1");
        TextView t2 = new TextView(this);
        t2.setText("Header 2");
        mHeaderAndFooterWrapper.addHeaderView(t1);
        mHeaderAndFooterWrapper.addHeaderView(t2);
        mHeaderAndFooterWrapper.addFootView(t1);
        mLoadMoreWrapper = new LoadMoreWrapper(mHeaderAndFooterWrapper);
        mLoadMoreWrapper.setLoadMoreView(R.layout.default_loading);
        mLoadMoreWrapper.setOnLoadMoreListener(new LoadMoreWrapper.OnLoadMoreListener()
        {
            @Override
            public void onLoadMoreRequested()
            {
                new Handler().postDelayed(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        for (int i = 0; i < 10; i++)
                        {
                            mData.add("Add:" + i);
                        }
                        mLoadMoreWrapper.notifyDataSetChanged();

                    }
                }, 3000);
            }
        });

        mRecyclerView.setAdapter(mLoadMoreWrapper);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initEvent() {

    }


}
