package com.example.alan.hundred.adapter;

import android.content.Context;

import com.example.alan.hundred.R;
import com.example.alan.hundred.info.FlipperInfo;

import java.util.List;

/**
 * Function :
 * Author : Alan
 * Modify Date : 27/9/17
 * Issue : TODO
 * Whether solve :
 */

public class FlipperAdapter extends CustomAdapter<FlipperInfo> {
    public FlipperAdapter(Context mContext, List<FlipperInfo> mData, int resID) {
        super(mContext, mData, resID);
    }

    @Override
    public void convert(CustomViewHolder viewHolder, int position) {
        viewHolder.setText(mData.get(position).getContext(), R.id.tv_flipper_item);
        viewHolder.setBitmap(mData.get(position).getBitmap(),R.id.iv_flipper_item);
    }
}
