package com.pandora.modular.home.model;

import com.pandora.core.exception.ExceptionHandle;
import com.pandora.core.http.BaseObserver;
import com.pandora.modular.home.api.HomeAPIPModel;
import com.pandora.modular.home.bean.HomeBean;
import com.pandora.modular.home.bean.HomeVO;

import io.reactivex.Observable;

/**
 * Created by XIAOHONG
 * Author: XIAOHONG.
 * Date: 2018/5/11.
 */

public class HomeModel {

    public void getData(HomeVO params) {
        Observable<HomeBean> observable = HomeAPIPModel.getInstance().getModelHomeData(params);
        observable.subscribe(new BaseObserver<HomeBean>() {
            @Override
            public void onError(ExceptionHandle.ResponeThrowable e) {

            }

            @Override
            public void onNext(HomeBean homeBean) {

            }
        });
    }

}
