package com.example.alan.hundred.fragment.adapter;

import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.example.alan.hundred.R;
import com.example.alan.hundred.adapter.CustomExpandableListAdapter;
import com.example.alan.hundred.fragment.BaseFragment;
import com.example.alan.hundred.info.ContactGroup;
import com.example.alan.hundred.info.ContactItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Function :
 * Author : Alan
 * Modify Date : 25/9/17
 * Issue : TODO
 * Whether solve :
 */

public class ListContactFragment extends BaseFragment {

    private static ListContactFragment listContactFragment;

    private static Object i = 0;

    private ExpandableListView elv_contact;
    private CustomExpandableListAdapter adapter;

    private List<ContactGroup> contactGroupList = new ArrayList<>();
    private List<List<ContactItem>> listList = new ArrayList<>();

    private String[] contacts;


    public static ListContactFragment getInstance() {
        if (listContactFragment == null) {
            synchronized (i) {
                listContactFragment = new ListContactFragment();
                Bundle bundle = new Bundle();
                bundle.putString("fragmentName", "list_contact");
                bundle.putInt("fragmentId", LIST_FRAGMENT_CONTACT);
                listContactFragment.setArguments(bundle);
            }
        }

        return listContactFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_list_contact, container, false);

        initViews(rootView);
        initData();
        initEvent();

        return rootView;
    }

    private void initViews(View rootView) {
        elv_contact = (ExpandableListView) rootView.findViewById(R.id.elv_contact);
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
        elv_contact.setAdapter(adapter);
        elv_contact.setGroupIndicator(new GradientDrawable());

        elv_contact.setGroupIndicator(this.getResources().getDrawable(R.drawable.bg_expandable));

    }
}
