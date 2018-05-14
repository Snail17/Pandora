package com.pandora.modular.live.model;

import com.google.gson.Gson;
import com.pandora.core.utils.webservices.QueryAddressTask;
import com.pandora.modular.live.bean.LiveVO;
import com.pandora.modular.live.presenter.OnLiveFinishListener;


/**
 * Created by Administrator on 2018/5/11.
 */

public class LiveModel {

    public void getLiveData(LiveVO liveVO, final OnLiveFinishListener listener) {
        QueryAddressTask queryAddressTask = new QueryAddressTask();
        queryAddressTask.setListener(listener);
        String paramValue = new Gson().toJson(liveVO);

        //启动后台任务
        queryAddressTask.execute(paramValue);
    }

}
