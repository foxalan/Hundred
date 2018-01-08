package com.example.alan.hundred.fragment.adapter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.alan.hundred.R;
import com.example.alan.hundred.adapter.StateAdapter;
import com.example.alan.hundred.data.ListSource;
import com.example.alan.hundred.fragment.BaseFragment;
import com.example.alan.hundred.info.StateInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Function :
 * Author : Alan
 * Modify Date : 27/9/17
 * Issue : TODO
 * Whether solve :
 */

public class ListStateFragment extends BaseFragment {

    private static ListStateFragment listStateFragment;

    private static Object i = 0;

    private ListView lv_state;
    private StateAdapter adapter;

    private ListSource listSource;
    private List<StateInfo> stateList = new ArrayList<>();

    public static ListStateFragment getInstance() {
        if (listStateFragment == null) {
            synchronized (i) {
                listStateFragment = new ListStateFragment();
                Bundle bundle = new Bundle();
                bundle.putString("fragmentName", "list_state");
                bundle.putInt("fragmentId", LIST_FRAGMENT_STATE);
                listStateFragment.setArguments(bundle);
            }
        }

        return listStateFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_list_state, container, false);

        initView(rootView);
        initData();
        initEvent();

        return rootView;
    }

    private void initView(View rootView) {
        lv_state = (ListView) rootView.findViewById(R.id.lv_state);
    }

    private void initData() {

        listSource = new ListSource(getActivity());
        stateList = listSource.getStateInfoList();
    }

    private void initEvent(){

        adapter = new StateAdapter(getActivity(),stateList);
        lv_state.setAdapter(adapter);
    }


}
