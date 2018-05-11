package com.pandora.modular.live.presenter;

import com.pandora.modular.live.bean.LiveBean;
import com.pandora.modular.live.bean.LiveVO;
import com.pandora.modular.live.model.LiveModel;

import javax.inject.Inject;

/**
 * Created by Administrator on 2018/5/11.
 */

public class LivePresenter implements LiveContract.Presenter {

    private LiveContract.View mView;
    private LiveModel mModel;

    public void setModel(LiveModel model) {
        mModel = model;
    }

    @Inject
    public LivePresenter(LiveContract.View view) {
        mView = view;
    }

    @Override
    public void getData() {
        LiveBean liveBean = mModel.getLiveData(new LiveVO());
        mView.setData(liveBean);
    }
}
