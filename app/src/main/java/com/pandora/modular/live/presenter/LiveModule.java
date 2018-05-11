package com.pandora.modular.live.presenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2018/5/11.
 */

@Module
public class LiveModule {
    private LiveContract.View mView;

    public LiveModule(LiveContract.View view) {
        mView = view;
    }

    @Provides
    LiveContract.View providesPresenter() {
        return mView;
    }
}
