package com.pandora.core.base;

import android.support.v4.app.Fragment;

import com.pandora.core.utils.widget.CustomLoadingUtil;

/**
 * Created by Administrator on 2018/5/8.
 */

public class BaseFragment extends Fragment {

    public void showLoading() {
        CustomLoadingUtil.showLoading();
    }

    public void hideLading() {
        CustomLoadingUtil.hideDialog();
    }

}
