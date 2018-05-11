package com.pandora.modular.home.api;

import com.pandora.modular.home.bean.HomeBean;
import com.pandora.modular.home.bean.HomeVO;

import io.reactivex.Observable;

import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by Administrator on 2018/5/11.
 */

public interface HomeAPI {
    @POST("")
    Observable<HomeBean> getHomeData(@Body HomeVO params);

}
