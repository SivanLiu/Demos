package com.example.pmdemo

import android.content.Context
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Handler(Looper.getMainLooper()).postDelayed({
            /* Create an Intent that will start the Menu-Activity. */
           getPackageList(this)
        }, 3000)
    }

    private fun getPackageList(context: Context) {
        var count = 0
        val packageManager = context.packageManager
        val uid = 1000
        var v2: Array<String>?
        while (uid <= 1999) {
            v2 = packageManager.getPackagesForUid(uid)
            if (v2 != null && v2.isNotEmpty()) {
                for (item: String in v2) {
                    try {
                        val v6: PackageInfo = packageManager.getPackageInfo(item, 0) ?: break

                        val v7 = packageManager.getApplicationLabel(packageManager.getApplicationInfo(v6.packageName, PackageManager.GET_META_DATA))
                        count++
//                        Log.e("TAG", "应用名称 = " + v7.toString() + " (" + v6.packageName + ")");
                    } catch (e: PackageManager.NameNotFoundException) {
                        e.printStackTrace()
                    }
                }
            }
        }

        Log.e("TAG", "应用个数 $count")
    }

}