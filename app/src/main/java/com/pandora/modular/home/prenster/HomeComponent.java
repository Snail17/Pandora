package com.pandora.modular.home.prenster;

import com.pandora.modular.home.fragment.HomeFragment;

import dagger.Component;

/**
 * Created by Administrator on 2018/5/11.
 */

@Component(modules = HomeModule.class)
public interface HomeComponent {
    public void inject(HomeFragment fragment);
}
