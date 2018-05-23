package com.pandora.modular.home.prenster;

import com.pandora.modular.home.fragment.HomeHotFragment;

import dagger.Component;

/**
 * Created by Administrator on 2018/5/11.
 */

@Component(modules = HomeModule.class)
public interface HomeComponent {
    void inject(HomeHotFragment fragment);
}
