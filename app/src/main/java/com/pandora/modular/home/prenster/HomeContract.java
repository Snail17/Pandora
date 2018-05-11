package com.pandora.modular.home.prenster;

import com.pandora.core.base.BasePresenter;
import com.pandora.core.base.BaseView;
import com.pandora.modular.home.bean.HomeBean;

/**
 * Created by XIAOHONG
 * Author: XIAOHONG.
 * Date: 2018/5/11.
 */

public interface HomeContract {
    interface View extends BaseView<Presenter> {
        void setData(HomeBean data);
    }

    interface Presenter extends BasePresenter<View> {
        void getData();

    }
}
