package com.pandora.modular.purchase.model;

import com.google.gson.Gson;
import com.pandora.core.utils.webservices.QueryAddressTask;
import com.pandora.modular.purchase.bean.PurchaseVO;
import com.pandora.modular.purchase.fragment.PurchaseFragment;

/**
 * Created by Administrator on 2018/5/13.
 */

public class PurchaseModel {


    public void getData(PurchaseVO purchaseVO, final PurchaseFragment.OnPurchaseFinishListener listener) {

        QueryAddressTask queryAddressTask = new QueryAddressTask();
        queryAddressTask.setListener(listener);
        String paramValue = new Gson().toJson(purchaseVO);
        //启动后台任务
        queryAddressTask.execute(paramValue);
//
//        Observable<HomeBean> observable = HomeAPIPModel.getInstance().getModelHomeData(new PurchaseVO());
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
