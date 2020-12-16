package com.example.eventbusapp;

import com.hwangjr.rxbus.RxBus;
import com.hwangjr.rxbus.annotation.Produce;
import com.hwangjr.rxbus.annotation.Tag;
import com.hwangjr.rxbus.thread.EventThread;

import org.greenrobot.eventbus.EventBus;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;

public class MyService extends Service {
    public MyService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("sss", "post sticky event");
        EventBus.getDefault().postSticky(new MessageEvent());
    }

    @Produce
    public String produceFood() {
        return "This is bread!";
    }

    @Produce(
            thread = EventThread.IO,
            tags = {
                    @Tag("tag_eat")
            }
    )
    public ArrayList<String> produceMoreFood() {
        return new ArrayList<>(Arrays.asList("This is breads!"));
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e("sss", "onStartCommand");
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                RxBus.get().post(produceFood());
                RxBus.get().post("tag_eat", produceMoreFood());
            }
        }, 3000);
        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}