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

public class RegisterFragment extends RxBaseFragment {

    private static RegisterFragment mRegisterFragment;

    public static RegisterFragment getInstance() {
        if (mRegisterFragment == null) {
            mRegisterFragment = new RegisterFragment();
        }

        return mRegisterFragment;
    }

    @Override
    public void finishCreateView(Bundle state) {

    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_register;
    }
}
