package com.example.alan.hundred.fragment.page;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.alan.hundred.R;
import com.example.alan.hundred.adapter.page.PageAdapter;
import com.example.alan.hundred.base.RxBaseFragment;
import com.example.alan.hundred.info.PageInfo;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Function :
 *
 * @Author : Alan
 * Modify Date : 27/9/17
 * Issue : 1.viewPager的懒加载
 * Whether solve :
 */

public class PageFlipperFragment extends RxBaseFragment {

    private List<PageInfo> pageInfoList = new ArrayList<>();
    private int totalNum = 60;
    private static final String TAG = "PageFlipperFragment";

    @BindView(R.id.page_recycler_view)
    RecyclerView mRecyclerView;

    PageAdapter mPageAdapter;
    Bitmap bitmap;

    private static PageFlipperFragment flipperFragment;

    public static PageFlipperFragment getInstance() {
        if (flipperFragment == null) {
            flipperFragment = new PageFlipperFragment();
        }
        return flipperFragment;
    }


    @Override
    public void finishCreateView(Bundle state) {
        initData();
        initEvent();
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_page_flipper;
    }


    private void initData() {
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.guide_concise);
        for (int i = 0; i < totalNum; i++) {
            PageInfo info = new PageInfo();

            if (i < 11) {
                info.setType(1);
                info.setBitmap(bitmap);
            } else {
                info.setType(2);
                info.setBitmap(bitmap);
                info.setTitle("alan" + i);
            }
            pageInfoList.add(info);
        }

        mPageAdapter = new PageAdapter(pageInfoList, getContext());
    }

    private void initEvent() {


        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
//        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
//            @Override
//            public int getSpanSize(int position) {
//                if (position>10){
//
//                    return 2;
//                }else {
//
//                    return 1;
//                }
//            }
//        });

        mRecyclerView.setLayoutManager(gridLayoutManager);
        mRecyclerView.setAdapter(mPageAdapter);
    }

}