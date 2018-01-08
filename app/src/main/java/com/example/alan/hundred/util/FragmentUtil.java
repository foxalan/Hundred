package com.example.alan.hundred.util;



import android.app.Fragment;

import com.example.alan.hundred.fragment.BaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Function :
 * Author : Alan
 * Modify Date : 21/9/17
 * Issue : TODO
 * Whether solve :
 */

public class FragmentUtil {

    public static BaseFragment firstFragment ;

    private static List<BaseFragment> fragmentList = new ArrayList<>();

    public static void show(Fragment fragment) {

//        FragmentTransaction transaction = firstFragment.getTransaction().beginTransaction();

        if (isContain(fragment)) {

        }

//        transaction.replace(R.id.fl_container,fragment).commit();
    }

    private static boolean isContain(Fragment fragment) {
        if (fragmentList.contains(fragment)) {
            return true;
        } else {
            return false;
        }


    }

}
