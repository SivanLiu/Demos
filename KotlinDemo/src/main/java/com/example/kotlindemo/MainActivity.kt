package com.example.kotlindemo

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.example.kotlindemo.top.CONST_SECOND_NUMBER
import com.example.kotlindemo.top.Sample
import com.example.kotlindemo.top.topLevelFunction


class MainActivity : AppCompatActivity(), Impl {
    lateinit var tvText: TextView;
    val test: String = "texttest+"
    var myName: String? = "rengwuxian"
    var number: Int = 1
    var c: Char = 'c'
    var b: Boolean = true
    var array: IntArray = intArrayOf(1, 2)
    var str: String = "String"
    var a: Int = 1;
    var bb: Int? = 2
    var list: List<Int> = listOf(1, 2)
    var strSet = setOf("a", "b", "c")
    var map = mapOf("key1" to 1, "key2" to 2, "key3" to 3)

    var sequence1 = sequenceOf("a", "b", "c")


    val listener = object : ViewPager.SimpleOnPageChangeListener() {
        override fun onPageSelected(position: Int) {
            super.onPageSelected(position)

            map.get("key1")

            map.toMutableMap()["key1"] = 3

            list.asSequence()

            val sequence = generateSequence(0) { it + 1 }
        }
    }

    var textViews:List<out TextView>
    var textViews2:List<in TextView>

    val producer:Producer<out TextView> = Producer<Button>()



    final override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tvText = findViewById(R.id.tvtext);
        tvText.setText(test)
        topLevelFunction()
        CONST_SECOND_NUMBER
        Sample.CONST_NUMBER
    }
}