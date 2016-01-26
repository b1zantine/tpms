package me.sudar.tpms;

import android.app.Application;

/**
 * Created by sudar on 25/1/16.
 * Email : hey@sudar.me
 */
public class TpmsApp extends Application {
    public static String deviceAddress;
    public static int connectedTimes = 0;

    public static String getDeviceAddress() {
        return deviceAddress;
    }

    public static void setDeviceAddress(String deviceAddress) {
        TpmsApp.deviceAddress = deviceAddress;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }
}
