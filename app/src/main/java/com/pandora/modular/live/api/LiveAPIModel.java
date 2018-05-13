package com.pandora.modular.live.api;

import com.pandora.core.http.HttpResponseFunc;
import com.pandora.core.http.ServiceGenerator;
import com.pandora.core.utils.RxUtils;
import com.pandora.modular.live.bean.LiveBean;
import com.pandora.modular.live.bean.LiveVO;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

/**
 * Created by Administrator on 2018/5/12.
 */

public class LiveAPIModel {
    private static LiveAPI sServices;
    private static LiveAPIModel sModel;

    private boolean isDebugger = false;

    private LiveAPIModel() {
    }

    public static LiveAPIModel getInstance() {
        if (sModel == null) {
            synchronized (LiveAPIModel.class) {
                if (sModel == null) {
                    sServices = ServiceGenerator.createService(LiveAPI.class);
                    sModel = new LiveAPIModel();
                }
            }
        }
        return sModel;
    }

    public Observable<LiveBean> getLiveData(LiveVO params) {
        Observable<LiveBean> observable = null;
        if (isDebugger) {
            observable = sServices.getLiveData(params);
        } else {
            LiveBean liveBean = new LiveBean();
            List<LiveBean.LiveData> liveData = new ArrayList<>();
            for (int i = 0; i < 30; i++) {
                LiveBean.LiveData data = liveBean.new LiveData();
                data.setTitle("item" + i);
                data.setPep(3 * i + "999");
                liveData.add(data);
            }
            liveBean.setData(liveData);
            observable = RxUtils.demo(liveBean);
        }
        return observable.compose(RxUtils.<LiveBean>io_main())
                .onErrorResumeNext(new HttpResponseFunc<LiveBean>());
    }
}
