package com.example.wydemo

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.shanghainengcheng.wysdk.gotoWYClinicWithVideo
import com.shanghainengcheng.wysdk.gotoWYOrderList
import com.shanghainengcheng.wysdk.wyLogin

class MainActivity : AppCompatActivity() {
    lateinit var btLogin: Button
    lateinit var btStartVideo: Button
    lateinit var btOrderList: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btLogin = findViewById(R.id.bt_login)
        btStartVideo = findViewById(R.id.bt_start_video)
        btOrderList = findViewById(R.id.bt_order_list)

        btLogin.setOnClickListener {
            Log.e("sss", "login...");
            wyLogin(this, "18867105717", "AmeLkBe1")
        }

        btStartVideo.setOnClickListener {
            Log.e("sss", "start video...");
            gotoWYClinicWithVideo(this)
        }

        btOrderList.setOnClickListener {
            Log.e("sss", "order list...");
            gotoWYOrderList(this)
        }
    }
}