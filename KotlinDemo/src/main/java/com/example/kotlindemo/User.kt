package com.example.kotlindemo

import android.app.Person

class User(name: String, var id: String) {
    init {

    }

    constructor(person: Person) : this("", "") {

    }

    var name: String = name

    companion object {
        val anotherString = "Another String"
    }

    fun area(width: Int, height: Int) = width * height

    fun sayHi(name: String = "world") {
        println("Hi " + name)
    }

    fun sayHi(name:String = "world", age:Int, isStudent:Boolean = true, isFat:Boolean = true,
    isTall:Boolean = true){
    }

    fun call(){
        sayHi(name = "wo", age = 21, isStudent = false, isFat = false, isTall = true)
    }



}