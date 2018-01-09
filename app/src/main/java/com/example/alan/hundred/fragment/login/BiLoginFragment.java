package com.example.alan.hundred.fragment.login;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.alan.hundred.R;
import com.example.alan.hundred.base.RxBaseFragment;
import com.example.alan.hundred.util.ToastUtil;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Function :
 * Modify Date : 2018/1/9
 *
 * @Author : Alan
 * Issue : TODO
 * Whether Solve :
 */

public class BiLoginFragment extends RxBaseFragment {

    private static BiLoginFragment mBiLoginFragment;

    @BindView(R.id.iv_icon_left)
    ImageView mImageViewLeft;
    @BindView(R.id.iv_icon_right)
    ImageView mImageViewRight;
    @BindView(R.id.et_username)
    EditText mEditUserName;
    @BindView(R.id.et_password)
    EditText mEditPassword;
    @BindView(R.id.delete_username)
    ImageButton mImageButton;

    @OnClick({R.id.btn_login})
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                login();
                break;
            default:
                break;
        }
    }

    public static BiLoginFragment getInstance() {
        if (mBiLoginFragment == null) {
            mBiLoginFragment = new BiLoginFragment();
        }

        return mBiLoginFragment;
    }

    @Override
    public void finishCreateView(Bundle state) {

        mEditPassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    mImageViewLeft.setImageResource(R.drawable.ic_22_hide);
                    mImageViewRight.setImageResource(R.drawable.ic_33_hide);
                } else {

                }
            }
        });

        mEditUserName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    mImageButton.setVisibility(View.VISIBLE);
                    mImageViewLeft.setImageResource(R.drawable.ic_22);
                    mImageViewRight.setImageResource(R.drawable.ic_33);
                } else {
                    if (mImageButton != null) {

                        mImageButton.setVisibility(View.INVISIBLE);
                    }
                }
            }
        });
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_login_bi;
    }

    private void login() {
        String name = mEditUserName.getText().toString();
        String password = mEditPassword.getText().toString();
        if (TextUtils.isEmpty(name)) {
            ToastUtil.showShort(getActivity(), "用户名不能为空");
            return;
        }
        if (TextUtils.isEmpty(password)) {
            ToastUtil.showShort(getActivity(), "密码不能为空");
            return;
        }
    }
}
