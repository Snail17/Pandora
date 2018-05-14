package com.pandora.modular.home.prenster;

import com.pandora.core.base.BasePresenter;
import com.pandora.core.utils.LogUtils;
import com.pandora.modular.home.bean.HomeBean;
import com.pandora.modular.home.bean.HomeVO;
import com.pandora.modular.home.model.HomeModel;

import javax.inject.Inject;

/**
 * Created by XIAOHONG
 * Author: XIAOHONG.
 * Date: 2018/5/11.
 */

public class HomePresenter implements HomeContract.Presenter, OnHomeFinishListener {

    private HomeContract.View mView;
    private HomeModel mModel;

    @Inject
    public HomePresenter(HomeContract.View view) {
        mView = view;
        mModel = new HomeModel();
    }


    @Override
    public void getData(HomeVO homeVO) {
        mModel.getData(homeVO, this);
    }

    @Override
    public void onError() {

    }

    @Override
    public void onSuccess(String homeJson) {
        mView.setData(homeJson);
    }
}
