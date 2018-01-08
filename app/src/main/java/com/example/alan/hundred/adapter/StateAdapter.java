package com.example.alan.hundred.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.alan.hundred.R;
import com.example.alan.hundred.info.StateInfo;
import com.example.alan.hundred.info.Type;

import java.util.List;

/**
 * Function :
 * Author : Alan
 * Modify Date : 27/9/17
 * Issue : TODO
 * Whether solve :
 */

public class StateAdapter extends BaseAdapter {

    private LayoutInflater inflater;
    private List<StateInfo> stateInfoList;

    public StateAdapter(Context context, List<StateInfo> stateInfoList) {

        inflater = LayoutInflater.from(context);
        this.stateInfoList = stateInfoList;
    }

    @Override
    public int getCount() {
        return stateInfoList.size();
    }

    @Override
    public Object getItem(int position) {
        return stateInfoList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        int type = getItemViewType(position);

        switch (type) {
            case 0:
                if (convertView == null) {
                    convertView = inflater.inflate(R.layout.list_state_item, parent, false);
                }

                TextView tv_state_content = (TextView) convertView.findViewById(R.id.tv_list_state_name);
                tv_state_content.setText(stateInfoList.get(position).getContent());

                break;
            case 1:
                if (convertView == null) {
                    convertView = inflater.inflate(R.layout.list_state_view, parent, false);
                }
                break;
        }

        return convertView;
    }

    @Override
    public int getItemViewType(int position) {

        if (stateInfoList.get(position).getType() == Type.ICON) {
            return 0;
        } else {
            return 1;
        }

    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }
}
