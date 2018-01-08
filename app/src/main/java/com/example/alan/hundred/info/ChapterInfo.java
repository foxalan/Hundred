package com.example.alan.hundred.info;

import android.graphics.Bitmap;

/**
 * Function : 章节的封装
 * Author : Alan
 * Modify Date : 15/9/17
 * Issue : TODO
 * Whether solve :
 */

public class ChapterInfo {

    private String name;
    private Bitmap icon;
    private String description;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Bitmap getIcon() {
        return icon;
    }

    public void setIcon(Bitmap icon) {
        this.icon = icon;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ChapterInfo(String name, Bitmap icon, String description) {
        this.name = name;
        this.icon = icon;
        this.description = description;
    }
}
