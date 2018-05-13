package com.pandora.core.utils;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.provider.Settings;
import android.telephony.TelephonyManager;

import com.pandora.modular.PandoraApplication;


/**
 * Created by feng on 2017/11/13.
 */

public class DeviceIdUtils {
    /**
     *
     * @return re
     */
    public static String getDeviceId() {
        String deviceId = "";
        if (deviceId == null || "".equals(deviceId)) {
            deviceId = getIMIEStatus();
        }
//        if (deviceId == null || "".equals(deviceId)) {
//            deviceId = getAndroidId();
//        }
//        if (deviceId == null || "".equals(deviceId)) {
//            deviceId = getLocalMac().replace(":", "");
//        }
//

        return deviceId;
    }

    /**
     * @return IMIE
     */
    private static String getIMIEStatus() {
        TelephonyManager tm = (TelephonyManager) PandoraApplication.getInstance().getSystemService(Context.TELEPHONY_SERVICE);
        String deviceId = tm.getDeviceId();
        return deviceId;
    }

    /**
     * @return mac
     */
    private static String getLocalMac() {
        WifiManager wifi = (WifiManager) PandoraApplication.getInstance().getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        WifiInfo info = wifi.getConnectionInfo();
        if (info.getMacAddress() == null) {
            return BluetoothAdapter.getDefaultAdapter().getAddress();
        } else {
            return info.getMacAddress();
        }

    }

    /**
     *
     * @return andrdidId
     */
    public static String getAndroidId() {
        String androidID = Settings.Secure.getString(PandoraApplication.getInstance().getApplicationContext().getContentResolver(), Settings.Secure.ANDROID_ID);
        return androidID;
    }

}
