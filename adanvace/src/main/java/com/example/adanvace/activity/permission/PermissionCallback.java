package com.example.adanvace.activity.permission;

public interface PermissionCallback {

    /**
     * 接收权限
     */
    void onPermissionReceive();

    /**
     * 拒绝
     * @param permissions
     */
    void onPermissionReject(String permissions);

    /**
     * 不在提醒
     */
    void onPermissionRemind();
}
