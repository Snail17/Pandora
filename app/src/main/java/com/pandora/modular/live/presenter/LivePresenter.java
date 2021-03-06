package com.pandora.modular.live.presenter;

import com.pandora.modular.live.bean.LiveBean;
import com.pandora.modular.live.bean.LiveVO;
import com.pandora.modular.live.model.LiveModel;

import javax.inject.Inject;

/**
 * Created by Administrator on 2018/5/11.
 */

public class LivePresenter implements LiveContract.Presenter, OnLiveFinishListener {

    private LiveContract.View mView;
    private LiveModel mModel;


    @Inject
    public LivePresenter(LiveContract.View view) {
        mView = view;
        mModel = new LiveModel();
    }

    @Override
    public void getData(LiveVO liveVO) {
        mModel.getLiveData(liveVO, this);
    }


    @Override
    public void onError() {
        mView.onError();
    }

    @Override
    public void onSuccess(String resultJson) {
        mView.setData(resultJson);
    }
}
