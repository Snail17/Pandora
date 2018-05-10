package com.pandora.modular;

import android.app.Application;


public class PandoraApplication extends Application {

    private static PandoraApplication instance;
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static PandoraApplication getInstance() {
        return instance;
    }
}
