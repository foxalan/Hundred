package com.example.alan.hundred.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.alan.hundred.R;
import com.example.alan.hundred.activity.animation.AnimActivity;
import com.example.alan.hundred.activity.intent.IntentActivity;
import com.example.alan.hundred.activity.provider.FirstProviderActivity;
import com.example.alan.hundred.activity.storage.StorageActivity;
import com.example.alan.hundred.activity.view.SimpleViewActivity;
import com.example.alan.hundred.activity.viewgroup.CustomGroupActivity;
import com.example.alan.hundred.adapter.ChaptersAdapter;
import com.example.alan.hundred.base.RxBaseFragment;
import com.example.alan.hundred.data.ListSource;
import com.example.alan.hundred.info.ChapterInfo;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Function :
 * @Author : Alan
 * Modify Date : 22/9/17
 * Issue : TODO
 * Whether solve :
 */

public class HomeFragment extends RxBaseFragment {

    private static HomeFragment homeFragment;

    @BindView(R.id.gv_home)
    GridView gv_home;

    private List<ChapterInfo> chapterInfoList = new ArrayList<>();
    private ListSource listSource;
    private ChaptersAdapter chaptersAdapter;

    public static HomeFragment getInstance() {
        if (homeFragment == null) {
            homeFragment = new HomeFragment();
        }
        return homeFragment;
    }


    @Override
    public void finishCreateView(Bundle state) {
        initData();
        initEvent();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_home;
    }


    private void initData() {
        listSource = new ListSource(getActivity());
        chapterInfoList = listSource.getChapterInfoList();
        chaptersAdapter = new ChaptersAdapter(getActivity(), chapterInfoList, R.layout.grid_home_item);
    }

    private void initEvent() {

        gv_home.setAdapter(chaptersAdapter);
        gv_home.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        startActivity(new Intent(getActivity(), SimpleViewActivity.class));
                        break;
                    case 1:
                        startActivity(new Intent(getActivity(), CustomGroupActivity.class));
                        break;
                    case 2:
                        startActivity(new Intent(getActivity(), IntentActivity.class));
                        break;
                    case 3:
                        startActivity(new Intent(getActivity(), StorageActivity.class));
                        break;
                    case 4:
                        startActivity(new Intent(getActivity(), AnimActivity.class));
                        break;
                    case 5:
                        startActivity(new Intent(getActivity(), FirstProviderActivity.class));
                        break;
                    case 6:

                        break;
                    case 7:

                        break;
                    case 8:

                        break;
                    default:
                        break;
                }
            }
        });
    }
}
