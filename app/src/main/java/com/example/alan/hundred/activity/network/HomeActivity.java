package com.example.alan.hundred.activity.network;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.alan.hundred.R;
import com.example.alan.hundred.activity.BaseActivity;
import com.example.alan.hundred.adapter.ChaptersAdapter;
import com.example.alan.hundred.data.ListSource;
import com.example.alan.hundred.info.ChapterInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Function :
 * Author : Alan
 * Modify Date : 15/9/17
 * Issue : TODO
 * Whether solve :
 */

public class HomeActivity extends BaseActivity {

    private GridView gv_home;
    private List<ChapterInfo> chapterInfoList = new ArrayList<>();
    private ListSource listSource;
    private ChaptersAdapter chaptersAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int getContentView() {
        return R.layout.activity_home;
    }

    @Override
    public void initViews() {
        gv_home = (GridView) findViewById(R.id.gv_home);
    }

    @Override
    public void initData() {
        listSource = new ListSource(this);
        chapterInfoList = listSource.getChapterInfoList();

        chaptersAdapter = new ChaptersAdapter(this,chapterInfoList,R.layout.grid_home_item);
    }

    @Override
    public void initEvents() {
        gv_home.setAdapter(chaptersAdapter);

        gv_home.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:

                        break;
                    case 1:

                        break;
                    case 2:

                        break;
                    case 3:

                        break;
                    case 4:

                        break;
                    case 5:

                        break;
                    case 6:

                        break;
                    case 7:

                        break;
                    case 8:

                        break;
                }
            }
        });
    }
}
