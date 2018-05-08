package com.pandora.core.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;


/**
 * Created by bukai on 2017/8/31.
 *
 * intro： 1 获取安卓手机相关信息
 *         2 获取App版本信息
 *
 * detail：1 手机型号
 *         2 手机厂商
 *         3 手机安卓系统版本
 *         4 app versionName
 *         5 app
 */
public class PhoneInformationUtil {

    /**
     * 手机型号
     * @return model
     */
    public static String getModel(){
        String model = Build.MODEL;
        return model;
    }

    /**
     * 手机厂商
     * @return manufacturer
     */
    public static String getManufacturer(){
        String manufacturer = Build.MANUFACTURER;
        return manufacturer;
    }

    /**
     * 手机系统版本
     * @return model mm
     */
    public static String getRelease(){
        String release = Build.VERSION.RELEASE;
        return release;
    }

    /**
     * getVersionCode
     * @param context cc
     * @return rr
     */
    public static int getVersionCode(Context context){
        PackageManager packageManager = context.getPackageManager();
        try{
            PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            return packageInfo.versionCode;

        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * getVersionName
     * @param context cc
     * @return rr
     */
    public static String getVersionName(Context context){
        PackageManager packageManager = context.getPackageManager();
        try{
            PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            return packageInfo.versionName;

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

}
