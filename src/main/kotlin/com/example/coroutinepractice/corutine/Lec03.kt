package com.example.coroutinepractice.corutine

import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

fun ex4(): Unit = runBlocking {
    val job = async {
        3 + 5
    }

    val eight: Int = job.await() // await : async의 결과를 가져오는 함수
}

fun ex3(): Unit = runBlocking {
    val job1 = launch {
        delay(1000L)
        printWithThread("Job 1")
    }

    job1.join()

    val job2 = launch {
        delay(1000L)
        printWithThread("Job 2")
    }
}

fun ex2(): Unit = runBlocking {
    // 이 코드는 아래의 `job.start()`으로 실행시키지 않으면 실행될 수 없는 상태가 된다.
    val job = launch(start = CoroutineStart.LAZY) {
        printWithThread("Hello launch")
    }

    delay(1_000L)
    job.start()
}

fun ex1() {
    // runBlocking은 안에서 만든 코투린을 포함해 완전히 끝날 때까지 이 스레드 전체를 블로킹 해버리기 때문에
    // START > LAUNCH END > END 순으로 끝나게 됨
    runBlocking {
        printWithThread("START")
        launch {
            delay(2_000L) // yield
            printWithThread("LAUNCH END")
        }
    }

    printWithThread("END")
}

fun main(): Unit = runBlocking {
//    val time = measureTimeMillis {
//        val job1 = async { apiCall1() }
//        val job2 = async { apiCall2() }
//        printWithThread(job1.await() + job2.await())
//    }
//
//    printWithThread("소요 시간 : $time ms")

    val time = measureTimeMillis {
        val job1 = async { apiCall1() }
        val job2 = async { apiCall3(job1.await()) }
        printWithThread(job2.await())
    }

    printWithThread("소요 시간 : $time ms")
}

suspend fun apiCall1(): Int {
    delay(1000L)
    return 1
}

suspend fun apiCall2(): Int {
    delay(1000L)
    return 2
}

suspend fun apiCall3(num: Int): Int {
    delay(1000L)
    return 2 + num
}