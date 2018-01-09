package com.example.alan.hundred.fragment.login;

import android.os.Bundle;

import com.example.alan.hundred.R;
import com.example.alan.hundred.base.RxBaseFragment;

/**
 * Function :
 * Modify Date : 2018/1/9
 *
 * @Author : Alan
 * Issue : TODO
 * Whether Solve :
 */

public class LoginOutsideFragment extends RxBaseFragment {


    private static LoginOutsideFragment mLoginOutsideFragment;

    public static LoginOutsideFragment getInstance() {
        if (mLoginOutsideFragment == null) {
            mLoginOutsideFragment = new LoginOutsideFragment();
        }
        return mLoginOutsideFragment;
    }


    @Override
    public void finishCreateView(Bundle state) {

    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_login;
    }
}
