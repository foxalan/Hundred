package com.example.adanvace.main;

/**
 * @author alan
 * function:
 */
public class PageItemBean {

    private String title;
    private int resIdNormal;
    private int resIdClicked;
    private BaseItemFragment itemFragment;

    public PageItemBean(String title, int resIdNormal, int resIdClicked, BaseItemFragment itemFragment) {
        this.title = title;
        this.resIdNormal = resIdNormal;
        this.resIdClicked = resIdClicked;
        this.itemFragment = itemFragment;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getResIdNormal() {
        return resIdNormal;
    }

    public void setResIdNormal(int resIdNormal) {
        this.resIdNormal = resIdNormal;
    }

    public int getResIdClicked() {
        return resIdClicked;
    }

    public void setResIdClicked(int resIdClicked) {
        this.resIdClicked = resIdClicked;
    }

    public BaseItemFragment getItemFragment() {
        return itemFragment;
    }

    public void setItemFragment(BaseItemFragment itemFragment) {
        this.itemFragment = itemFragment;
    }
}
