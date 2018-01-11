package com.example.alan.hundred.activity.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.alan.hundred.R;
import com.example.alan.hundred.activity.BaseActivity;
import com.example.alan.hundred.adapter.ViewsAdapter;
import com.example.alan.hundred.data.ListSource;
import com.example.alan.hundred.info.ViewInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Function :
 * @Author : Alan
 * Modify Date : 15/9/17
 * Issue : TODO
 * Whether solve :
 */

public class SimpleViewActivity extends BaseActivity {

    private ListView lv_simple_view;

    private List<ViewInfo> viewInfoList = new ArrayList<>();
    private ListSource listSource;

    private ViewsAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public int getContentView() {
        return R.layout.activity_simple_view;
    }

    @Override
    public void initViews() {

        lv_simple_view = (ListView) findViewById(R.id.lv_simple_view);

    }

    @Override
    public void initData() {
        listSource = new ListSource(this);
        viewInfoList = listSource.getViewInfoList();

        adapter = new ViewsAdapter(this, viewInfoList, R.layout.grid_view_item);
    }

    @Override
    public void initEvents() {
        lv_simple_view.setAdapter(adapter);

        lv_simple_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        startActivity(new Intent(SimpleViewActivity.this, TextActivity.class));
                        break;
                    case 1:
                        startActivity(new Intent(SimpleViewActivity.this, EditActivity.class));
                        break;
                    case 2:
                        startActivity(new Intent(SimpleViewActivity.this, ButtonActivity.class));
                        break;
                    case 3:
                        startActivity(new Intent(SimpleViewActivity.this, AdapterActivity.class));
                        break;
                    case 4:
                        startActivity(new Intent(SimpleViewActivity.this, PageActivity.class));
                        break;
                    case 5:
                        startActivity(new Intent(SimpleViewActivity.this, ProgressActivity.class));
                        break;
                    case 6:
                        startActivity(new Intent(SimpleViewActivity.this, ToastActivity.class));
                        break;
                    case 7:
                        startActivity(new Intent(SimpleViewActivity.this, DialogActivity.class));
                        break;
                    default:
                        break;

                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
