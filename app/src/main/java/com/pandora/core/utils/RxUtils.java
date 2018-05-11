package com.pandora.core.utils;



import com.pandora.core.base.ILoading;

import org.reactivestreams.Publisher;

import io.reactivex.Flowable;
import io.reactivex.FlowableTransformer;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Rx工具类
 * Created by mac on 17/7/20.
 */

public class RxUtils {


    /**
     * 线程检测
     * @param <T> d
     * @return d
     */
    public static <T> ObservableTransformer<T, T> io_main() {
        return  new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(@NonNull Observable<T> upstream) {
                return  upstream.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    /**
     * 显示 loading
     * @param view d
     * @param <T> d
     * @return d
     */
    public static <T> ObservableTransformer<T,T> showLoading(final ILoading view) {
        return showLoading(view,null);
    }


    /**
     * loading 带关闭
     * @param view s
     * @param <T> s
     * @return s
     */
    public static <T> ObservableTransformer<T,T> showLoadingWithClose(final ILoading view) {
        return showLoadingWithClose(view,null);
    }

    /**
     * loading不带关闭
     * @param view d
     * @param tip d
     * @param <T> d
     * @return d
     */
    public static  <T> ObservableTransformer<T, T> showLoading(final ILoading view, final String tip){
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(@NonNull Observable<T> upstream) {
                return upstream.doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(@NonNull Disposable disposable) throws Exception {
                        view.showLoading(tip);
                    }
                }).doOnTerminate(new Action() {
                    @Override
                    public void run() throws Exception {
                        view.hideLoading();
                    }
                }).observeOn(AndroidSchedulers.mainThread());
            }
        };
    }



    /**
     * loading 带关闭
     * @param view d
     * @param tip d
     * @param <T> d
     * @return d
     */
    public static  <T> ObservableTransformer<T, T> showLoadingWithClose(final ILoading view, final String tip){
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(@NonNull Observable<T> upstream) {
                return upstream.doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(@NonNull Disposable disposable) throws Exception {
                        view.showLoadingWithClose(tip);
                    }
                }).doOnTerminate(new Action() {
                    @Override
                    public void run() throws Exception {
                        view.hideLoading();
                    }
                }).observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    /**
     * 构造假数据并延时
     * @param value s
     * @param <T> t
     * @return s
     */
    public static <T> Observable<T> demo(final T value){
        return Observable.just(value).flatMap(new Function<T, ObservableSource<T>>() {
            @Override
            public ObservableSource<T> apply(@NonNull T t) throws Exception {
//                Thread.sleep(3000);
                return Observable.just(t);
            }
        });
    }


    /**
     *
     * @param view d
     * @param tip d
     * @param <T> d
     * @return d
     */
    public static <T> FlowableTransformer<T, T> flshow(final ILoading view, final String tip){
        return  new FlowableTransformer<T, T>() {
            @Override
            public Publisher<T> apply(@NonNull Flowable<T> upstream) {
                return upstream;
            }
        };
    }



}
