package com.example.alan.hundred.adapter;

import android.content.Context;

import com.example.alan.hundred.R;
import com.example.alan.hundred.info.ChapterInfo;

import java.util.List;

/**
 * Function : 章节的
 * Author : Alan
 * Modify Date : 15/9/17
 * Issue : TODO
 * Whether solve :
 */

public class ChaptersAdapter extends CustomAdapter<ChapterInfo> {

    public ChaptersAdapter(Context mContext, List<ChapterInfo> mData, int resID) {
        super(mContext, mData, resID);
    }

    @Override
    public void convert(CustomViewHolder viewHolder, int position) {
        viewHolder.setText(mData.get(position).getName(), R.id.tv_grid_chapter_item);
    }
}
