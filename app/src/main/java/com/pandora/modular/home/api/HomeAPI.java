package com.pandora.modular.home.api;

import com.pandora.modular.home.bean.HomeBean;
import com.pandora.modular.home.bean.HomeVO;

import io.reactivex.Observable;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Url;

/**
 * Created by Administrator on 2018/5/11.
 */

public interface HomeAPI {
    @POST("http://mj55.top/pandoraService/PrandoraPort?wsdl")
    Observable<HomeBean> getHomeData(@Body HomeVO params);

    @GET
    Call<ResponseBody> downloadFile(@Url String url);
}
