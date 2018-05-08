package com.pandora.core.http;


import com.pandora.core.globle.SPConstants;
import com.pandora.core.utils.LogUtils;
import com.pandora.core.utils.SPUtils;

import java.io.IOException;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * Created by cain on 2017/8/18.
 */

public class ReceivedCookiesInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Response originalResponse = chain.proceed(chain.request());
        //这里获取请求返回的cookie
        if (!originalResponse.headers("Set-Cookie").isEmpty()) {
            final StringBuffer cookieBuffer = new StringBuffer();
            Observable.fromIterable(originalResponse.headers("Set-Cookie")).map(new Function<String, String>() {
                @Override
                public String apply(@NonNull String s) throws Exception {
                    String[] cookieArray = s.split(";");
                    return cookieArray[0];
                }

            }).subscribe(new Consumer<String>() {
                @Override
                public void accept(@NonNull String s) throws Exception {
                    cookieBuffer.append(s).append(";");
                }
            });
            if (cookieBuffer.toString().contains("JSESSIONID")){
                SPUtils.putString(SPConstants.REQUEST_COOKIE, cookieBuffer.toString());
            }

            LogUtils.e("cookieBuffer.toString()---------"+cookieBuffer.toString());
        }
        return originalResponse;
    }
}
