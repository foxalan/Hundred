package com.example.alan.hundred.fragment.page;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterViewFlipper;

import com.example.alan.hundred.R;
import com.example.alan.hundred.adapter.FlipperAdapter;
import com.example.alan.hundred.data.ListSource;
import com.example.alan.hundred.fragment.BaseSupportFragment;
import com.example.alan.hundred.info.FlipperInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Function :
 * Author : Alan
 * Modify Date : 27/9/17
 * Issue : 1.viewPager的懒加载
 * Whether solve :
 */

public class PageFlipperFragment extends BaseSupportFragment{

    private AdapterViewFlipper adapterViewFlipper;

    private List<FlipperInfo> flipperList = new ArrayList<>();
    private FlipperAdapter adapter;
    private ListSource source;

    private static PageFlipperFragment flipperFragment;

    public static PageFlipperFragment getInstance(){
        if (flipperFragment == null){
            flipperFragment = new PageFlipperFragment();
        }

        return flipperFragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_page_flipper,container,false);

        initViews(rootView);
        initData();
        initEvent();

        return rootView;
    }

    private void initViews(View rootView) {

        adapterViewFlipper = rootView.findViewById(R.id.avf_page);
    }

    private void initData(){
        source = new ListSource(getActivity());
        flipperList = source.getFlipperInfoList();
        adapter = new FlipperAdapter(getActivity(),flipperList,R.layout.list_flipper_item);
    }

    private void initEvent(){

        adapterViewFlipper.setAdapter(adapter);
        adapterViewFlipper.setFlipInterval(5000);
        adapterViewFlipper.startFlipping();
    }
}
