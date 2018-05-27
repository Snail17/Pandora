package com.pandora.modular.live.presenter;

import com.pandora.core.base.BasePresenter;
import com.pandora.core.base.BaseView;
import com.pandora.modular.home.bean.HomeBean;
import com.pandora.modular.live.bean.LiveBean;
import com.pandora.modular.live.bean.LiveVO;
import com.pandora.modular.live.model.LiveModel;

/**
 * Created by Administrator on 2018/5/11.
 */

public interface LiveContract {

    interface View extends BaseView<LiveContract.Presenter> {
        void setData(String liveJson);
        void onError();
    }

    interface Presenter extends BasePresenter<LiveContract.View> {
        void getData(LiveVO liveVO);
    }
}
