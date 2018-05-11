package com.pandora.modular.live.api;

import com.pandora.core.http.HttpResponseFunc;
import com.pandora.core.http.ServiceGenerator;
import com.pandora.core.utils.RxUtils;
import com.pandora.modular.live.bean.LiveBean;
import com.pandora.modular.live.bean.LiveVO;

import io.reactivex.Observable;

/**
 * Created by Administrator on 2018/5/12.
 */

public class LiveModel {
    private static LiveAPI sServices;
    private static LiveModel sModel;

    private boolean isDebugger = false;

    private LiveModel() {
    }

    private static LiveModel getInstance() {
        if (sModel == null) {
            synchronized (LiveModel.class) {
                if (sModel == null) {
                    sServices = ServiceGenerator.createService(LiveAPI.class);
                    sModel = new LiveModel();
                }
            }
        }
        return sModel;
    }

    public Observable<LiveBean> getLiveData(LiveVO params) {
        Observable<LiveBean> observable = null;
        if (isDebugger) {
            LiveBean bean = new LiveBean();
            observable = RxUtils.demo(bean);
        } else {
            observable = sServices.getLiveData(params);
        }
        return observable.compose(RxUtils.<LiveBean>io_main())
                .onErrorResumeNext(new HttpResponseFunc<LiveBean>());
    }
}
