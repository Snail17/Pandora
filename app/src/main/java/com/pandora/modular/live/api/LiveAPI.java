package com.pandora.modular.live.api;

import com.pandora.modular.live.bean.LiveBean;
import com.pandora.modular.live.bean.LiveVO;

import io.reactivex.Observable;

import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by Administrator on 2018/5/12.
 */

public interface LiveAPI {
    @POST("")
    Observable<LiveBean> getLiveData(@Body LiveVO params);
}
