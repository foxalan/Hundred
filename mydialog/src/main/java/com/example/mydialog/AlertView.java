package com.example.mydialog;

import android.app.Activity;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Function :
 * Author : Alan
 * Modify Date : 18/10/17
 * Issue : TODO
 * Whether solve :
 */

public class AlertView {

    private ViewGroup decorView;//activity的根View
    private ViewGroup view_fl;
    private ViewGroup view_container;

    private View view_dialog;

    private LayoutInflater inflater;
    private Context context;
    private OnItemClickListener listener;

    private String title;
    private String content;

    private Animation outAnim;
    private Animation inAnim;

    public AlertView(Context context, OnItemClickListener listener, String title, String content) {

        inflater = LayoutInflater.from(context);
        this.context = context;
        this.listener = listener;

        this.title = title;
        this.content = content;

        initView(context);
        initAnim();
    }


    /**
     * 初始化ViewGroup
     */
    private void initView(Context context) {

        decorView = (ViewGroup) ((Activity) context).getWindow().getDecorView().findViewById(android.R.id.content);
        view_fl = (ViewGroup) inflater.inflate(R.layout.activity_container, decorView, false);
        view_container = (ViewGroup) view_fl.findViewById(R.id.fl_container);

        int margin_alert_left_right;
        margin_alert_left_right = context.getResources().getDimensionPixelSize(R.dimen.margin_alert_left_right);

        setContainerStyle(view_container, margin_alert_left_right);

        view_dialog = inflater.inflate(R.layout.dialog_body, view_container, false);

        setDialogData(view_dialog);
        setDialogBottom(view_dialog);

        view_container.addView(view_dialog);
    }

    /**
     * 初始化动画
     */
    private void initAnim() {

        inAnim = getInAnimation();
        outAnim = getOutAnimation();
    }

    public Animation getInAnimation() {
        int res = AlertAnimateUtil.getAnimationResource(true);
        return AnimationUtils.loadAnimation(context, res);
    }

    public Animation getOutAnimation() {
        int res = AlertAnimateUtil.getAnimationResource(false);
        return AnimationUtils.loadAnimation(context, res);
    }

    /**
     * 设置标题和内容
     *
     * @param view_dialog
     */
    private void setDialogData(View view_dialog) {

        TextView tv_title = (TextView) view_dialog.findViewById(R.id.tv_dialog_title);
        TextView tv_content = (TextView) view_dialog.findViewById(R.id.tv_dialog_content);

        tv_title.setText(title);
        tv_content.setText(content);
    }

    /**
     * 设置Container的Style
     *
     * @param view_container
     * @param margin_alert_left_right
     */
    private void setContainerStyle(ViewGroup view_container, int margin_alert_left_right) {
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.gravity = Gravity.CENTER;
        layoutParams.setMargins(margin_alert_left_right, 0, margin_alert_left_right, 0);
        view_container.setLayoutParams(layoutParams);

    }

    /**
     * 设置对话框下面的按钮和点击事件
     *
     * @param view_dialog
     */
    private void setDialogBottom(View view_dialog) {

        ViewStub viewStub = (ViewStub) view_dialog.findViewById(R.id.vs_hor);
        viewStub.inflate();

        LinearLayout ll_dialog_bottom = (LinearLayout) view_dialog.findViewById(R.id.ll_dialog_bottom);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(context.getResources().getDimensionPixelSize(R.dimen.size_divier), ViewGroup.LayoutParams.MATCH_PARENT);
        View viewLine = new View(context);
        viewLine.setBackgroundColor(context.getResources().getColor(R.color.bgColor_divier));


        View view_left = inflater.inflate(R.layout.layout_dialog_bottom, null);
        TextView tv_left = (TextView) view_left.findViewById(R.id.tv_button);
        tv_left.setText("取消");
        tv_left.setClickable(true);
        tv_left.setBackgroundResource(R.drawable.bg_alertbutton_left);
        tv_left.setTextColor(context.getResources().getColor(R.color.textColor_alert_button_cancel));

        ll_dialog_bottom.addView(view_left, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1));
        ll_dialog_bottom.addView(viewLine, params);

        View view_right = inflater.inflate(R.layout.layout_dialog_bottom, null);
        TextView tv_right = (TextView) view_right.findViewById(R.id.tv_button);
        tv_right.setText("确定");
        tv_right.setClickable(true);
        tv_right.setBackgroundResource(R.drawable.bg_alertbutton_left);
        tv_right.setTextColor(context.getResources().getColor(R.color.textColor_alert_button_destructive));
        ll_dialog_bottom.addView(view_right, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1));


        tv_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        tv_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                listener.onItemClick(v, 0);
                dismiss();
            }
        });

    }

    public AlertView setCancelable(boolean isCancelable) {
        View view = view_fl.findViewById(R.id.outmost_container);

        if (isCancelable) {
            view.setOnTouchListener(onCancelableTouchListener);
        } else {
            view.setOnTouchListener(null);
        }
        return this;
    }

    /**
     * Called when the user touch on black overlay in order to dismiss the dialog
     */
    private final View.OnTouchListener onCancelableTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                dismiss();
            }
            return false;
        }
    };

    /**
     * 显示Dialog
     */

    public void show() {
        decorView.addView(view_fl);
        view_container.startAnimation(inAnim);
    }

    /**
     * 让Dialog消失
     */
    private void dismiss() {
        view_container.startAnimation(outAnim);
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
                        decorView.removeView(view_fl);

                    }
                });
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

    }


}
