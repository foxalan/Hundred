package com.example.alan.hundred.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.alan.hundred.R;
import com.example.alan.hundred.activity.animation.AnimActivity;
import com.example.alan.hundred.activity.animation.AnimationActivity;
import com.example.alan.hundred.activity.intent.IntentActivity;
import com.example.alan.hundred.activity.provider.FirstProviderActivity;
import com.example.alan.hundred.activity.storage.StorageActivity;
import com.example.alan.hundred.activity.view.SimpleViewActivity;
import com.example.alan.hundred.activity.viewgroup.CustomGroupActivity;
import com.example.alan.hundred.adapter.ChaptersAdapter;
import com.example.alan.hundred.data.ListSource;
import com.example.alan.hundred.info.ChapterInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Function :
 * Author : Alan
 * Modify Date : 22/9/17
 * Issue : TODO
 * Whether solve :
 */

public class HomeFragment extends BaseFragment {

    private static HomeFragment homeFragment;

    private GridView gv_home;
    private List<ChapterInfo> chapterInfoList = new ArrayList<>();
    private ListSource listSource;
    private ChaptersAdapter chaptersAdapter;

    public static HomeFragment getInstance() {

        if (homeFragment == null) {
            homeFragment = new HomeFragment();
        }

        return homeFragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.activity_home, container, false);

        initViews(rootView);
        initData();
        initEvent();


        return rootView;
    }

    private void initViews(View rootView) {

        gv_home = (GridView) rootView.findViewById(R.id.gv_home);
    }

    private void initData() {

        listSource = new ListSource(getActivity());
        chapterInfoList = listSource.getChapterInfoList();

        chaptersAdapter = new ChaptersAdapter(getActivity(), chapterInfoList, R.layout.grid_home_item);

        Log.d("TANG", chapterInfoList.size() + "================");
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
                }
            }
        });
    }
}
