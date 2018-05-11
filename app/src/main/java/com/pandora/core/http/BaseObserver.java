package com.pandora.core.http;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.view.ContextThemeWrapper;


import com.pandora.core.exception.ExceptionHandle;
import com.pandora.core.utils.LogUtils;
import com.pandora.modular.PandoraApplication;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;


/**
 * Created by cain on 2017/8/25.
 *
 * @param <T> d
 */
public abstract class BaseObserver<T> implements Observer<T> {

    private Context context;

    public BaseObserver(Context context) {
        this.context = context;
    }

    public BaseObserver() {

    }

    @Override
    public void onSubscribe(@NonNull Disposable d) {
        LogUtils.e("onSubscribe");
    }

    @Override
    public void onError(@NonNull Throwable e) {
        LogUtils.e(e.getMessage());

        if (e instanceof ExceptionHandle.ResponeThrowable) {
            if (((ExceptionHandle.ResponeThrowable) e).code == 401) {
            }
            onError((ExceptionHandle.ResponeThrowable) e);
        } else {
            onError(new ExceptionHandle.ResponeThrowable(e, ExceptionHandle.ERROR.UNKNOWN));
        }

    }


    @Override
    public void onComplete() {
        LogUtils.e("onComplete");
    }

    /**
     * @param e s
     */
    public abstract void onError(ExceptionHandle.ResponeThrowable e);


    private AlertDialog dialog;
    private boolean dialogIsShow;


}
