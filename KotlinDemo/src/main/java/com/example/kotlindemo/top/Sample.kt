package com.example.kotlindemo.top

import android.util.Log

fun topLevelFunction() {
    System.out.println("xxxxx")
    Log.e("ss", "top");
}

class Sample {
    companion object {
        init {

        }

        var c: Int = 0
        const val CONST_NUMBER = 1
    }
}

const val CONST_SECOND_NUMBER = 2

open class A {
    open fun method() {

    }
}

interface B {
    fun interfaceMethod()
}

object c : A(), B {
    override fun interfaceMethod() {
        TODO("Not yet implemented")
    }

    override fun method() {
        super.method()
    }
}