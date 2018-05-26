package com.pandora.modular.home.prenster;

import com.pandora.core.base.BasePresenter;
import com.pandora.core.base.BaseView;
import com.pandora.modular.home.bean.HomeBean;
import com.pandora.modular.home.bean.HomeVO;

/**
 * Created by XIAOHONG
 * Author: XIAOHONG.
 * Date: 2018/5/11.
 */

public interface HomeContract {
    interface View extends BaseView<Presenter> {
        void setData(String homeJson);
        void onErrorData();
    }

    interface Presenter extends BasePresenter<View> {
        void getData(HomeVO homeVO);

    }
}
