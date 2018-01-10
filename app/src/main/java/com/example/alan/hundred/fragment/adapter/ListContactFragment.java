package com.example.alan.hundred.fragment.adapter;

import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.widget.ExpandableListView;

import com.example.alan.hundred.R;
import com.example.alan.hundred.adapter.CustomExpandableListAdapter;
import com.example.alan.hundred.base.RxBaseFragment;
import com.example.alan.hundred.info.ContactGroup;
import com.example.alan.hundred.info.ContactItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Function :
 * @Author : Alan
 * Modify Date : 25/9/17
 * Issue : TODO
 * Whether solve :
 */

public class ListContactFragment extends RxBaseFragment {

    private static ListContactFragment listContactFragment;

    @BindView(R.id.elv_contact)
    ExpandableListView mExpandableListView;

    private CustomExpandableListAdapter adapter;

    private List<ContactGroup> contactGroupList = new ArrayList<>();
    private List<List<ContactItem>> listList = new ArrayList<>();

    private String[] contacts;


    public static ListContactFragment getInstance() {
        if (listContactFragment == null) {
            listContactFragment = new ListContactFragment();
        }
        return listContactFragment;
    }



    @Override
    public void finishCreateView(Bundle state) {
        initData();
        initEvent();
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_list_contact;
    }

    private void initData() {
        contactGroupList.clear();
        listList.clear();

        contacts = getActivity().getResources().getStringArray(R.array.contacts);

        for (int i = 0; i < contacts.length; i++) {
            ContactGroup group = new ContactGroup(contacts[i]);
            List<ContactItem> list = new ArrayList<>();
            for (int j = 0; j < 4; j++) {
                ContactItem item = new ContactItem(null, contacts[i] + j + "1--" + i, "content" + i);
                list.add(item);
            }

            listList.add(list);
            contactGroupList.add(group);
        }
    }

    private void initEvent(){

        adapter = new CustomExpandableListAdapter(contactGroupList,listList,getActivity());
        mExpandableListView.setAdapter(adapter);
        mExpandableListView.setGroupIndicator(new GradientDrawable());
        //设置默认的指示器为空
        mExpandableListView.setGroupIndicator(null);
        //默认打开第一个
        mExpandableListView.expandGroup(0);
        //默认的
      //  mExpandableListView.setGroupIndicator(this.getResources().getDrawable(R.drawable.bg_expandable));

    }
}
