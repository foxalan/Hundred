package com.example.alan.hundred.data;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.alan.hundred.R;
import com.example.alan.hundred.info.ChapterInfo;
import com.example.alan.hundred.info.FlipperInfo;
import com.example.alan.hundred.info.StateInfo;
import com.example.alan.hundred.info.Type;
import com.example.alan.hundred.info.ViewInfo;


import java.util.ArrayList;
import java.util.List;

/**
 * Function Name : 用于存放固定的listview的数据
 * Author : Alan
 * Modify Date : 22/7/17
 * Input Parameter: new ArrayList<>() 需要理解
 */

public class ListSource {

    private List<ChapterInfo> chapterInfoList;

    private List<ViewInfo> viewInfoList;

    private Context context;

    private String[] chapters;
    private String[] views;

    private List<Bitmap> bitmapList = new ArrayList<>();

    private List<StateInfo> stateInfoList = new ArrayList<>();
    private String[] states;

    public ListSource(Context context) {
        this.context = context;
    }

    public List<ChapterInfo> getChapterInfoList() {
        initChapterList();
        return new ArrayList<>(chapterInfoList);
    }

    /**
     * 初始化Chapter数据
     */
    private void initChapterList() {

        chapterInfoList = new ArrayList<>();

        chapters = context.getResources().getStringArray(R.array.chapters);

        for (int i = 0; i < chapters.length; i++) {
            ChapterInfo info = new ChapterInfo(chapters[i], null, "");
            chapterInfoList.add(info);
        }
    }

    public List<ViewInfo> getViewInfoList() {

        initViewList();

        return new ArrayList<>(viewInfoList);
    }

    /**
     * 初始化View数据
     */
    private void initViewList() {

        viewInfoList = new ArrayList<>();
        views = context.getResources().getStringArray(R.array.views);

        Bitmap bitmap_text = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_view_text);
        Bitmap bitmap_edit = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_view_edit_text);
        Bitmap bitmap_button = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_view_button);
        Bitmap bitmap_adapter = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_view_adapter);
        Bitmap bitmap_pager = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_view_page);
        Bitmap bitmap_progress = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_view_progress);
        Bitmap bitmap_switcher = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_view_switcher);
        Bitmap bitmap_toast = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_view_toast);
        Bitmap bitmap_dialog = BitmapFactory.decodeResource(context.getResources(),R.drawable.ic_view_dialog);

        bitmapList.add(bitmap_text);
        bitmapList.add(bitmap_edit);
        bitmapList.add(bitmap_button);
        bitmapList.add(bitmap_adapter);
        bitmapList.add(bitmap_pager);
        bitmapList.add(bitmap_progress);
        bitmapList.add(bitmap_switcher);
        bitmapList.add(bitmap_toast);
        bitmapList.add(bitmap_dialog);

        for (int i = 0; i < views.length; i++) {
            ViewInfo info = new ViewInfo(views[i], bitmapList.get(i));
            viewInfoList.add(info);
        }

    }

    public List<StateInfo> getStateInfoList() {

        initStateList();

        return new ArrayList<>(stateInfoList);
    }

    /**
     * 初始化State数据
     */
    private void initStateList() {

        states = context.getResources().getStringArray(R.array.states);


        for (int i = 0; i < (states.length); i++) {

            Type icon = Type.ICON;
            Type view = Type.NULL;

            StateInfo info;
            if (i == 0 || i == 2 || i == 7) {
                info = new StateInfo(null, states[i], view);
            } else {
                info = new StateInfo(null, states[i], icon);
            }

            stateInfoList.add(info);
        }

    }


    private List<FlipperInfo> flipperInfoList = new ArrayList<>();

    public List<FlipperInfo> getFlipperInfoList() {
        initFlipperList();

        return new ArrayList<>(flipperInfoList);
    }


    private void initFlipperList() {
        String[] flipper = context.getResources().getStringArray(R.array.flipper);

        Bitmap bitmap1 = BitmapFactory.decodeResource(context.getResources(), R.drawable.guide_concise);
        Bitmap bitmap2 = BitmapFactory.decodeResource(context.getResources(), R.drawable.guide_smart);
        Bitmap bitmap3 = BitmapFactory.decodeResource(context.getResources(), R.drawable.guide_strong);

        List<Bitmap> list = new ArrayList<>();
        list.add(bitmap1);
        list.add(bitmap2);
        list.add(bitmap3);

        for (int i = 0; i < flipper.length; i++) {
            FlipperInfo info = new FlipperInfo(list.get(i), flipper[i]);
            flipperInfoList.add(info);
        }
    }


}
