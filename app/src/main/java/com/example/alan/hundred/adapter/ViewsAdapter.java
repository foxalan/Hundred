package com.example.alan.hundred.adapter;

import android.content.Context;
import android.renderscript.ScriptGroup;
import android.widget.ImageView;

import com.example.alan.hundred.R;
import com.example.alan.hundred.info.ViewInfo;

import java.util.List;

/**
 * Function :
 * Author : Alan
 * Modify Date : 22/9/17
 * Issue : TODO
 * Whether solve :
 */

public class ViewsAdapter extends CustomAdapter<ViewInfo> {

    public ViewsAdapter(Context mContext, List<ViewInfo> mData, int resID) {
        super(mContext, mData, resID);
    }

    @Override
    public void convert(CustomViewHolder viewHolder, int position) {
        viewHolder.setText(mData.get(position).getName(), R.id.tv_list_view_name);
        ImageView iv_list_view_icon = viewHolder.getView(R.id.iv_list_view_icon);
        iv_list_view_icon.setImageBitmap(mData.get(position).getIcon());
    }
}
