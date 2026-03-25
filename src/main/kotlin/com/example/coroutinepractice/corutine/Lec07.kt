package com.example.coroutinepractice.corutine

import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.async
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.concurrent.Executors

fun main() {
    CoroutineScope(Dispatchers.Default).launch {
        delay(1_000L)
        printWithThread("Job 1")
    }

    Thread.sleep(1_500L)

    // 새로운 영역을 만들 때, asCoroutineDispatcher() 를 통해 새로운 스레드 풀을 만들어 여러 코루틴을 실행할 수 있다.
    val threadPool = Executors.newSingleThreadExecutor()
    CoroutineScope(threadPool.asCoroutineDispatcher()).launch {
        printWithThread("새로운 코루틴")
    }
}

class AsyncLogic {
    private val scope = CoroutineScope(Dispatchers.Default)

    fun doSomething() {
        scope.launch {
            // 무언가의 코루틴이 시작되어 작업 진행!
        }
    }

    fun destroy() {
        scope.cancel()
    }
}

suspend fun lec07Example1() {
    val job = CoroutineScope(Dispatchers.Default).launch {
        delay(1_000L)
        printWithThread("Job 1")
        coroutineContext + CoroutineName("이름")
    }

    job.join()
}