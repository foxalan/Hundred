package com.example.alan.hundred.view;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.alan.hundred.R;

/**
 * Function :
 * Author : Alan
 * Modify Date : 29/9/17
 * Issue : TODO
 * Whether solve :
 */

public class DialogView {

    private Context context;
    private LayoutInflater inflater;

    //当前页面的Activity
    private ViewGroup vp_home;
    //显示一个带背景的Dialog
    private ViewGroup rootView;
    //Dialog主体
    private ViewGroup container;


    private View view_dialog;

    private TextView tv_dialog_title;
    private TextView tv_dialog_content;
    private ViewStub vs_bottom;


    public DialogView(Context context) {

        this.context = context;
        inflater = LayoutInflater.from(context);

        initView();
    }


    private void initView() {
        vp_home = (ViewGroup) ((Activity) context).getWindow().getDecorView().findViewById(android.R.id.content);
        rootView = (ViewGroup) inflater.inflate(R.layout.dialog_container, vp_home, false);

        container = (ViewGroup) rootView.findViewById(R.id.fl_dialog_container);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        params.setMargins(context.getResources().getDimensionPixelSize(R.dimen.dialog_margin_left),
                context.getResources().getDimensionPixelSize(R.dimen.dialog_margin_top),
                context.getResources().getDimensionPixelSize(R.dimen.dialog_margin_right),
                context.getResources().getDimensionPixelSize(R.dimen.dialog_margin_bottom));

        container.setLayoutParams(params);

        view_dialog = inflater.inflate(R.layout.dialog_content, container, false);
        container.addView(view_dialog);

        initHeaderView(view_dialog);


    }

    /**
     * 初始化Title Content
     *
     * @param container
     */
    private void initHeaderView(View container) {

        tv_dialog_title = (TextView) container.findViewById(R.id.tv_dialog_title);
        tv_dialog_content = (TextView) container.findViewById(R.id.tv_dialog_content);
        vs_bottom = (ViewStub) container.findViewById(R.id.vs_bottom);

    }

    public void show() {
        if (rootView != null) {
            vp_home.addView(rootView);
        }
    }


}
