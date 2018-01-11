package com.example.alan.hundred.alert;

import android.content.Context;

import java.util.List;

/**
 * Function :
 * Modify Date : 2018/1/11
 *
 * @Author : Alan
 * Issue : TODO
 * Whether Solve :
 */

public class AlertViewBuilder {

    private String mTitle;
    private String mContent;
    private List<String> mDestructive;
    private AlertType mType;
    private Context mContext;
    private IAlertCallBack iAlertCallBack;
    private List<String> mDataList;

    public AlertViewBuilder setTitle(String title) {
        this.mTitle = title;
        return this;
    }

    public AlertViewBuilder setContent(String content) {
        this.mContent = content;
        return this;
    }

    public AlertViewBuilder setDialogType(AlertType type) {
        this.mType = type;
        return this;
    }

    public AlertViewBuilder setDestructive(List<String> destructive) {
        this.mDestructive = destructive;
        return this;
    }

    public AlertViewBuilder setDataList(List<String> dataList) {
        this.mDataList = dataList;
        return this;
    }

    public AlertViewBuilder setContext(Context context){
        this.mContext = context;
        return this;
    }

    public AlertViewBuilder setAlertOnClickLister(IAlertCallBack iAlertCallBack){
        this.iAlertCallBack = iAlertCallBack;
        return this;
    }

    public AlertView build() {
        return new AlertView(mTitle, mContent, mDestructive, mType, mContext,iAlertCallBack,mDataList);
    }


}
