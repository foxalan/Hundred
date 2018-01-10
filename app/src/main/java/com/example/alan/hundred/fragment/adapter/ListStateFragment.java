package com.example.alan.hundred.fragment.adapter;

import android.os.Bundle;
import android.widget.ListView;

import com.example.alan.hundred.R;
import com.example.alan.hundred.adapter.StateAdapter;
import com.example.alan.hundred.base.RxBaseFragment;
import com.example.alan.hundred.data.ListSource;
import com.example.alan.hundred.info.StateInfo;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Function :
 * @Author : Alan
 * Modify Date : 27/9/17
 * Issue : TODO
 * Whether solve :
 */

public class ListStateFragment extends RxBaseFragment {

    private static ListStateFragment listStateFragment;

    @BindView(R.id.lv_state)
    ListView mListView;

    private StateAdapter adapter;
    private ListSource listSource;
    private List<StateInfo> stateList = new ArrayList<>();

    public static ListStateFragment getInstance() {
        if (listStateFragment == null) {
            listStateFragment = new ListStateFragment();
        }
        return listStateFragment;
    }


    @Override
    public void finishCreateView(Bundle state) {
        initData();
        initEvent();
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_list_state;
    }


    private void initData() {

        listSource = new ListSource(getActivity());
        stateList = listSource.getStateInfoList();
    }

    private void initEvent() {

        adapter = new StateAdapter(getActivity(), stateList);
        mListView.setAdapter(adapter);
    }


}
