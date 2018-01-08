package com.example.alan.hundred.info;

import android.graphics.Bitmap;

/**
 * Function :
 * Author : Alan
 * Modify Date : 27/9/17
 * Issue : TODO
 * Whether solve :
 */

public class StateInfo {


    private Bitmap icon;
    private String content;
    private Type type;

    public StateInfo(Bitmap icon, String content, Type type) {
        this.icon = icon;
        this.content = content;
        this.type = type;
    }

    public Bitmap getIcon() {
        return icon;
    }

    public void setIcon(Bitmap icon) {
        this.icon = icon;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}
