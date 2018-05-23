package com.pandora.modular.home.api;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.support.v4.content.FileProvider;
import android.text.TextUtils;

import com.pandora.BuildConfig;
import com.pandora.core.base.AppManager;
import com.pandora.core.http.HttpResponseFunc;
import com.pandora.core.http.MyOkHttpClient;
import com.pandora.core.http.ServiceGenerator;
import com.pandora.core.utils.RxUtils;
import com.pandora.modular.PandoraApplication;
import com.pandora.modular.home.bean.HomeBean;
import com.pandora.modular.home.bean.HomeVO;
import com.pandora.modular.home.fragment.HomeHotFragment;
import com.pandora.modular.home.util.ProgressListener;
import com.pandora.modular.home.util.ProgressResponseBody;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
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

    @SuppressLint("StaticFieldLeak")
    public void downloadFileProgress(final ProgressListener listener, final String url) {
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


        final HomeAPI downloadRetrofit = retrofitBuilder.client(client).build().create(HomeAPI.class);

        final Call<ResponseBody> call = downloadRetrofit.downloadFile(url);
        startCall(call);
    }

    private void startCall(Call<ResponseBody> call) {
        File storageDir = new File(Environment.getExternalStorageDirectory().toString(), "Android");
        String apkName = PandoraApplication.getInstance().getApplicationContext().getPackageName()
                + "_" + "Pandora" + ".apk";
        call.enqueue(new FileCallback(storageDir.getAbsolutePath(), apkName) {


            @Override
            public void onSuccess(File file) {
                super.onSuccess(file);
                install(HomeHotFragment.downloadUpdateApkFilePath);
            }


            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

                call.cancel();


            }

        });
    }

    private void install(String path) {
        if (!TextUtils.isEmpty(path)) {
            Intent i = new Intent(Intent.ACTION_VIEW);
            File apkFile = new File(path);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                i.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                Uri contentUri = FileProvider.getUriForFile(
                        PandoraApplication.getInstance().getApplicationContext(),
                        PandoraApplication.getInstance().getApplicationContext().getPackageName() + ".fileprovider",
                        apkFile);
                i.setDataAndType(contentUri, "application/vnd.android.package-archive");
            } else {
                i.setDataAndType(Uri.fromFile(apkFile),
                        "application/vnd.android.package-archive");
            }
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            PandoraApplication.getInstance().getApplicationContext().startActivity(i);
            AppManager.getAppManager().AppExit(PandoraApplication.getInstance().getApplicationContext(), false);
        }
    }


}
