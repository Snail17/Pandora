package com.pandora.modular.home.prenster;

import com.pandora.modular.home.bean.HomeParam;
import com.pandora.modular.home.model.HomeModel;

import javax.inject.Inject;

/**
 * Created by XIAOHONG
 * Author: XIAOHONG.
 * Date: 2018/5/11.
 */

public class HomePresenter implements HomeContract.Presenter, OnHomeHotFinishListener {

    private HomeContract.View mView;
    private HomeModel mModel;

    @Inject
    public HomePresenter(HomeContract.View view) {
        mView = view;
        mModel = new HomeModel();
    }


    @Override
    public void getData(HomeParam homeVO) {
        mModel.getData(homeVO, this);
    }

    @Override
    public void onError() {
        mView.onErrorData();
    }

    @Override
    public void onSuccess(String homeJson) {
        mView.setData(homeJson);
    }
}
