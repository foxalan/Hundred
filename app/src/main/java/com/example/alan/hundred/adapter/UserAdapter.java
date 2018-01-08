package com.example.alan.hundred.adapter;

import android.content.Context;

import com.example.alan.hundred.R;
import com.example.alan.hundred.info.UserInfo;

import java.util.List;

/**
 * Function :
 * Author : Alan
 * Modify Date : 25/9/17
 * Issue : TODO
 * Whether solve :
 */

public class UserAdapter extends CustomAdapter<UserInfo> {

    public UserAdapter(Context mContext, List<UserInfo> mData, int resID) {
        super(mContext, mData, resID);
    }


    @Override
    public void convert(CustomViewHolder viewHolder, int position) {

        viewHolder.setText(mData.get(position).getTitle(), R.id.tv_list_user_title);
        viewHolder.setText(mData.get(position).getContent(), R.id.tv_list_user_content);

    }


}
