package com.example.kotlindemo

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.example.kotlindemo.topLevelFunction


class MainActivity: AppCompatActivity(), Impl {
    lateinit var tvText: TextView;
    val test: String = "texttest+"
    var myName:String?="rengwuxian"
    var number:Int = 1
    var c:Char= 'c'
    var b:Boolean = true
    var array:IntArray = intArrayOf(1, 2)
    var str:String = "String"
    var a:Int = 1;
    var bb:Int?= 2
    var list:List<Int>  = listOf(1, 2)

    var textViews:List<out TextView>
    var textViews2:List<in TextView>

    val producer:Producer<out TextView> = Producer<Button>()



    final override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tvText = findViewById(R.id.tvtext);
        tvText.setText(test)

    }

}