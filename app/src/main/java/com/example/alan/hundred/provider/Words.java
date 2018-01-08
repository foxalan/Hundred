package com.example.alan.hundred.provider;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Function :
 * Author : Alan
 * Modify Date : 16/10/17
 * Issue : TODO
 * Whether solve :
 */

public class Words {
    public static final String AUTHORITY = "org.alan.providers.first";

    public static final class Word implements BaseColumns {
        public final static String _ID = "_id";
        public final static String WORD = "word";
        public final static String DETAIL = "detail";
        public final static Uri DICT_CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/words");
        public final static Uri WORD_CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/word");
    }
}
