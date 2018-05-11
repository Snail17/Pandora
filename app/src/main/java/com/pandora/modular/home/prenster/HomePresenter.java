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

public class HomePresenter implements HomeContract.Presenter {

    private HomeContract.View mView;
    private HomeModel mModel;

    public void setModel(HomeModel model) {
        mModel = model;
    }

    @Inject
    public HomePresenter(HomeContract.View view) {
        mView = view;
    }


    @Override
    public void getData() {
        HomeBean data = mModel.getData(new HomeVO());
        mView.setData(data);
    }

}
