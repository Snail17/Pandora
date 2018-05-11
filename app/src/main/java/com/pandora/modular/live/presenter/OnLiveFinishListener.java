package com.pandora.modular.live.presenter;

import com.pandora.modular.live.bean.LiveBean;

/**
 * Created by Administrator on 2018/5/12.
 */

public interface OnLiveFinishListener {
    void onError();

    void onSuccess(LiveBean liveBean);
}
