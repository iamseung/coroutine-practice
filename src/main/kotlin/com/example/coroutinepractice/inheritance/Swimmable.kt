package com.example.coroutinepractice.inheritance

interface Swimmable {
    val maxSpeed: Int // 추상 프로퍼티

    fun swim() // 추상 메서드

    fun dive() { // 디폴트 메서드
        println("${maxSpeed}km/h 속도로 잠수합니다")
    }
}
