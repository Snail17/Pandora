package com.pandora.core.utils;

import android.text.TextUtils;

import com.pandora.core.globle.SPConstants;
import com.pandora.modular.PandoraApplication;

import okhttp3.Request;

/**
 * Created by cain on 2017/8/18.
 */

public final class RestUtil {
    /**
     *
     */
    private static RestUtil instance;
    /**
     *
     */
    private static Object lock = new Object();

    /**
     *
     */
    private RestUtil() {

    }

    /**
     * @return s
     * @throws Exception s
     */
    public static RestUtil getInstance() throws Exception {
        if (null == instance) {

            synchronized (lock) {
                if (null == instance) {
                    instance = new RestUtil();
                }
            }
        }
        return instance;

    }

    /**
     * @param request s
     * @return s
     */
    public Request addAcceptLanguage(Request request) {
        String language = SPUtils.getString(SPConstants.ACCEPT_LANGUAGE, "zh-CN");
        if (TextUtils.isEmpty(language)) {
            return request;
        }
        return request.newBuilder().addHeader("Accept-Language", language).build();
    }

    /**
     * @param request s
     * @return s
     */
    public Request addPhoneInfo(Request request) {
        String versionName = PhoneInformationUtil.getVersionName(PandoraApplication.getInstance());
        String model = PhoneInformationUtil.getModel();
        String release = PhoneInformationUtil.getRelease();
        String platform = "Android";
        String deviceId = SPUtils.getString("devicedId", "Pandora");
        String DeviceInfo = versionName + "|" + model + "|" + release + "|" + platform + "|" + deviceId;
        return request.newBuilder().addHeader("DeviceInfo", DeviceInfo).build();
    }

}
