package com.example.alan.hundred.info;

import android.graphics.Bitmap;

/**
 * Function :
 * Author : Alan
 * Modify Date : 27/9/17
 * Issue : TODO
 * Whether solve :
 */

public class FlipperInfo {

    private Bitmap bitmap;
    private String context;

    public FlipperInfo(Bitmap bitmap, String context) {
        this.bitmap = bitmap;
        this.context = context;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }
}
