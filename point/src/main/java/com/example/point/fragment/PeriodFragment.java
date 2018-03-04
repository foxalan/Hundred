package com.example.point.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        L.d("fragment onCreateView");

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        L.d("fragment onViewCreated");
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
