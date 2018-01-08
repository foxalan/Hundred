package com.example.alan.hundred.activity.view;

import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import com.example.alan.hundred.R;
import com.example.alan.hundred.activity.BaseHomeActivity;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Function :
 * Author : Alan
 * Modify Date : 28/9/17
 * Issue : TODO
 * Whether solve :
 */

public class SwitcherActivity extends BaseHomeActivity {
    private ImageSwitcher is_switcher;
    private TextSwitcher ts_switcher;

    private int[] iv_ids;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int getContentView() {
        return R.layout.activity_view_switcher;
    }

    @Override
    public void initViews() {
        is_switcher = (ImageSwitcher) findViewById(R.id.is_switcher);
        ts_switcher = (TextSwitcher) findViewById(R.id.ts_switcher);
    }

    @Override
    public void initData() {
        iv_ids = new int[]{R.drawable.ic_view_button, R.drawable.ic_view_page, R.drawable.ic_view_adapter};
    }


    /**
     * 使用ImageSwitcher时要先设置Factory
     */

    @Override
    public void initEvents() {
        is_switcher.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                ImageView imageView = new ImageView(SwitcherActivity.this);
                imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                imageView.setLayoutParams(new ImageSwitcher.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));

                return imageView;
            }
        });

        is_switcher.setImageResource(R.drawable.ic_animation);

        ts_switcher.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {

                TextView textView = new TextView(SwitcherActivity.this);
                textView.setTextColor(Color.RED);
                textView.setTextSize(30);
                return textView;
            }
        });

        ts_switcher.setText("SWITCHER");
        /**
         *  android.view.ViewRootImpl$CalledFromWrongThreadException:
         *  Only the original thread that created a view hierarchy can touch its views.
         */
//        new Timer().schedule(new TimerTask() {
//            @Override
//            public void run() {
//                is_switcher.setImageResource(iv_ids[i]);
//                i++;
//                if (i == 3) {
//                    i = 0;
//                }
//            }
//        }, 3000, 3000);

    }

//    public void next(View view){
//        if (view.getId() == R.id.ts_switcher){
//            ts_switcher.setText("NEXT");
//        }
//    }
}
