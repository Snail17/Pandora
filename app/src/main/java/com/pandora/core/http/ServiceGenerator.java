package com.pandora.core.http;

import com.pandora.BuildConfig;
import com.pandora.core.utils.LogUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * 生成相应的api service对象
 * Created by mac on 17/7/19.
 */
public final class ServiceGenerator {

    // private static final String API_BASE_URI = "http://www.kuaidi100.com/";


    private static final Object INSTANCE_LOCK = new Object();

    private Retrofit.Builder mRetrofitBuilder;

    private OkHttpClient sOkHttpClient;

    private static ServiceGenerator sServiceGenerator;


    /**
     * @return s
     */
    public static ServiceGenerator getServiceGenerator() {
        if (sServiceGenerator == null) {
            synchronized (INSTANCE_LOCK) {
                if (sServiceGenerator == null) {
                    sServiceGenerator = new ServiceGenerator();
                }
            }
        }
        return sServiceGenerator;
    }

    /**
     * s
     */
    private ServiceGenerator() {

        sOkHttpClient = MyOkHttpClient.getOkHttpClient();
        mRetrofitBuilder = new Retrofit.Builder()
                .baseUrl(BuildConfig.API_BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create());
    }

    public Retrofit.Builder getRetrofitBuilder() {
        return mRetrofitBuilder;
    }

    /**
     * @param serviceClass s
     * @param <S>          s
     * @return s
     */
    public static <S> S createService(Class<S> serviceClass) {
        return createService(serviceClass, false);
    }

    /**
     * @param serviceClass s
     * @param resetUrl     s
     * @param <S>          s
     * @return s
     */
    public static <S> S createService(Class<S> serviceClass, boolean resetUrl) {
        Retrofit retrofit;
        retrofit = getServiceGenerator().getRetrofit();
        return retrofit.create(serviceClass);
    }

    /**
     * @param serviceClass s
     * @param url          s
     * @param <S>          s
     * @return s
     */
    public static <S> S createService(Class<S> serviceClass, String url) {
        Retrofit retrofit;
        retrofit = getServiceGenerator().getRetrofit(url);
        return retrofit.create(serviceClass);
    }

    /**
     * @param url s
     * @return s
     */
    private Retrofit getRetrofit(String url) {
        // return mRetrofitBuilder.baseUrl(url + "/api/").client(sOkHttpClient).build();
        return mRetrofitBuilder.baseUrl(url).client(sOkHttpClient).build();
    }

    /**
     * @return s
     */
    private Retrofit getRetrofit() {
        return mRetrofitBuilder.client(sOkHttpClient).build();
    }

    /**
     * @return s
     */
    private String getServerUrl() {
        // Log.e("getServerUrl", "getServerUrl->" + RFSPUtil.getServerUrl());
        //return RFSPUtil.getServerUrl();
        return null;
    }


    private CookieJar cookieJar() {
        return new CookieJar() {

            HashMap<HttpUrl, List<Cookie>> cookieStore = new HashMap<>();

            @Override
            public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
                cookieStore.put(url, cookies);
                cookieStore.put(HttpUrl.parse(BuildConfig.API_BASE_URL), cookies);
                for (Cookie cookie : cookies) {

                    LogUtils.e("cookie name-------" + cookie.name());
                    LogUtils.e("cookie Path------" + cookie.path());
                }
            }

            @Override
            public List<Cookie> loadForRequest(HttpUrl url) {
                List<Cookie> cookies = cookieStore.get(HttpUrl.parse(BuildConfig.API_BASE_URL));

                if (cookies == null) {
                    LogUtils.e("mei you get cookie---------");
                }
                return cookies != null ? cookies : new ArrayList<Cookie>();
            }
        };
    }


}
