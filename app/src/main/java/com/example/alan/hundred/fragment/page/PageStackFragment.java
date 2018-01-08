package com.example.alan.hundred.fragment.page;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.StackView;

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
 * Issue : TODO
 * Whether solve :
 */

public class PageStackFragment extends BaseSupportFragment {

    private static PageStackFragment stackFragment;

    public static PageStackFragment getInstance() {
        if (stackFragment == null) {
            stackFragment = new PageStackFragment();
        }

        return stackFragment;
    }

    private StackView sv_page;

    private List<FlipperInfo> flipperList = new ArrayList<>();
    private FlipperAdapter adapter;
    private ListSource source;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_page_stack, container, false);

        initViews(rootView);
        initData();
        initEvent();

        return rootView;
    }

    private void initViews(View rootView) {
        sv_page = (StackView) rootView.findViewById(R.id.sv_page);

    }

    private void initData() {

        source = new ListSource(getActivity());
        flipperList = source.getFlipperInfoList();
        adapter = new FlipperAdapter(getActivity(), flipperList, R.layout.list_flipper_item);
    }

    private void initEvent() {
        sv_page.setAdapter(adapter);
    }


}
