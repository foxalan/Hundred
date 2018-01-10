package com.example.alan.hundred.info;

import android.graphics.Bitmap;

import java.util.List;

/**
 * Function :
 * Modify Date : 2018/1/10
 *
 * @Author : Alan
 * Issue : TODO
 * Whether Solve :
 */

public class PageInfo {
    private int  type;
    private Bitmap bitmap;
    private String title;
    private List<Bitmap> bitmapList;

    public PageInfo(){

    }

    public PageInfo(int type, Bitmap bitmap, String title, List<Bitmap> bitmapList) {
        this.type = type;
        this.bitmap = bitmap;
        this.title = title;
        this.bitmapList = bitmapList;
    }

    public PageInfo(int type, Bitmap bitmap) {
        this.type = type;
        this.bitmap = bitmap;
    }

    public PageInfo(int type, List<Bitmap> bitmapList) {
        this.type = type;
        this.bitmapList = bitmapList;
    }

    public PageInfo(int type, Bitmap bitmap, String title) {
        this.type = type;
        this.bitmap = bitmap;
        this.title = title;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Bitmap> getBitmapList() {
        return bitmapList;
    }

    public void setBitmapList(List<Bitmap> bitmapList) {
        this.bitmapList = bitmapList;
    }
}
