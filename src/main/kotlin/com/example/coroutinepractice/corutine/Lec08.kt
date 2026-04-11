package com.example.coroutinepractice.corutine

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

fun main(): Unit = runBlocking {
    // async, 결과값을 반환하는 코루틴 빌더
    val result1 = call1()


    val result2 = call2(result1)


    printWithThread(result2)
}

suspend fun call1(): Int {
    withContext(Dispatchers.IO) {
        Thread.sleep(1_000L)
    }
    return 100
}

suspend fun call2(num: Int): Int {
    withContext(Dispatchers.IO) {
        Thread.sleep(1_000L)
    }
    return num * 100
}

//fun main(): Unit = runBlocking {
//    launch {
//        a()
//        b()
//    }
//
//    launch {
//        c()
//    }
//}
//
//suspend fun a() {
//    printWithThread("A")
//}
//
//suspend fun b() {
//    printWithThread("B")
//}
//
//suspend fun c() {
//    printWithThread("C")
//}

fun test() {
    val list = mutableListOf<Person>()
    list.add(Person("aa", 2))
}

data class Person(val name: String, val age: Int)

fun testt() {
    var list = mutableListOf<String>()

    list = mutableListOf("a", "b", "c")
}