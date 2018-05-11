package com.pandora.core.http;


import com.pandora.core.exception.ExceptionHandle;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

/**
 * Created by cain on 2017/8/25.
 */

/**
 *
 * @param <T> s
 */
public class HttpResponseFunc<T> implements Function<Throwable, Observable<T>> {

    @Override
    public Observable<T> apply(@NonNull Throwable throwable) throws Exception {
        return Observable.error(ExceptionHandle.handleException(throwable));
    }
}
