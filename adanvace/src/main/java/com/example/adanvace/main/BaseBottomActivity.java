package com.example.adanvace.main;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.adanvace.R;
import com.example.adanvace.activity.view.BaseActivity;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author alan
 * function:
 */
public abstract class BaseBottomActivity extends BaseActivity implements View.OnClickListener {

    private final ArrayList<PageItemBean> TAB_BEANS = new ArrayList<>();
    private final ArrayList<BaseItemFragment> ITEM_DELEGATES = new ArrayList<>();
    private final LinkedHashMap<PageItemBean, BaseItemFragment> ITEMS = new LinkedHashMap<>();
    private int mIndexDelegate = 0;
    private int mClickedColor = Color.RED;

    private LinearLayoutCompat mBottomBar = null;

    public abstract LinkedHashMap<PageItemBean, BaseItemFragment> setItems(ItemBuilder builder);

    public ArrayList<BaseItemFragment> getItemDelegates() {
        return ITEM_DELEGATES;
    }

    private BaseItemFragment lastFragment ;

    @Override
    public int getContentView() {
        return R.layout.delegate_bottom;
    }


    public abstract int setIndexDelegate();

    @ColorInt
    public abstract int setClickedColor();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mIndexDelegate = setIndexDelegate();
        if (setClickedColor() != 0) {
            mClickedColor = setClickedColor();
        }

        final ItemBuilder builder = ItemBuilder.builder();
        final LinkedHashMap<PageItemBean, BaseItemFragment> items = setItems(builder);
        ITEMS.putAll(items);
        for (Map.Entry<PageItemBean, BaseItemFragment> item : ITEMS.entrySet()) {
            final PageItemBean key = item.getKey();
            final BaseItemFragment value = item.getValue();
            TAB_BEANS.add(key);
            ITEM_DELEGATES.add(value);
        }
    }


    @Override
    public void initView() {
        onBindView();
    }

    public void onBindView() {
        mBottomBar = (LinearLayoutCompat) findViewById(R.id.bottom_bar);
        final int size = ITEMS.size();
        for (int i = 0; i < size; i++) {
            LayoutInflater.from(this).inflate(R.layout.bottom_item_icon_text_layout, mBottomBar);
            final RelativeLayout item = (RelativeLayout) mBottomBar.getChildAt(i);
            //设置每个item的点击事件
            item.setTag(i);
            item.setOnClickListener(this);
            final ImageView itemIcon = (ImageView) item.getChildAt(0);
            final AppCompatTextView itemTitle = (AppCompatTextView) item.getChildAt(1);
            final PageItemBean bean = TAB_BEANS.get(i);
            //初始化数据
            itemIcon.setImageResource(bean.getResIdNormal());
            itemTitle.setText(bean.getTitle());
            if (i == mIndexDelegate) {
                itemIcon.setImageResource(bean.getResIdClicked());
                itemTitle.setTextColor(mClickedColor);
            }
        }
        lastFragment = ITEM_DELEGATES.get(setIndexDelegate());
//        getSupportFragmentManager().beginTransaction().
//        final ISupportFragment[] delegateArray = ITEM_DELEGATES.toArray(new ISupportFragment[size]);
//        getSupportDelegate().loadMultipleRootFragment(R.id.bottom_bar_delegate_container, mIndexDelegate, delegateArray);
    }

    @SuppressLint("RestrictedApi")
    private void triggerFragment(BaseItemFragment currentFragment) {

        if (currentFragment == lastFragment) {
            return;
        }
        FragmentManager fragmentManager = this.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if (!fragmentManager.getFragments().contains(currentFragment)) {
            if (lastFragment == null) {
                fragmentTransaction.add(R.id.bottom_bar_delegate_container, currentFragment).
                        commit();
            } else {
                fragmentTransaction.add(R.id.bottom_bar_delegate_container, currentFragment)
                        .hide(lastFragment).commit();
            }
        } else {
            fragmentTransaction.show(currentFragment)
                    .hide(lastFragment)
                    .commit();
        }
    }


    private void resetColor() {
        final int count = mBottomBar.getChildCount();
        for (int i = 0; i < count; i++) {
            final RelativeLayout item = (RelativeLayout) mBottomBar.getChildAt(i);
            final ImageView itemIcon = (ImageView) item.getChildAt(0);
//            itemIcon.setTextColor(Color.GRAY);
            final AppCompatTextView itemTitle = (AppCompatTextView) item.getChildAt(1);
            itemTitle.setTextColor(Color.GRAY);
        }
    }

    public void changeColor(int tabIndex) {
        resetColor();
        final RelativeLayout item = (RelativeLayout) mBottomBar.getChildAt(tabIndex);
        final ImageView itemIcon = (ImageView) item.getChildAt(0);
//        itemIcon.setTextColor(mClickedColor);
        final AppCompatTextView itemTitle = (AppCompatTextView) item.getChildAt(1);
        itemTitle.setTextColor(mClickedColor);
    }

    @Override
    public void onClick(View v) {
        final int tabIndex = (int) v.getTag();
        changeColor(tabIndex);
        triggerFragment(ITEM_DELEGATES.get(tabIndex));
        //注意先后顺序
        lastFragment = ITEM_DELEGATES.get(tabIndex);
    }
}
