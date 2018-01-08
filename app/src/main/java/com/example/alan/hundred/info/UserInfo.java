package com.example.alan.hundred.info;

import android.graphics.Bitmap;

/**
 * Function :
 * Author : Alan
 * Modify Date : 25/9/17
 * Issue : TODO
 * Whether solve :
 */

public class UserInfo {

    private Bitmap icon;
    private String title;
    private String content;


    public Bitmap getIcon() {
        return icon;
    }

    public void setIcon(Bitmap icon) {
        this.icon = icon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public UserInfo(Bitmap icon, String title, String content) {
        this.icon = icon;
        this.title = title;
        this.content = content;
    }
}
