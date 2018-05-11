package com.pandora.modular.live.model;

import com.pandora.core.exception.ExceptionHandle;
import com.pandora.core.http.BaseObserver;
import com.pandora.modular.live.api.LiveAPIModel;
import com.pandora.modular.live.bean.LiveBean;
import com.pandora.modular.live.bean.LiveVO;
import com.pandora.modular.live.presenter.OnLiveFinishListener;

import io.reactivex.Observable;


/**
 * Created by Administrator on 2018/5/11.
 */

public class LiveModel {

    public void getLiveData(LiveVO params, final OnLiveFinishListener listener) {
        Observable<LiveBean> observable = LiveAPIModel.getInstance().getLiveData(params);
        observable.subscribe(new BaseObserver<LiveBean>() {
            @Override
            public void onError(ExceptionHandle.ResponeThrowable e) {
                listener.onError();
            }

            @Override
            public void onNext(LiveBean liveBean) {
                listener.onSuccess(liveBean);
            }
        });
    }
}
