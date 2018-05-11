package com.pandora.modular.home.model;

import com.pandora.core.exception.ExceptionHandle;
import com.pandora.core.http.BaseObserver;
import com.pandora.modular.home.api.HomeAPIPModel;
import com.pandora.modular.home.bean.HomeBean;
import com.pandora.modular.home.bean.HomeVO;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

/**
 * Created by XIAOHONG
 * Author: XIAOHONG.
 * Date: 2018/5/11.
 */

public class HomeModel {

    public HomeBean getData(HomeVO params) {
//        Observable<HomeBean> observable = HomeAPIPModel.getInstance().getModelHomeData(params);
//        observable.subscribe(new BaseObserver<HomeBean>() {
//            @Override
//            public void onError(ExceptionHandle.ResponeThrowable e) {
//                return;
//            }
//
//            @Override
//            public void onNext(HomeBean homeBean) {
//
//            }
//        });

        HomeBean homeBean = new HomeBean();
        List<HomeBean.HomeData> data = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            HomeBean.HomeData homeData = homeBean.new HomeData();
            homeData.setAnchor(2 * i + "");
            homeData.setName("item" + i);
            data.add(homeData);
        }
        homeBean.setData(data);
        return homeBean;
    }

}
