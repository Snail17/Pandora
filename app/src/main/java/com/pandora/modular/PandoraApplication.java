package com.pandora.modular;

import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;


public class PandoraApplication extends MultiDexApplication {

    private static PandoraApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        MultiDex.install(this);
        instance = this;
    }

    public static PandoraApplication getInstance() {
        return instance;
    }
}
