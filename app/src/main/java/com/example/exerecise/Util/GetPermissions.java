package com.example.exerecise.Util;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

public class GetPermissions {

    Context mContext;
    Activity mActivity;
    private final static int MY_PERMISSIONS_REQUEST_CALL_PHONE = 1234;

    public GetPermissions(Context mContext, Activity mActivity) {
        this.mContext = mContext;
        this.mActivity = mActivity;
    }

    public boolean getPhonePermission(){
        boolean permissionGranted = false;
        if (ContextCompat.checkSelfPermission(mActivity,
                Manifest.permission.CALL_PHONE)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(mActivity,
                    new String[]{Manifest.permission.CALL_PHONE},
                    MY_PERMISSIONS_REQUEST_CALL_PHONE);

        } else {
            try {
                permissionGranted = true;
            } catch(SecurityException e) {
                e.printStackTrace();
            }
        }

        return permissionGranted;
    }
}
