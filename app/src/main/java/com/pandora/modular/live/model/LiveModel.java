package com.pandora.modular.live.model;

import com.pandora.modular.live.bean.LiveBean;
import com.pandora.modular.live.bean.LiveVO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/5/11.
 */

public class LiveModel {

    public LiveBean getLiveData(LiveVO params) {
        LiveBean liveBean = new LiveBean();
        List<LiveBean.LiveData> liveData = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            LiveBean.LiveData data = liveBean.new LiveData();
            data.setName("item" + i);
            data.setRtmp(3 * i + "999");
            liveData.add(data);
        }
        liveBean.setData(liveData);
        return liveBean;
    }
}
