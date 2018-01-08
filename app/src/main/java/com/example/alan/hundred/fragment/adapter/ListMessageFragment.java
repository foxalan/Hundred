package com.example.alan.hundred.fragment.adapter;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.example.alan.hundred.R;
import com.example.alan.hundred.adapter.UserAdapter;
import com.example.alan.hundred.fragment.BaseFragment;
import com.example.alan.hundred.info.UserInfo;
import com.example.alan.hundred.view.MyToast;
import com.yydcdut.sdlv.Menu;
import com.yydcdut.sdlv.MenuItem;
import com.yydcdut.sdlv.SlideAndDragListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Function :
 * Author : Alan
 * Modify Date : 25/9/17
 * Issue : TODO
 * Whether solve :
 */

public class ListMessageFragment extends BaseFragment {

    private static ListMessageFragment listMessageFragment;

    private static Object i = 0;


    private SlideAndDragListView slideAndDragListView;
    private UserAdapter userAdapter;
    private List<UserInfo> userInfoList = new ArrayList<>();

    public static ListMessageFragment getInstance() {
        if (listMessageFragment == null) {
            synchronized (i) {
                listMessageFragment = new ListMessageFragment();
                Bundle bundle = new Bundle();
                bundle.putString("fragmentName", "list_message");
                bundle.putInt("fragmentId", LIST_FRAGMENT_MESSAGE);
                listMessageFragment.setArguments(bundle);
            }
        }

        return listMessageFragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_list_message, container, false);

        initViews(rootView);
        initData();
        initEvents();


        return rootView;
    }

    private void initViews(View rootView) {
        slideAndDragListView = (SlideAndDragListView) rootView.findViewById(R.id.lv_slide_drag);

    }

    private void initData() {
        initListData();


        Menu menu = new Menu(true, 0);//the first parameter is whether can slide over

        menu.addItem(new MenuItem.Builder().setWidth(190)
                .setText("删除")
                .setTextSize(Color.WHITE)
                .setBackground(new ColorDrawable(Color.RED))

                .setTextSize(20)
                .setDirection(MenuItem.DIRECTION_RIGHT)//set direction (default DIRECTION_LEFT)
                .build());
        //set in sdlv

        menu.addItem(new MenuItem.Builder().setWidth(190)//set Width
                .setBackground(new ColorDrawable(Color.RED))// set background
                .setText("置顶")//set text string
                .setTextColor(Color.WHITE)//set text color
                .setBackground(new ColorDrawable(Color.GRAY))
                .setTextSize(20)//set text size
                .setDirection(MenuItem.DIRECTION_RIGHT)
                // set icon
                .build());

        slideAndDragListView.setMenu(menu);

        slideAndDragListView.setOnMenuItemClickListener(new SlideAndDragListView.OnMenuItemClickListener() {
            @Override
            public int onMenuItemClick(View v, int itemPosition, int buttonPosition, int direction) {


                switch (direction) {
                    case MenuItem.DIRECTION_LEFT:
                        switch (buttonPosition) {
                            case 0://One
                                return Menu.ITEM_SCROLL_BACK;
                        }
                        break;
                    case MenuItem.DIRECTION_RIGHT:
                        switch (buttonPosition) {
                            case 0://icon
                                MyToast.showMessage("delete");
                                return Menu.ITEM_DELETE_FROM_BOTTOM_TO_TOP;
                            case 1:
                                MyToast.showMessage("top");
                                return Menu.ITEM_DELETE_FROM_BOTTOM_TO_TOP;

                        }
                        break;
                    default:
                        return Menu.ITEM_NOTHING;
                }
                return Menu.ITEM_NOTHING;
            }
        });

        slideAndDragListView.setAdapter(userAdapter);
    }

    /**
     * 初始化List数据
     */
    private void initListData() {

        for (int i = 0; i < 10; i++) {
            UserInfo info = new UserInfo(null, "One left or forget that" + i, "clear love lee sin " + i);
            userInfoList.add(info);
        }

        userAdapter = new UserAdapter(getActivity(), userInfoList, R.layout.grid_user_item);
    }

    UserInfo mDraggedEntity;
    //  List<ApplicationInfo> mDataList;

    public void initEvents() {

        slideAndDragListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MyToast.showMessage("onItemClick " + position);
            }
        });

        slideAndDragListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                MyToast.showMessage("onLongItemClick " + position);

                return false;
            }
        });


        slideAndDragListView.setOnDragDropListener(new SlideAndDragListView.OnDragDropListener() {
            @Override
            public void onDragViewStart(int beginPosition) {
                mDraggedEntity = userInfoList.get(beginPosition);
            }

            @Override
            public void onDragDropViewMoved(int fromPosition, int toPosition) {
                UserInfo applicationInfo = userInfoList.remove(fromPosition);
                userInfoList.add(toPosition, applicationInfo);
            }

            @Override
            public void onDragViewDown(int finalPosition) {
                userInfoList.set(finalPosition, mDraggedEntity);
            }
        });
    }
}
