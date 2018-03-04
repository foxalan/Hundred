package com.example.point.util;

import android.util.Log;

/**
 * Created by alan on 2018/3/4.
 */

public class L {

    private static String tag = "tang";

    public static void d(String str) {
        d(tag, str);
    }

    private static void d(String tag, String str) {
        Log.d(tag, str);
    }
}
