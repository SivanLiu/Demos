package com.example.buglyapplication;

import com.tencent.bugly.Bugly;
import com.tencent.bugly.beta.Beta;

import android.app.Application;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
//        Beta.autoCheckAppUpgrade = true;
        Bugly.init(getApplicationContext(), "b3427e6cc5", false);
    }
}