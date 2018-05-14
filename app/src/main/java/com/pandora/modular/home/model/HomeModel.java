package com.pandora.modular.home.model;

import com.google.gson.Gson;
import com.pandora.core.utils.webservices.QueryAddressTask;
import com.pandora.modular.home.bean.HomeVO;
import com.pandora.modular.home.prenster.OnHomeFinishListener;

/**
 * Created by XIAOHONG
 * Author: XIAOHONG.
 * Date: 2018/5/11.
 */

public class HomeModel {


    public void getData(HomeVO homeVO, final OnHomeFinishListener listener) {

        QueryAddressTask queryAddressTask = new QueryAddressTask();
        queryAddressTask.setListener(listener);
        String paramValue = new Gson().toJson(homeVO);
        //启动后台任务
        queryAddressTask.execute(paramValue);
//
//        Observable<HomeBean> observable = HomeAPIPModel.getInstance().getModelHomeData(new HomeVO());
//        observable.subscribe(new BaseObserver<HomeBean>() {
//            @Override
//            public void onError(ExceptionHandle.ResponeThrowable e) {
//                listener.onError();
//            }
//
//            @Override
//            public void onNext(HomeBean homeBean) {
//                listener.onSuccess(homeBean);
//            }
//        });
    }


}
