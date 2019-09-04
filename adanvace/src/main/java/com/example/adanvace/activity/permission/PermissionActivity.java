package com.example.adanvace.activity.permission;


import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;


import com.example.adanvace.activity.view.BaseActivity;
import com.example.adanvace.util.L;

import java.util.ArrayList;

public abstract class PermissionActivity extends BaseActivity {

    public static final int REQUEST_CODE = 0x11;

    private PermissionCallback permissionCallback;

    public void setPermissionCallback(PermissionCallback permissionCallback) {
        this.permissionCallback = permissionCallback;
    }

    protected void requestPermission() {
        L.d("request permission"+getClass()+getClass().isAnnotation());
        if (getClass().isAnnotationPresent(RequestPermission.class)) {
            RequestPermission requestPermission = getClass().getAnnotation(RequestPermission.class);
            String[] permissions = requestPermission.value();
            startRequestPermissions(permissions);
        }
    }

    protected void startRequestPermissions(String[] permissions) {
        ArrayList<String> permissionList = new ArrayList<>();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            for (String permission:permissions){
                if (checkCallingOrSelfPermission(permission)!=PackageManager.PERMISSION_GRANTED){
                    permissionList.add(permission);
                }
            }
            if (permissionList.size() == 0){
                permissionCallback.onPermissionReceive();
            }else {
                requestPermissions(permissions, REQUEST_CODE);
            }
        } else {
            if (permissionCallback != null) {
                permissionCallback.onPermissionReceive();
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        ArrayList<String> permissionList = new ArrayList<>();
        if (requestCode == REQUEST_CODE) {
            for (int i = 0; i < permissions.length; i++) {
                if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                    permissionList.add(permissions[i]);
                } else {
                    if (!ActivityCompat.shouldShowRequestPermissionRationale(this, permissions[i])) {
                        permissionCallback.onPermissionRemind();
                    } else {
                        //权限请求失败，但未选中“不再提示”选项
                        permissionCallback.onPermissionReject(permissions[i]);
                    }
                }
            }
            if (permissionList.size() == permissions.length){
                permissionCallback.onPermissionReceive();
            }
        }
    }
}
