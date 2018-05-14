package com.pandora.modular.purchase.model;

import android.os.AsyncTask;

import com.google.gson.Gson;
import com.pandora.core.utils.LogUtils;
import com.pandora.core.utils.webservices.QueryAddressTask;
import com.pandora.modular.home.bean.HomeBean;
import com.pandora.modular.home.prenster.OnHomeFinishListener;
import com.pandora.modular.purchase.bean.PurchaseBean;
import com.pandora.modular.purchase.bean.PurchaseVO;
import com.pandora.modular.purchase.fragment.PurchaseFragment;

import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

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
