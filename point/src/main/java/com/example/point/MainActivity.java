package com.example.point;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;

import com.example.point.fragment.PeriodFragment;
import com.example.point.util.L;

public class MainActivity extends AppCompatActivity {
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //support.v4.app.Fragment;
        getSupportFragmentManager().beginTransaction().add(R.id.fl_container,PeriodFragment.getInstance()).commit();
        L.d("activity onCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();

        L.d("activity onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();

        L.d("activity onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();

        L.d("activity onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();

        L.d("activity onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        L.d("activity onDestroy");
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }
}
