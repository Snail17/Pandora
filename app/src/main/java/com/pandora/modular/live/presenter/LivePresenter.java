package com.pandora.modular.live.presenter;

import com.pandora.modular.live.model.LiveModel;

import javax.inject.Inject;

/**
 * Created by Administrator on 2018/5/11.
 */

public class LivePresenter {

    private LiveContract.View mView;
    private LiveModel mModel;

    public void setModel(LiveModel model) {
        mModel = model;
    }

    @Inject
    public LivePresenter(LiveContract.View view) {
        mView = view;
    }
}
