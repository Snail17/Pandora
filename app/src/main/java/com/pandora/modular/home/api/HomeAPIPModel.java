package com.pandora.modular.home.api;

import com.pandora.BuildConfig;
import com.pandora.core.http.HttpResponseFunc;
import com.pandora.core.http.MyOkHttpClient;
import com.pandora.core.http.ServiceGenerator;
import com.pandora.core.utils.RxUtils;
import com.pandora.modular.home.bean.HomeBean;
import com.pandora.modular.home.bean.HomeVO;
import com.pandora.modular.home.util.ProgressListener;
import com.pandora.modular.home.util.ProgressResponseBody;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2018/5/11.
 */

public class HomeAPIPModel {

    private static HomeAPI sHomeService;
    private static HomeAPIPModel sHomeModel;
    private boolean isDebugger;

    private HomeAPIPModel() {
    }

    public static HomeAPIPModel getInstance() {
        if (sHomeService == null) {
            synchronized (HomeAPIPModel.class) {
                if (sHomeService == null) {
                    sHomeService = ServiceGenerator.createService(HomeAPI.class);
                    sHomeModel = new HomeAPIPModel();
                }
            }
        }
        return sHomeModel;
    }

    public Observable<HomeBean> getModelHomeData(HomeVO params) {
        Observable<HomeBean> observable = null;
        if (observable == null) {
            if (isDebugger) {
                observable = sHomeService.getHomeData(params);
            } else {
                HomeBean homeBean = new HomeBean();
                homeBean.setOnlineService("有问题可以联系客服，联系客服，联系客服，联系客服，本软件无需注册即可登录");
                List<String> words = new ArrayList<>();
                words.add("出租源码设计");
                homeBean.setaWords(words);
                List<HomeBean.HomeData> data = new ArrayList<>();
                for (int i = 0; i < 36; i++) {
                    HomeBean.HomeData homeData = homeBean.new HomeData();
                    homeData.setAnchor(2 * i + "");
                    homeData.setName("item" + i);
                    data.add(homeData);
                }
                homeBean.setData(data);
                observable = RxUtils.demo(homeBean);
            }
        }
        return observable.compose(RxUtils.<HomeBean>io_main())
                .onErrorResumeNext(new HttpResponseFunc<HomeBean>());

    }

    public void downloadFileProgress(final ProgressListener listener, Callback<ResponseBody> callback, String url) {
        //okhttp拦截
        Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//            .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BuildConfig.API_BASE_URL);
        OkHttpClient client = MyOkHttpClient.getOkHttpClient().newBuilder().addNetworkInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                okhttp3.Response response = chain.proceed(chain.request());
                return response.newBuilder().body(new ProgressResponseBody(response.body(), listener)).build();
            }
        }).build();


        HomeAPI downloadRetrofit = retrofitBuilder.client(client).build().create(HomeAPI.class);


        downloadRetrofit.downloadFile(url).enqueue(callback);


    }


}
