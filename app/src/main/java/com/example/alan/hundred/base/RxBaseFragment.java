package com.example.alan.hundred.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Function :
 * Modify Date : 2018/1/8
 *
 * @Author : Alan
 * Issue : TODO
 * Whether Solve :
 */

public abstract class RxBaseFragment extends Fragment {

    private View rootView;
    private Unbinder unbinder;
    private FragmentActivity fragmentActivity;
    protected boolean isVisible;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        fragmentActivity = (FragmentActivity) context;
    }

    public FragmentActivity getBaseActivity() {
        if (fragmentActivity != null) {

            return fragmentActivity;
        }

        return null;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        rootView = inflater.inflate(getLayoutId(), container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this, view);
        finishCreateView(savedInstanceState);
    }

    /**
     * 初始化views
     *
     * @param state
     */
    public abstract void finishCreateView(Bundle state);


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    /**
     * 获取Layout
     *
     * @return
     */
    public abstract int getLayoutId();

    /**
     * Fragment数据的懒加载
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {
            isVisible = true;
            onVisible();
        } else {
            isVisible = false;
            onInvisible();
        }
    }

    /**
     * fragment显示时才加载数据
     */
    protected void onVisible() {
        lazyLoad();
    }

    /**
     * fragment懒加载方法
     */
    protected void lazyLoad() {
    }

    /**
     * fragment隐藏
     */
    protected void onInvisible() {

    }
}
