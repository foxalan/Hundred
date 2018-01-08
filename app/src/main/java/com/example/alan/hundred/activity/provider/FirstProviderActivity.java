package com.example.alan.hundred.activity.provider;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.example.alan.hundred.R;
import com.example.alan.hundred.activity.BaseHomeActivity;
import com.example.alan.hundred.provider.Words;

/**
 * Function :
 * Author : Alan
 * Modify Date : 16/10/17
 * Issue : TODO
 * Whether solve :
 */

public class FirstProviderActivity extends BaseHomeActivity {

    private String uri_first = "org.alan.providers.first";
    private Uri uri;
    private ContentResolver contentResolver;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int getContentView() {
        return R.layout.activity_provider;
    }

    @Override
    public void initViews() {

    }

    @Override
    public void initData() {
        contentResolver = getContentResolver();
        uri = Uri.parse(uri_first);

    }

    @Override
    public void initEvents() {

    }


    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_provider_add:
                // Cursor cursor = contentResolver.query(uri, null, null, null, null);
                ContentValues contentValues = new ContentValues();
                contentValues.put(Words.Word.WORD, "edg shit");
                contentValues.put(Words.Word.DETAIL, "detail");
                contentResolver.insert(Words.Word.DICT_CONTENT_URI, contentValues);
                break;
            case R.id.bt_provider_delete:

                break;
            case R.id.bt_provider_search:

                break;
            case R.id.bt_provider_replace:

                break;
        }
    }
}
