package com.example.alan.hundred.info;

import android.graphics.Bitmap;

/**
 * Function :
 * Author : Alan
 * Modify Date : 22/9/17
 * Issue : TODO
 * Whether solve :
 */

public class ViewInfo {

    private String name;
    private Bitmap icon;


    public ViewInfo(String name, Bitmap icon) {
        this.name = name;
        this.icon = icon;
    }

    public Bitmap getIcon() {
        return icon;
    }

    public void setIcon(Bitmap icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
