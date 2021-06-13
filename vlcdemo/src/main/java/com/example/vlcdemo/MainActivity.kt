package com.example.vlcdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.vlc.lib.VlcPlayer
import com.vlc.lib.VlcVideoView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        VlcPlayer
    }
}