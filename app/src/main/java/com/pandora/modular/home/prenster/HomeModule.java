package com.pandora.modular.home.prenster;

import com.pandora.modular.home.prenster.HomeContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2018/5/11.
 */

@Module
public class HomeModule {
    private HomeContract.View view;

    public HomeModule(HomeContract.View view) {
        this.view = view;
    }

    @Provides
    HomeContract.View provideHomeView() {
        return view;
    }
}
