package com.example.alan.hundred;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import com.example.alan.hundred.Config.Config;
import com.example.alan.hundred.fragment.AnimationFragment;
import com.example.alan.hundred.fragment.HomeFragment;
import com.example.alan.hundred.fragment.ViewFragment;
import com.example.alan.hundred.service.CustomService;
import com.example.alan.hundred.util.FragmentUtil;
import com.example.alan.hundred.util.ShareUtils;

public class MainActivity extends AppCompatActivity {

    public static Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0x123) {
                Log.d("TANG", "==================================");
            }
        }
    };


    private android.support.v7.widget.Toolbar toolbar;
    private android.support.design.widget.NavigationView navigationview;
    private android.support.v4.widget.DrawerLayout drawerlayout;

    /*创建一个Drawerlayout和Toolbar联动的开关*/
    private ActionBarDrawerToggle toggle;

    private AnimationFragment animationFragment;
    private ViewFragment viewFragment;
    private HomeFragment homeFragment;

    @Override
    protected void onResume() {

        super.onResume();


    }

    @Override
    protected void onPause() {
        super.onPause();
        stopService(new Intent(MainActivity.this, CustomService.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        setActionBar();

        setDrawerToggle();

        setListener();

        initEvents();

        initUseTime();
    }

    private void initUseTime() {
        int time = ShareUtils.getInt(this, Config.APP_USE_COUNT, 0);
        time++;
        ShareUtils.putInt(this, Config.APP_USE_COUNT, time);
    }

    private void initViews() {

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        navigationview = (NavigationView) findViewById(R.id.navigation_view);
        drawerlayout = (DrawerLayout) findViewById(R.id.drawer_layout);

    }


    /*设置ActionBar*/
    private void setActionBar() {
        setSupportActionBar(toolbar);
        /*显示Home图标*/
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    /*设置Drawerlayout的开关,并且和Home图标联动*/
    private void setDrawerToggle() {
        toggle = new ActionBarDrawerToggle(this, drawerlayout, toolbar, 0, 0);
        drawerlayout.addDrawerListener(toggle);
        /*同步drawerlayout的状态*/
        toggle.syncState();
    }


    /*设置监听器*/
    private void setListener() {
        navigationview.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()) {

                    case R.id.single_1:
                        //       FragmentUtil.show(animationFragment);
                        break;
                    case R.id.single_2:
                        //       FragmentUtil.show(viewFragment);
                        break;
                    case R.id.single_3:
                        break;
                    case R.id.single_4:
                        break;

                }
                drawerlayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });
    }

    private void initEvents() {

        viewFragment = ViewFragment.getInstance();
        animationFragment = AnimationFragment.getInstance();
        homeFragment = HomeFragment.getInstance();
        getFragmentManager().beginTransaction().add(R.id.fl_container, homeFragment).show(homeFragment).commit();

        FragmentUtil.firstFragment = viewFragment;
        //    FragmentUtil.show(animationFragment);
    }


    @Override
    protected void onNewIntent(Intent intent) {
        String result = intent.getStringExtra("result");
        if (result != null) {
            Log.d("TANG", result);

        }
        // super.onNewIntent(intent);
    }
}
