package com.example.adanvace.util;

import android.util.Log;

/**
 * Function Name : SetAudioMute
 * Author : Eddie
 * Modify Date :
 * Input Parameter &
 */

public class L {

    private static String name = "TANG";

    private static String VIEW_DISTRI = "view_distribution";

    public static void d(String str) {
        Log.d(name, str);
    }

    public static void e(String str) {
        Log.e(name, str);
    }

    public static void e(String tag, String str) {
        Log.e(tag, str);
    }

    public static void distribution(String value) {
        L.e(VIEW_DISTRI, value);
    }
}
