package com.example.alan.hundred.activity.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.alan.hundred.R;
import com.example.alan.hundred.activity.BaseHomeActivity;
import com.example.alan.hundred.alert.AlertType;
import com.example.alan.hundred.alert.AlertView;
import com.example.alan.hundred.alert.IAlertCallBack;
import com.example.alan.hundred.view.CustomDialog;
import com.example.alan.hundred.view.DialogView;
import com.example.alan.hundred.view.MyToast;

import java.util.ArrayList;
import java.util.List;

/**
 * Function :
 * @Author : Alan
 * Modify Date : 28/9/17
 * Issue : 1.Caused by: android.view.WindowManager$BadTokenException: Unable to add window
 * -- token android.os.BinderProxy@22701c7 is not valid; is your activity running?
 * Whether solve :
 * <p>
 * 这里主要说的是你的android里一个Activity发生窗体泄漏了，也就是我们常说的内存泄漏，为什么窗体会泄漏,
 * <p>
 * 主要是你的打开一个PopupWindow（窗体）时，如图。它没有关闭PopupWindow（窗体），就退出这个Activity，就会发生这个错误，
 * <p>
 * 因为这里就有一个顺序，你要先关闭PopupWindow，再关闭Activity,这个一定的，PopupWindow(窗体)不能独立存在。
 */

public class DialogActivity extends BaseHomeActivity {

    private ViewGroup rootView;
    private LayoutInflater layoutInflater;

    private View view_header;
    private DialogView dialogView;

    private String mTitle;
    private String mContent;
    private List<String> mDestructive = new ArrayList<>();
    private AlertType mType;
    private String mCancel;
    private List<String> mDataList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int getContentView() {
        return R.layout.activity_view_dialog;
    }

    @Override
    public void initViews() {

        dialogView = new DialogView(this);

        CustomDialog.createGirlDialog(this);
        layoutInflater = LayoutInflater.from(this);

        rootView = getWindow().getDecorView().findViewById(android.R.id.content);
        view_header = layoutInflater.inflate(R.layout.layout_header, rootView, false);
    }

    @Override
    public void initData() {
        mTitle = "title";
        mContent = "4396 clear love run";
        mType = AlertType.NORMAL;
        mDestructive.add("取消");
        mDestructive.add("确定");
        mCancel = "取消";
        mDataList.add("从相册选择");
        mDataList.add("拍照");
    }

    @Override
    public void initEvents() {

    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_dialog_simple:
                AlertView.builder().setTitle(mTitle)
                        .setContent(mContent)
                        .setContext(DialogActivity.this)
                        .setDestructive(mDestructive)
                        .setDialogType(mType)
                        .setAlertOnClickLister(new IAlertCallBack() {
                            @Override
                            public void onConfirmClick() {
                                MyToast.showMessage("点击了确定");
                            }

                            @Override
                            public void onCancelClick() {
                                MyToast.showMessage("点击了取消");
                            }
                        })
                        .build()
                        .show();
                break;
            case R.id.bt_dialog_simple_bottom:
                AlertView.builder()
                        .setTitle("拍照")
                        .setCancel(mCancel)
                        .setDataList(mDataList)
                        .setContext(DialogActivity.this)
                        .setDialogType(AlertType.BOTTOM)
                        .build()
                        .show();

                break;
            case R.id.bt_dialog_bottom:
                AlertView.builder().setTitle(mTitle)
                        .setContent(mContent)
                        .setContext(DialogActivity.this)
                        .setCancel(mCancel)
                        .setDialogType(AlertType.BOTTOM)
                        .build()
                        .show();
                break;
            default:
                break;
        }
    }

}
