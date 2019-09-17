package com.example.adanvace.article.activity.view;

import android.animation.ObjectAnimator;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.adanvace.R;
import com.example.adanvace.activity.view.BaseActivity;

/**
 * @author alan
 * function: todo scrollBy scrollTo
 */
public class ViewPositionActivity extends BaseActivity implements View.OnClickListener {

    private EditText mEtLeft;
    private EditText mEtRight;
    private EditText mEtTop;
    private EditText mEtBottom;

    private TextView mTvPosition;
    private View view;

    private EditText mEtX;
    private EditText mEtY;
    private TextView mTvScroll;

    @Override
    public int getContentView() {
        return R.layout.activity_article_view_position;
    }

    @Override
    public void initView() {
        mEtLeft = (EditText) findViewById(R.id.et_left);
        mEtRight = (EditText) findViewById(R.id.et_right);
        mEtTop = (EditText) findViewById(R.id.et_top);
        mEtBottom = (EditText) findViewById(R.id.et_bottom);

        mEtX = (EditText) findViewById(R.id.et_scroll_x);
        mEtY = (EditText) findViewById(R.id.et_scroll_y);

        mTvPosition = (TextView) findViewById(R.id.tv_position);
        mTvScroll = (TextView) findViewById(R.id.tv_scroll);

        view = findViewById(R.id.view_position);
//        view.setFocusable(true);

        mTvPosition.setOnClickListener(this);
        mTvScroll.setOnClickListener(this);
    }

    @Override
    public void initEvent() {

        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        break;
                    case MotionEvent.ACTION_MOVE:
                        break;
                    case MotionEvent.ACTION_UP:
                        break;
                    default:
                        break;
                }

                return false;
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_position:
                layoutPosition();
                break;
            case R.id.tv_scroll:
                scroll();
                break;
            default:
                break;
        }
    }

    private void layoutPosition() {
        int right = Integer.parseInt(mEtRight.getText().toString());
        int left = Integer.parseInt(mEtLeft.getText().toString());
        int bottom = Integer.parseInt(mEtBottom.getText().toString());
        int top = Integer.parseInt(mEtTop.getText().toString());
        view.layout(left, top, right, bottom);
    }

    private void scroll() {

       // ObjectAnimator.ofFloat(view, "translationX", 0, 100).setDuration(100).start();
        //scroll
        mTvScroll.scrollTo(20, 20);
        int scrollX = Integer.parseInt(mEtX.getText().toString());
        int scrollY = Integer.parseInt(mEtY.getText().toString());
//        view.scrollBy(50, 50);
    }
}
