package com.example.adanvace;

import android.Manifest;
import android.content.Intent;
import android.view.View;

import com.example.adanvace.activity.animation.AnimationActivity;
import com.example.adanvace.activity.animation.AnimatorActivity;
import com.example.adanvace.activity.permission.JumpPermissionManagement;
import com.example.adanvace.activity.permission.PermissionCallback;
import com.example.adanvace.activity.permission.RequestPermission;
import com.example.adanvace.activity.view.ContactActivity;
import com.example.adanvace.activity.view.CustomActivity;
import com.example.adanvace.activity.view.LatteActivity;
import com.example.adanvace.activity.view.LotteryActivity;
import com.example.adanvace.activity.view.RadiusActivity;
import com.example.adanvace.activity.view.RefreshListViewActivity;
import com.example.adanvace.activity.view.ScrollViewActivity;
import com.example.adanvace.activity.view.SoundActivity;
import com.example.adanvace.activity.view.VerticalActivity;
import com.example.adanvace.activity.view.ViewPagerActivity;
import com.example.adanvace.activity.view.VolleyActivity;
import com.example.adanvace.activity.viewgroup.AdapterActivity;
import com.example.adanvace.activity.viewgroup.FlowViewGroupActivity;
import com.example.adanvace.activity.viewgroup.HorizontalScrollViewActivity;
import com.example.adanvace.activity.viewgroup.JigsawActivity;

import com.example.adanvace.activity.viewgroup.RecyclerActivity;
import com.example.adanvace.activity.viewgroup.ViewGroupActivity;
import com.example.adanvace.article.activity.ArticleActivity;
import com.example.adanvace.recyclerview.RecyclerViewTestActivity;
import com.example.adanvace.util.L;

/**
 * @author alan
 */
@RequestPermission(value = {Manifest.permission.READ_PHONE_STATE,Manifest.permission.CALL_PHONE})
public class MainActivity extends LatteActivity implements PermissionCallback {



    @Override
    public int getContentView() {
        setPermissionCallback(this);
        return R.layout.activity_main;
    }

    @Override
    public void initView() {

    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_article:
                startActivity(new Intent(MainActivity.this, ArticleActivity.class));
                break;
            case R.id.bt_vertical:
                startActivity(new Intent(MainActivity.this, VerticalActivity.class));
                break;
            case R.id.bt_view_image:
                startActivity(new Intent(MainActivity.this, CustomActivity.class));
                break;
            case R.id.bt_view_radius:
                startActivity(new Intent(MainActivity.this, RadiusActivity.class));
                break;
            case R.id.bt_view_sound:
                startActivity(new Intent(MainActivity.this, SoundActivity.class));
                break;
            case R.id.bt_volley:
                startActivity(new Intent(MainActivity.this, VolleyActivity.class));
                break;
            case R.id.bt_database:
                startActivity(new Intent(MainActivity.this, ContactActivity.class));
                break;
            case R.id.bt_viewpager:
                startActivity(new Intent(MainActivity.this, ViewPagerActivity.class));
                break;
            case R.id.bt_scrollView:
                startActivity(new Intent(MainActivity.this, ScrollViewActivity.class));
                break;
            case R.id.bt_refresh_listView:
                startActivity(new Intent(MainActivity.this, RefreshListViewActivity.class));
                break;
            case R.id.bt_view_group:
                startActivity(new Intent(MainActivity.this, ViewGroupActivity.class));
                break;
            case R.id.bt_view_group_flow:
                startActivity(new Intent(MainActivity.this, FlowViewGroupActivity.class));
                break;
            case R.id.bt_custom_adapter_listView:
                startActivity(new Intent(MainActivity.this, AdapterActivity.class));
                break;
            case R.id.bt_custom_adapter_recyclerView:
                startActivity(new Intent(MainActivity.this, RecyclerActivity.class));
                break;
            case R.id.bt_lottery:
                startActivity(new Intent(MainActivity.this, LotteryActivity.class));
                break;
            case R.id.bt_animation:
                startActivity(new Intent(MainActivity.this, AnimationActivity.class));
                break;
            case R.id.bt_animator:
                startActivity(new Intent(MainActivity.this, AnimatorActivity.class));
                break;
            case R.id.bt_horizontalScrollView:
                startActivity(new Intent(MainActivity.this, HorizontalScrollViewActivity.class));
                break;
            case R.id.bt_jigsaw:
                startActivity(new Intent(MainActivity.this, JigsawActivity.class));
                break;
            case R.id.bt_recycler:
                startActivity(new Intent(MainActivity.this, RecyclerViewTestActivity.class));
                break;
            default:
                break;

        }
    }


    @Override
    public void onPermissionReceive() {
        L.e("ON RECEIVE");
    }

    @Override
    public void onPermissionReject(String permissions) {
        L.e("reject "+permissions);
    }

    @Override
    public void onPermissionRemind() {
//        JumpPermissionManagement.GoToSetting(this);
        L.e("on remind");
    }
}
