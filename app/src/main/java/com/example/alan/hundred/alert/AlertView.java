package com.example.alan.hundred.alert;

import android.app.Activity;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.alan.hundred.R;

import java.util.List;

/**
 * Function :
 * Modify Date : 2018/1/11
 *
 * @Author : Alan
 * Issue : TODO
 * Whether Solve :
 */

public class AlertView {

    private String mTitle;
    private String mContent;
    private List<String> mDestructive;
    private AlertType mType;
    private Context mContext;
    private LayoutInflater layoutInflater;

    /**
     * activity的根View
     */
    private ViewGroup rootView;
    /**
     * AlertView 的 根View
     */
    private ViewGroup decorView;
    /**
     * 窗口headerView
     */
    private ViewGroup loAlertHeader;
    private ViewGroup contentContainer;

    private final FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, Gravity.BOTTOM
    );


    public AlertView(String mTitle, String mContent, List<String> mDestructive, AlertType mType, Context mContext) {
        this.mTitle = mTitle;
        this.mContent = mContent;
        this.mDestructive = mDestructive;
        this.mType = mType;
        this.mContext = mContext;
        layoutInflater = LayoutInflater.from(mContext);
        initView();
    }

    static class AlertViewHolder {
        static final AlertViewBuilder BUILDER = new AlertViewBuilder();
    }

    public static AlertViewBuilder builder() {
        return AlertViewHolder.BUILDER;
    }

    public void show() {
        onAttached(rootView);
    }

    public void initView() {

        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        decorView = ((Activity) mContext).getWindow().getDecorView().findViewById(android.R.id.content);
        rootView = (ViewGroup) layoutInflater.inflate(R.layout.layout_alertview, decorView, false);
        rootView.setLayoutParams(new FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT
        ));
        contentContainer = rootView.findViewById(R.id.content_container);
        setAlertView();

    }

    private void setAlertView() {
        int margin_alert_left_right = 0;
        switch (mType) {
            case NORMAL:
                params.gravity = Gravity.CENTER;
                margin_alert_left_right = mContext.getResources().getDimensionPixelSize(R.dimen.margin_alert_left_right);
                params.setMargins(margin_alert_left_right, 0, margin_alert_left_right, 0);
                contentContainer.setLayoutParams(params);
                initAlertViews(layoutInflater);
                break;
            case BOTTOM:

                break;
            default:
                break;
        }
    }

    private void initAlertViews(LayoutInflater layoutInflater) {
        ViewGroup viewGroup = (ViewGroup) layoutInflater.inflate(R.layout.layout_alertview_alert, contentContainer);
        initHeaderView(viewGroup);

        ViewStub viewStub = contentContainer.findViewById(R.id.viewStubHorizontal);
        viewStub.inflate();
        LinearLayout loAlertButtons = contentContainer.findViewById(R.id.loAlertButtons);

        for (int i = 0; i < mDestructive.size(); i++) {
            //如果不是第一个按钮
            if (i != 0) {
                //添加上按钮之间的分割线
                View divier = new View(mContext);
                divier.setBackgroundColor(mContext.getResources().getColor(R.color.bgColor_divier));
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams((int) mContext.getResources().getDimension(R.dimen.size_divier), LinearLayout.LayoutParams.MATCH_PARENT);
                loAlertButtons.addView(divier, params);
            }
            View itemView = LayoutInflater.from(mContext).inflate(R.layout.item_alertbutton, null);
            TextView tvAlert = itemView.findViewById(R.id.tvAlert);
            tvAlert.setClickable(true);

           if (mDestructive.size() == 1) {
                tvAlert.setBackgroundResource(R.drawable.bg_alertbutton_bottom);
            } else if (i == 0) {
                //设置最左边的按钮效果
                tvAlert.setBackgroundResource(R.drawable.bg_alertbutton_left);
            } else if (i == mDestructive.size() - 1) {
                //设置最右边的按钮效果
                tvAlert.setBackgroundResource(R.drawable.bg_alertbutton_right);
            }
            //设置点击效果
            String data = mDestructive.get(i);
            tvAlert.setText(data);
//
//            //取消按钮的样式
//            if (data == cancel) {
//                tvAlert.setTypeface(Typeface.DEFAULT_BOLD);
//                tvAlert.setTextColor(context.getResources().getColor(R.color.textColor_alert_button_cancel));
//                tvAlert.setOnClickListener(new OnTextClickListener(CANCELPOSITION));
//                position = position - 1;
//            }
//            //高亮按钮的样式
//            else if (mDestructive != null && mDestructive.contains(data)) {
//                tvAlert.setTextColor(context.getResources().getColor(R.color.textColor_alert_button_destructive));
//            }
//
//            tvAlert.setOnClickListener(new OnTextClickListener(position));
//            position++;
//            loAlertButtons.addView(itemView, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
//                    LinearLayout.LayoutParams.WRAP_CONTENT, 1));
        }


    }

    /**
     * 初始化title和Content
     *
     * @param viewGroup
     */
    private void initHeaderView(ViewGroup viewGroup) {

        loAlertHeader = viewGroup.findViewById(R.id.loAlertHeader);
        //标题和消息
        TextView tvAlertTitle = viewGroup.findViewById(R.id.tvAlertTitle);
        TextView tvAlertMsg = viewGroup.findViewById(R.id.tvAlertMsg);
        if (mTitle != null) {
            tvAlertTitle.setText(mTitle);
        } else {
            tvAlertTitle.setVisibility(View.GONE);
        }
        if (mContent != null) {
            tvAlertMsg.setText(mContent);
        } else {
            tvAlertMsg.setVisibility(View.GONE);
        }
    }

    private void onAttached(View view) {
        decorView.addView(view);
//        contentContainer.startAnimation(inAnim);
    }


}
