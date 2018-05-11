package com.pandora.modular.home.prenster;

import com.pandora.core.base.BasePresenter;
import com.pandora.modular.home.bean.HomeBean;
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

    @Inject
    public HomePresenter(HomeContract.View view) {
        mView = view;
    }


    @Override
    public HomeBean getData() {
        return null;
    }

    @Override
    public void setData(HomeBean bean) {

    }
}
