package com.example.alan.hundred.activity.view;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.CheckBox;
import android.widget.Chronometer;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.ToggleButton;

import com.example.alan.hundred.R;
import com.example.alan.hundred.activity.BaseHomeActivity;
import com.example.alan.hundred.view.MyToast;

/**
 * 1.设置selector的问题:
 * item 是由上向下匹配的,遇到的符合的条件就生效
 * button 的默认条件是 state_enabled="true"
 * 2.selector的值
 * android:state_enabled: 设置触摸或点击事件是否可用状态，一般只在false时设置该属性，表示不可用状态
 * android:state_pressed: 设置是否按压状态，一般在true时设置该属性，表示已按压状态，默认为false
 * android:state_selected: 设置是否选中状态，true表示已选中，false表示未选中
 * android:state_checked: 设置是否勾选状态，主要用于CheckBox和RadioButton，true表示已被勾选，false表示未被勾选
 * android:state_checkable: 设置勾选是否可用状态，类似state_enabled，只是state_enabled会影响触摸或点击事件，而state_checkable影响勾选事件
 * android:state_focused: 设置是否获得焦点状态，true表示获得焦点，默认为false，表示未获得焦点
 * android:state_window_focused: 设置当前窗口是否获得焦点状态，true表示获得焦点，false表示未获得焦点，例如拉下通知栏或弹出对话框时，当前界面就会失去焦点；另外，ListView的ListItem获得焦点时也会触发true状态，可以理解为当前窗口就是ListItem本身
 * android:state_activated: 设置是否被激活状态，true表示被激活，false表示未激活，API Level 11及以上才支持，可通过代码调用控件的setActivated(boolean)方法设置是否激活该控件
 * android:state_hovered: 设置是否鼠标在上面滑动的状态，true表示鼠标在上面滑动，默认为false，API Level 14及以上才支持
 */

public class ButtonActivity extends BaseHomeActivity {

    private RadioGroup rg_sex;

    private CheckBox cb_red;
    private CheckBox cb_yellow;
    private CheckBox cb_blue;

    private BoxChecked boxChecked;

    private ToggleButton tb_test;
    private Switch switch_test;

    private Chronometer chronometer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int getContentView() {
        return R.layout.activity_view_button;
    }

    @Override
    public void initViews() {
        rg_sex = (RadioGroup) findViewById(R.id.rg_sex);

        cb_red = (CheckBox) findViewById(R.id.cb_red);
        cb_blue = (CheckBox) findViewById(R.id.cb_blue);
        cb_yellow = (CheckBox) findViewById(R.id.cb_yellow);

        tb_test = (ToggleButton) findViewById(R.id.tb_test);
        switch_test = (Switch) findViewById(R.id.switch_test);

        chronometer = (Chronometer) findViewById(R.id.chronometer);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initEvents() {
        rg_sex.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch (checkedId) {
                    case R.id.rb_female:
                        MyToast.showMessage("female checked");
                        break;
                    case R.id.rb_male:
                        MyToast.showMessage("male checked");
                        break;
                }
            }
        });

        boxChecked = new BoxChecked();

        cb_red.setOnCheckedChangeListener(boxChecked);
        cb_blue.setOnCheckedChangeListener(boxChecked);
        cb_yellow.setOnCheckedChangeListener(boxChecked);

        tb_test.setOnCheckedChangeListener(boxChecked);
        switch_test.setOnCheckedChangeListener(boxChecked);

        chronometer.setBase(SystemClock.elapsedRealtime());
        chronometer.start();

        chronometer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer chronometer) {
                if (SystemClock.elapsedRealtime() - chronometer.getBase() > 10 * 1000) {
                    chronometer.stop();
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = new MenuInflater(this);
        inflater.inflate(R.menu.meau_sound,menu);


        return super.onCreateOptionsMenu(menu);
    }

    private class BoxChecked implements CompoundButton.OnCheckedChangeListener {

        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            switch (buttonView.getId()) {
                case R.id.cb_red:
                    MyToast.showMessage(isChecked ? "cb_red checked" : "cb_red not checked");
                    break;
                case R.id.cb_blue:
                    MyToast.showMessage(isChecked ? "cb_blue checked" : "cb_blue not checked");
                    break;
                case R.id.cb_yellow:
                    MyToast.showMessage(isChecked ? "cb_yellow checked" : "cb_yellow not checked");
                    break;
                case R.id.tb_test:
                    MyToast.showMessage(isChecked ? "tb on" : "tb off");
                    break;
                case R.id.switch_test:
                    MyToast.showMessage(isChecked ? "switch on" : "switch off");
                    break;
            }
        }
    }
}
