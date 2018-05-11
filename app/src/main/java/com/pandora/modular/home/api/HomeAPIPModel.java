package com.pandora.modular.home.api;

import com.pandora.core.http.HttpResponseFunc;
import com.pandora.core.http.ServiceGenerator;
import com.pandora.core.utils.RxUtils;
import com.pandora.modular.home.bean.HomeBean;
import com.pandora.modular.home.bean.HomeVO;

import io.reactivex.Observable;

/**
 * Created by Administrator on 2018/5/11.
 */

public class HomeAPIPModel {

    private static HomeAPI sHomeService;
    private static HomeAPIPModel sHomeModel;
    private boolean isDebugger = false;

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
            if (!isDebugger) {
                observable = sHomeService.getHomeData(params);
            } else {
                HomeBean bean = new HomeBean();
                observable = RxUtils.demo(bean);
            }
        }
        return observable.compose(RxUtils.<HomeBean>io_main())
                .onErrorResumeNext(new HttpResponseFunc<HomeBean>());

    }
}
