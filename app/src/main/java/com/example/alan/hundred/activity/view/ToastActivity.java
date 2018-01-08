package com.example.alan.hundred.activity.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import com.example.alan.hundred.MainActivity;
import com.example.alan.hundred.R;
import com.example.alan.hundred.activity.BaseHomeActivity;
import com.example.alan.hundred.util.NotificationUtil;
import com.example.alan.hundred.view.MyToast;

/**
 * Function :
 * Author : Alan
 * Modify Date : 28/9/17
 * Issue : TODO
 * Whether solve :
 */

public class ToastActivity extends BaseHomeActivity {

    private ListView lv_search;
    private SearchView sv_toast;

    private String[] titles;
    private ArrayAdapter arrayAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int getContentView() {
        return R.layout.activity_view_toast;
    }

    @Override
    public void initViews() {

        lv_search = (ListView) findViewById(R.id.lv_search);
        sv_toast = (SearchView) findViewById(R.id.sv_toast);
    }

    @Override
    public void initData() {
        titles = new String[]{"aaa", "bbb", "ccc"};

        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, titles);
    }

    @Override
    public void initEvents() {
        lv_search.setAdapter(arrayAdapter);
        lv_search.setTextFilterEnabled(true);

        sv_toast.setIconifiedByDefault(false);
        sv_toast.setSubmitButtonEnabled(true);
        sv_toast.setQueryHint("查找");

        sv_toast.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {


                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (TextUtils.isEmpty(newText)) {
                    lv_search.clearTextFilter();
                } else {
                    lv_search.setFilterText(newText);
                }
                return true;
            }
        });
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_toast_simple:
                MyToast.showMessage("TOAST");
                break;
            case R.id.bt_toast_simple_image:
                MyToast.showImage("TOAST_IMAGE");
                break;
            case R.id.bt_notification:
                int icon = R.drawable.ic_view_toast;
                String tickerText = "new message";
                String title = "bb";
                String content = "aaaaaaaaaaaaaaaaaaa";
                Intent intent = new Intent(ToastActivity.this, TextActivity.class);
                int id = 0 ;
                int time = 3000;
                NotificationUtil.createNotification(this,icon,tickerText,title,content,intent,id,time);

                break;
        }
    }
}
