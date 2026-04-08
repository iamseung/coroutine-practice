package com.example.coroutinepractice.sealed

fun main() {
    // 하위 타입이 정해져 있으므로 when에서 else 없이 모든 경우를 처리
    val countries = listOf(
        Country.Korea,
        Country.USA("California"),
        Country.USA("New York"),
        Country.Japan
    )

    for (country in countries) {
        println(getGreeting(country))
    }
}
