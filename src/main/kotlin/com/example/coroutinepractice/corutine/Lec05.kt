package com.example.coroutinepractice.corutine

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main(): Unit = runBlocking {
    val exceptionHandler = CoroutineExceptionHandler { _, _ ->
        printWithThread("예외")
    }

    val job = CoroutineScope(Dispatchers.Default).launch(exceptionHandler) {
        throw IllegalArgumentException()
    }

    delay(1_000L)
}

fun lec5_ex3(): Unit = runBlocking {
    val job = async(SupervisorJob()) {
        throw IllegalArgumentException()
    }

    delay(1_000L)
}

/*
    아래의 코드를 실행시키면 두번째 코루틴의 IllegalArgumentException 이 발생한다.
 */
// 첫번째 코루틴, runBlocking
fun lec5_ex2(): Unit = runBlocking {
    // 두번째 코루틴, CoroutineScope
    val job = CoroutineScope(Dispatchers.Default).async {
        throw IllegalArgumentException()
    }

    delay(1_000L)
    job.await()
}

fun lec5_ex1() = runBlocking {
    val job1 = CoroutineScope(Dispatchers.Default).launch {
        delay(1_000L)
        printWithThread("Job 1")
    }

    val job2 = CoroutineScope(Dispatchers.Default).launch {
        delay(1_000L)
        printWithThread("Job 2")
    }
}