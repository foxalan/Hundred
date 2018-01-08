package com.example.alan.hundred.activity.view;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.example.alan.hundred.R;
import com.example.alan.hundred.activity.BaseHomeActivity;

import com.example.alan.hundred.fragment.adapter.ListContactFragment;

import com.example.alan.hundred.fragment.adapter.ListStateFragment;

/**
 * Function :
 * @Author : Alan
 * Modify Date : 25/9/17
 * Issue : TODO
 * Whether solve :
 */

public class AdapterActivity extends BaseHomeActivity {


    private ListContactFragment contactFragment;
    private ListStateFragment stateFragment;

    private LinearLayout ll_adapter_message;
    private LinearLayout ll_adapter_contact;
    private LinearLayout ll_adapter_state;

    private ImageView iv_adapter_message;
    private ImageView iv_adapter_contact;
    private ImageView iv_adapter_state;

    private TextView tv_adapter_message;
    private TextView tv_adapter_contact;
    private TextView tv_adapter_state;

    private ButtonClick buttonClick;

    private int color_normal = 0xFF8a8a8a;
    private int color_pressed = 0xFF81C9E1;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int getContentView() {
        return R.layout.activity_view_adapter;
    }

    @Override
    public void initViews() {

        ll_adapter_message = (LinearLayout) findViewById(R.id.ll_adapter_message);
        ll_adapter_contact = (LinearLayout) findViewById(R.id.ll_adapter_contact);
        ll_adapter_state = (LinearLayout) findViewById(R.id.ll_adapter_state);

        iv_adapter_message = (ImageView) findViewById(R.id.iv_adapter_message);
        iv_adapter_contact = (ImageView) findViewById(R.id.iv_adapter_contact);
        iv_adapter_state = (ImageView) findViewById(R.id.iv_adapter_state);

        tv_adapter_message = (TextView) findViewById(R.id.tv_adapter_message);
        tv_adapter_contact = (TextView) findViewById(R.id.tv_adapter_contact);
        tv_adapter_state = (TextView) findViewById(R.id.tv_adapter_state);


        contactFragment = ListContactFragment.getInstance();
        stateFragment = ListStateFragment.getInstance();

        getFragmentManager().beginTransaction().replace(R.id.fl_adapter_container, contactFragment).commit();
    }

    @Override
    public void initData() {
        setBackGround(0);
    }

    @Override
    public void initEvents() {

        buttonClick = new ButtonClick();

        ll_adapter_message.setOnClickListener(buttonClick);
        ll_adapter_contact.setOnClickListener(buttonClick);
        ll_adapter_state.setOnClickListener(buttonClick);
    }


    private class ButtonClick implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.ll_adapter_message:
                    //     getFragmentManager().beginTransaction().replace(R.id.fl_adapter_container, messageFragment).commit();

                    setBackGround(0);
                    break;
                case R.id.ll_adapter_contact:
                    getFragmentManager().beginTransaction().replace(R.id.fl_adapter_container, contactFragment).commit();

                    setBackGround(1);
                    break;
                case R.id.ll_adapter_state:
                    getFragmentManager().beginTransaction().replace(R.id.fl_adapter_container, stateFragment).commit();

                    setBackGround(2);
                    break;
                default:
                    break;
            }
        }
    }

    private void setBackGround(int position) {
        setAllItemGray();
        switch (position) {
            case 0:
                iv_adapter_message.setImageResource(R.drawable.iv_message_pressed);
                tv_adapter_message.setTextColor(color_pressed);
                break;
            case 1:
                iv_adapter_contact.setImageResource(R.drawable.iv_contact_pressed);
                tv_adapter_contact.setTextColor(color_pressed);
                break;
            case 2:
                iv_adapter_state.setImageResource(R.drawable.iv_state_pressed);
                tv_adapter_state.setTextColor(color_pressed);
                break;
            default:
                break;

        }
    }

    /**
     * 设置所有颜色为Gray
     */
    private void setAllItemGray() {

        iv_adapter_message.setImageResource(R.drawable.iv_message_normal);
        tv_adapter_message.setTextColor(color_normal);

        iv_adapter_contact.setImageResource(R.drawable.iv_contact_normal);
        tv_adapter_contact.setTextColor(color_normal);

        iv_adapter_state.setImageResource(R.drawable.iv_state_normal);
        tv_adapter_state.setTextColor(color_normal);
    }
}
