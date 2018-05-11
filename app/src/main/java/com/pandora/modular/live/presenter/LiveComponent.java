package com.pandora.modular.live.presenter;

import com.pandora.modular.live.activity.LiveBroadcastActivity;

import dagger.Component;

/**
 * Created by Administrator on 2018/5/11.
 */

@Component(modules = LiveModule.class)
public interface LiveComponent {
    void inject(LiveBroadcastActivity activity);
}
