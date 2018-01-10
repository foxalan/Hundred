package com.example.alan.hundred.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.alan.hundred.R;
import com.example.alan.hundred.info.ContactGroup;
import com.example.alan.hundred.info.ContactItem;

import java.util.List;

/**
 * Function :
 * @Author : Alan
 * Modify Date : 25/9/17
 * Issue : TODO
 * Whether solve :
 */

public class CustomExpandableListAdapter extends BaseExpandableListAdapter {

    private List<ContactGroup> contactGroupList;
    private List<List<ContactItem>> listList;
    private LayoutInflater inflater;

    public CustomExpandableListAdapter(List<ContactGroup> contactGroupList, List<List<ContactItem>> listList, Context context) {
        this.contactGroupList = contactGroupList;
        this.listList = listList;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getGroupCount() {
        return contactGroupList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return listList.get(groupPosition).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return contactGroupList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return listList.get(groupPosition).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.grid_contact_group_item, parent, false);
        }

        TextView tv_group =  convertView.findViewById(R.id.tv_group);
        ImageView iv_group =  convertView.findViewById(R.id.iv_group);

        tv_group.setText(contactGroupList.get(groupPosition).getName());
        if (!isExpanded) {
            iv_group.setImageResource(R.drawable.iv_expandable_right);
        } else {
            iv_group.setImageResource(R.drawable.iv_expandable_down);
        }

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.grid_contact_item, parent, false);
        }

        TextView tv_list_contact_name = convertView.findViewById(R.id.tv_list_contact_name);
        TextView tv_list_contact_content = convertView.findViewById(R.id.tv_list_contact_content);

        Log.d("TANG", listList.size() + "===" + listList.get(1).get(1).toString());
        tv_list_contact_name.setText(listList.get(groupPosition).get(childPosition).getName());
        tv_list_contact_content.setText(listList.get(groupPosition).get(childPosition).getContent());

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
