package com.pandora.core.base;

/**
 * Created by Administrator on 2018/5/14.
 */

public interface OnFinishListener {
    void onError();

    void onSuccess(String resultJson);
}
