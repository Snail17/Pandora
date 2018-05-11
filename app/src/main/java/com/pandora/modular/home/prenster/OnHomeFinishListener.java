package com.pandora.modular.home.prenster;

import com.pandora.modular.home.bean.HomeBean;

/**
 * Created by Administrator on 2018/5/12.
 */

public interface OnHomeFinishListener {
    void onError();

    void onSuccess(HomeBean homeBean);
}
