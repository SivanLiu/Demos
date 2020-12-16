package com.example.eventbusapp;

import com.hwangjr.rxbus.RxBus;
import com.hwangjr.rxbus.annotation.Subscribe;
import com.hwangjr.rxbus.annotation.Tag;
import com.hwangjr.rxbus.thread.EventThread;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.ThreadMode;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RxBus.get().register(this);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                EventBus.getDefault().register(MainActivity.this);
                Log.e("sss", "register last");
            }
        }, 5000);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        RxBus.get().unregister(this);
        EventBus.getDefault().unregister(this);
    }

    @org.greenrobot.eventbus.Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onMessageEvent(MessageEvent event) {
        Log.e("sss", "onMessageEvent");
    }


    @Subscribe
    public void eat(String food) {
        Log.e("sss", "eat " + food);
    }

    @Subscribe(
            thread = EventThread.IO,
            tags = {
                    @Tag("tag_eat")
            }
    )
    public void eatMore(ArrayList<String> foods) {
        Log.e("sss", "eatMoreFood = " + foods);
    }
}