package com.example.alan.hundred.alert;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
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

    private static final String TAG = "AlertView";
    private String mTitle;
    private String mContent;
    private List<String> mDestructive;
    private AlertType mType;
    private Context mContext;
    private LayoutInflater layoutInflater;
    private IAlertCallBack iAlertCallBack;
    private List<String> mDataList;

    private String cancel = "取消";

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

    private Animation outAnim;
    private Animation inAnim;
    private int gravity = Gravity.CENTER;
    private boolean isDismissing;

    private final FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, Gravity.BOTTOM
    );


    public AlertView(String mTitle, String mContent, List<String> mDestructive,
                     AlertType mType, Context mContext, IAlertCallBack iAlertCallBack
            , List<String> mDataList) {
        this.mTitle = mTitle;
        this.mContent = mContent;
        this.mDestructive = mDestructive;
        this.mType = mType;
        this.mContext = mContext;
        this.iAlertCallBack = iAlertCallBack;
        this.mDataList = mDataList;

        Log.e(TAG, "AlertView: " + mTitle + mContent);
        layoutInflater = LayoutInflater.from(mContext);
        init();
        initView();
    }

    protected void init() {
        inAnim = getInAnimation();
        outAnim = getOutAnimation();
    }

    public Animation getInAnimation() {
        int res = AlertAnimateUtil.getAnimationResource(this.gravity, true);
        return AnimationUtils.loadAnimation(mContext, res);
    }

    public Animation getOutAnimation() {
        int res = AlertAnimateUtil.getAnimationResource(this.gravity, false);
        return AnimationUtils.loadAnimation(mContext, res);
    }

//    static class AlertViewHolder {
//        static AlertViewBuilder BUILDER = new AlertViewBuilder();
//    }

    public static AlertViewBuilder builder() {
        return new AlertViewBuilder();
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
                params.gravity = Gravity.BOTTOM;
                margin_alert_left_right = mContext.getResources().getDimensionPixelSize(R.dimen.margin_actionsheet_left_right);
                params.setMargins(margin_alert_left_right, 0, margin_alert_left_right, margin_alert_left_right);
                contentContainer.setLayoutParams(params);
                initActionSheetViews(layoutInflater);
                break;
            default:
                break;
        }
    }

    private void initActionSheetViews(LayoutInflater layoutInflater) {
        ViewGroup viewGroup = (ViewGroup) layoutInflater.inflate(R.layout.layout_alertview_actionsheet, contentContainer);
        initHeaderView(viewGroup);

        initListView();

        TextView tvAlertCancel = contentContainer.findViewById(R.id.tvAlertCancel);
        if (cancel != null) {
            tvAlertCancel.setVisibility(View.VISIBLE);
            tvAlertCancel.setText(cancel);
            tvAlertCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dismiss();
                }
            });
        }

    }

    protected void initListView() {
        ListView alertButtonListView = contentContainer.findViewById(R.id.alertButtonListView);
        //把cancel作为footerView

        View itemView = LayoutInflater.from(mContext).inflate(R.layout.item_alertbutton, null);
        TextView tvAlert = itemView.findViewById(R.id.tvAlert);
        tvAlert.setText(cancel);
        tvAlert.setClickable(true);
        tvAlert.setTypeface(Typeface.DEFAULT_BOLD);
        tvAlert.setTextColor(mContext.getResources().getColor(R.color.textColor_alert_button_cancel));
        tvAlert.setBackgroundResource(R.drawable.bg_alertbutton_bottom);
        //  alertButtonListView.addFooterView(itemView);

        AlertViewAdapter adapter = new AlertViewAdapter(mDataList, null);
        alertButtonListView.setAdapter(adapter);
        alertButtonListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                dismiss();
            }
        });
    }

    /**
     * 正常模式
     *
     * @param layoutInflater
     */
    private void initAlertViews(LayoutInflater layoutInflater) {
        ViewGroup viewGroup = (ViewGroup) layoutInflater.inflate(R.layout.layout_alertview_alert, contentContainer);
        initHeaderView(viewGroup);

        ViewStub viewStub = contentContainer.findViewById(R.id.viewStubHorizontal);
        viewStub.inflate();
        LinearLayout loAlertButtons = contentContainer.findViewById(R.id.loAlertButtons);


        for (int i = 0; i < mDestructive.size(); i++) {
            Log.e(TAG, "initAlertViews: ");
            //如果不是第一个按钮
            if (i != 0) {
                //添加上按钮之间的分割线
                View split = new View(mContext);
                split.setBackgroundColor(mContext.getResources().getColor(R.color.bgColor_divier));
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams((int) mContext.getResources().getDimension(R.dimen.size_divier), LinearLayout.LayoutParams.MATCH_PARENT);
                loAlertButtons.addView(split, params);
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

            if (mDestructive.size() == 1) {
                tvAlert.setTextColor(Color.RED);
            } else {
                if (i == 1) {
                    tvAlert.setTextColor(Color.RED);
                }
            }

            final int position = i;

            tvAlert.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (position == 0) {

                        iAlertCallBack.onCancelClick();
                    } else {
                        iAlertCallBack.onConfirmClick();
                    }
                    dismiss();
                }
            });

            loAlertButtons.addView(itemView, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT, 1));
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
        contentContainer.startAnimation(inAnim);
    }

    public void dismiss() {
        decorView.removeView(rootView);
        if (isDismissing) {
            return;
        }

        //消失动画
        outAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                decorView.post(new Runnable() {
                    @Override
                    public void run() {
                        //从activity根视图移除
                        decorView.removeView(rootView);
                        isDismissing = false;
                    }
                });
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        contentContainer.startAnimation(outAnim);
        isDismissing = true;
    }


}
