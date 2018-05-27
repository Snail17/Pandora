package com.pandora.modular.home.prenster;

import com.pandora.modular.home.fragment.HomeAnchorFragment;

import dagger.Component;

/**
 * Created by Administrator on 2018/5/27.
 */
@Component(modules = HomeModule.class)
public interface HomeAnchorComponent {
    void inject(HomeAnchorFragment fragment);
}
