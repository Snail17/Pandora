package com.pandora.modular.home.api;

import android.os.Environment;

import com.pandora.BuildConfig;
import com.pandora.core.http.HttpResponseFunc;
import com.pandora.core.http.MyOkHttpClient;
import com.pandora.core.http.ServiceGenerator;
import com.pandora.core.utils.LogUtils;
import com.pandora.core.utils.RxUtils;
import com.pandora.modular.PandoraApplication;
import com.pandora.modular.home.bean.HomeBean;
import com.pandora.modular.home.bean.HomeVO;
import com.pandora.modular.home.util.ProgressListener;
import com.pandora.modular.home.util.ProgressResponseBody;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
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

    private String DOWNLOADPATH = "/apk/";//下载路径，如果不定义自己的路径，6.0的手机不自动安装


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

    public void downloadFileProgress(final ProgressListener listener, String url) {
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


        downloadRetrofit.downloadFile(url).enqueue(callBack);
    }

    private String mPathname;
    Callback<ResponseBody> callBack = new Callback<ResponseBody>() {
        @Override
        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
            try {
                InputStream is = response.body().byteStream();
//                File storageDir = new File(Environment.getExternalStorageDirectory(), "download/Android");
//                storageDir.mkdirs();
//                File file = File.createTempFile("Pandora", ".apk", storageDir);
                File storageDir = new File(PandoraApplication.getInstance().getApplicationContext().getFilesDir(), "Android");
//        File photoFile = File.createTempFile("Pandora", ".apk", storageDir);
//                File photoFile = new File(storageDir, "Pandora.apk ");
                File file = new File(storageDir, "Pandora.apk ");
                LogUtils.e(file.getAbsolutePath());
                FileOutputStream fos = new FileOutputStream(file);
                BufferedInputStream bis = new BufferedInputStream(is);
                byte[] buffer = new byte[1024];
                int len;
                while ((len = bis.read(buffer)) != -1) {
                    fos.write(buffer, 0, len);
                    fos.flush();
                }
                fos.close();
                bis.close();
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onFailure(Call<ResponseBody> call, Throwable t) {

        }
    };


}
