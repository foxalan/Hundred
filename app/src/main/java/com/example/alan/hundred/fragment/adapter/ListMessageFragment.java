package com.example.alan.hundred.fragment.adapter;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.example.alan.hundred.R;
import com.example.alan.hundred.adapter.UserAdapter;
import com.example.alan.hundred.base.RxBaseFragment;
import com.example.alan.hundred.info.UserInfo;
import com.example.alan.hundred.view.MyToast;
import com.yydcdut.sdlv.Menu;
import com.yydcdut.sdlv.MenuItem;
import com.yydcdut.sdlv.SlideAndDragListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


/**
 * Function :
 * Modify Date : 2018/1/10
 *
 * @Author : Alan
 * Issue : TODO
 * Whether Solve :
 */

public class ListMessageFragment extends RxBaseFragment {


    private static ListMessageFragment listMessageFragment;

    @BindView(R.id.lv_slide_drag)
    SlideAndDragListView slideAndDragListView;

    private UserAdapter userAdapter;
    private List<UserInfo> userInfoList = new ArrayList<>();

    public static ListMessageFragment getInstance() {
        if (listMessageFragment == null) {
            listMessageFragment = new ListMessageFragment();
        }
        return listMessageFragment;
    }


    private void initData() {
        initListData();

        Menu menu = new Menu(true, 0);

        menu.addItem(new MenuItem.Builder().setWidth(190)
                .setText("删除")
                .setTextSize(Color.WHITE)
                .setBackground(new ColorDrawable(Color.RED))
                .setTextSize(20)
                .setDirection(MenuItem.DIRECTION_RIGHT)
                .build());

        menu.addItem(new MenuItem.Builder().setWidth(190)
                .setBackground(new ColorDrawable(Color.RED))
                .setText("置顶")
                .setTextColor(Color.WHITE)
                .setBackground(new ColorDrawable(Color.GRAY))
                .setTextSize(20)
                .setDirection(MenuItem.DIRECTION_RIGHT)
                .build());

        slideAndDragListView.setMenu(menu);

        slideAndDragListView.setOnMenuItemClickListener(new SlideAndDragListView.OnMenuItemClickListener() {
            @Override
            public int onMenuItemClick(View v, int itemPosition, int buttonPosition, int direction) {

                switch (direction) {
                    case MenuItem.DIRECTION_LEFT:
                        switch (buttonPosition) {
                            case 0:
                                return Menu.ITEM_SCROLL_BACK;
                            default:
                                break;
                        }
                        break;
                    case MenuItem.DIRECTION_RIGHT:
                        switch (buttonPosition) {
                            case 0:
                                MyToast.showMessage("delete");
                                return Menu.ITEM_DELETE_FROM_BOTTOM_TO_TOP;
                            case 1:
                                MyToast.showMessage("top");
                                return Menu.ITEM_DELETE_FROM_BOTTOM_TO_TOP;
                            default:
                                break;

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


    @Override
    public void finishCreateView(Bundle state) {

        initData();
        initEvents();
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_list_message;
    }
}
