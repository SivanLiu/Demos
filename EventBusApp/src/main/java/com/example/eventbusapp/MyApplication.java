package com.example.eventbusapp;

import android.app.Application;
import android.content.Intent;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        startService(new Intent(this, MyService.class));
    }
}