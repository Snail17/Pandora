package com.pandora.modular.home.api;

import com.pandora.core.http.HttpResponseFunc;
import com.pandora.core.http.ServiceGenerator;
import com.pandora.core.utils.RxUtils;
import com.pandora.modular.home.bean.HomeBean;
import com.pandora.modular.home.bean.HomeVO;

import java.util.ArrayList;
import java.util.List;

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
            if (isDebugger) {
                observable = sHomeService.getHomeData(params);
            } else {
                HomeBean homeBean = new HomeBean();
                homeBean.setOnlineService("有问题可以联系客服，联系客服，联系客服，联系客服，本软件无需注册即可登录");
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
}
