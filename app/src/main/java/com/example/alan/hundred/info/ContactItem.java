package com.example.alan.hundred.info;

import android.graphics.Bitmap;

/**
 * Function :
 * Author : Alan
 * Modify Date : 25/9/17
 * Issue : TODO
 * Whether solve :
 */

public class ContactItem  {

    private Bitmap icon;
    private String name;
    private String content;


    public ContactItem(Bitmap icon, String name, String content) {
        this.icon = icon;
        this.name = name;
        this.content = content;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "ContactItem{" +
                "icon=" + icon +
                ", name='" + name + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
