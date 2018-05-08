package com.pandora.core.http;


import com.pandora.core.globle.SPConstants;
import com.pandora.core.utils.SPUtils;

import java.io.IOException;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by cain on 2017/8/18.
 */

public class AddCookiesInterceptor implements Interceptor {


    @Override
    public Response intercept(Chain chain) throws IOException {
        final Request.Builder builder = chain.request().newBuilder();
        Observable.just(SPUtils.getString(SPConstants.REQUEST_COOKIE, ""))
                .subscribe(new Consumer<String>() {

                    @Override
                    public void accept(@NonNull String s) throws Exception {
                        builder.addHeader("Cookie", s);
                    }
                });
        return chain.proceed(builder.build());

    }
}
