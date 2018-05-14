package com.pandora.modular;

import android.app.Application;
import android.content.Context;

import io.vov.vitamio.Vitamio;


public class PandoraApplication extends Application {

    private static PandoraApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
//        MultiDex.install(this);
    }

    public static PandoraApplication getInstance() {
        return instance;
    }
}
