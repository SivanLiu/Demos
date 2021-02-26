package com.example.kotlindemo

fun topLevelFunction() {
    System.out.println("xxxxx")
}

class Sample constructor(var name: String) {
    constructor(name: String, id: Int) : this(name) {

    }

    constructor(name: String, id: Int, age: Int) : this(name, id) {

    }

    companion object {
        init {

        }

        var c: Int = 0;
    }

}

fun  main(){
 println("ssss")
}
