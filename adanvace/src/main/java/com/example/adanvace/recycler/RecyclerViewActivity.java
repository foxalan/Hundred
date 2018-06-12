package com.example.adanvace.recycler;

import android.graphics.drawable.GradientDrawable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

import com.example.adanvace.R;
import com.example.adanvace.activity.view.BaseActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author alan
 *         Date  2018/6/10.
 *         Function :
 *         Issue :
 */

public class RecyclerViewActivity extends BaseActivity {


    private RecyclerView mRecyclerView;
    private List<String> data;
    private SimpleRecyclerAdapter mSimpleRecyclerAdapter;


    @Override
    public int getContentView() {
        return R.layout.activity_recycler;
    }

    @Override
    public void initView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
    }

    @Override
    public void initData() {

        data = new ArrayList<>();

        for (int i =0;i<20;i++){
            data.add("data:"+i);
        }

    }

    @Override
    public void initEvent() {
        mSimpleRecyclerAdapter = new SimpleRecyclerAdapter(this,data);

        mRecyclerView.setAdapter(mSimpleRecyclerAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayout.VERTICAL,false));
        //mRecyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayout.HORIZONTAL));
    }
}
