package com.demo.util;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

public class AppMarshMallowPermission {

    public static final int RECORD_PERMISSION_CODE = 1;
    public static final int STORAGE_PERMISSION_CODE = 2;
    public static final int CAMERA_PERMISSION_CODE = 3;

    public static boolean checkPermission(@NonNull Activity activity, @NonNull String permission) {
        int result = ContextCompat.checkSelfPermission(activity, permission);
        return result == PackageManager.PERMISSION_GRANTED;
    }

    public static void requestPermission(@NonNull Activity activity, String permission, int permissionRequest) {
        if (ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.RECORD_AUDIO)) {
            ActivityCompat.requestPermissions(activity, new String[]{permission}, permissionRequest);
        } else {
            ActivityCompat.requestPermissions(activity, new String[]{permission}, permissionRequest);
        }
    }

    public static String getPermission(int type) {
        switch (type) {
            case RECORD_PERMISSION_CODE:
                return Manifest.permission.RECORD_AUDIO;

            case STORAGE_PERMISSION_CODE:
                return Manifest.permission.WRITE_EXTERNAL_STORAGE;

            case CAMERA_PERMISSION_CODE:
                return Manifest.permission.CAMERA;
        }
        return null;
    }

}