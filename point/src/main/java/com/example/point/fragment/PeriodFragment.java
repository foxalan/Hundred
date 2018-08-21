package com.example.point.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.point.R;
import com.example.point.util.L;

/**
 * Created by alan on 2018/3/4.
 * 1.Fragment的生命周期
 */

public class PeriodFragment extends BaseFragment {

    private static PeriodFragment periodFragment;

    public static PeriodFragment getInstance() {

        if (periodFragment == null) {
            periodFragment = new PeriodFragment();
        }

        return periodFragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        L.d("fragment onAttach");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        L.d("fragment onCreate");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        L.d("fragment onCreateView");
        View view = inflater.inflate(R.layout.module_fragment_period, container, false);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        L.d("fragment onViewCreated");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        L.d("fragment onActivityCreated");
    }

    @Override
    public void onStart() {
        super.onStart();
        L.d("fragment onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        L.d("fragment onResume");
    }

    @Override
    public void onPause() {
        super.onPause();

        L.d("fragment onPause");
    }

    @Override
    public void onStop() {
        super.onStop();

        L.d("fragment onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        L.d("fragment onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        L.d("fragment onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();

        L.d("fragment onDetach");
    }


}
