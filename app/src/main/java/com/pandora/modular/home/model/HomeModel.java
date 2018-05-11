package com.pandora.modular.home.model;

import com.pandora.core.exception.ExceptionHandle;
import com.pandora.core.http.BaseObserver;
import com.pandora.core.utils.LogUtils;
import com.pandora.modular.home.api.HomeAPIPModel;
import com.pandora.modular.home.bean.HomeBean;
import com.pandora.modular.home.bean.HomeVO;
import com.pandora.modular.home.prenster.OnHomeFinishListener;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

/**
 * Created by XIAOHONG
 * Author: XIAOHONG.
 * Date: 2018/5/11.
 */

public class HomeModel {

    public void getData(HomeVO params, final OnHomeFinishListener listener) {
        Observable<HomeBean> observable = HomeAPIPModel.getInstance().getModelHomeData(params);
        observable.subscribe(new BaseObserver<HomeBean>() {
            @Override
            public void onError(ExceptionHandle.ResponeThrowable e) {
                listener.onError();
            }

            @Override
            public void onNext(HomeBean homeBean) {
                listener.onSuccess(homeBean);
            }
        });
    }

}
