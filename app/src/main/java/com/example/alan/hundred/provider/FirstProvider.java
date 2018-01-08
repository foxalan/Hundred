package com.example.alan.hundred.provider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.alan.hundred.database.SQLHelper;
import com.example.alan.hundred.util.L;

/**
 * Function :
 * Author : Alan
 * Modify Date : 16/10/17
 * Issue :
 * Whether solve :
 * 1.为UriMatcher注册两个Uri
 * 2.返回指定Uri参数对应的数据的Mime类型
 * 3.借助数据库工具完成操作
 */

public class FirstProvider extends ContentProvider {

    private static UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    private static final int WORDS = 1;
    private static final int WORD = 2;

    static {
        uriMatcher.addURI(Words.AUTHORITY, "words", WORDS);
        uriMatcher.addURI(Words.AUTHORITY, "word/#", WORD);
    }

    @Override
    public boolean onCreate() {

        L.e("first provider onCreate");
        sqlHelper = new SQLHelper(this.getContext());
        return true;
    }


    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        L.e("first provider getType");
        switch (uriMatcher.match(uri)) {
            case WORDS:
                return "vnd.android.cursor.dir/org.alan.dict";
            case WORD:
                return "vnd.android.cursor.item/org.alan.dict";
            default:
                throw new IllegalArgumentException("未知Uri:" + uri);
        }

    }


    private SQLHelper sqlHelper;

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        L.e("first provider insert");
        SQLiteDatabase database = sqlHelper.getWritableDatabase();
        switch (uriMatcher.match(uri)) {
            case WORDS:
                long rowId = database.insert("tb_dict", Words.Word._ID, values);
                if (rowId > 0) {
                    Uri wordUri = ContentUris.withAppendedId(uri, rowId);
                    getContext().getContentResolver().notifyChange(wordUri, null);
                    return wordUri;
                }
                break;
        }

        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        L.e("first provider delete");

        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        L.e("first provider update");
        return 0;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        L.e("first provider query");
        SQLiteDatabase database = sqlHelper.getReadableDatabase();
        switch (uriMatcher.match(uri)){
            case WORDS:

                return database.query("tb_dict",projection,selection,selectionArgs,null,null,sortOrder);
            case WORD:

        }

        return null;
    }
}
