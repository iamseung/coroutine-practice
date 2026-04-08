package com.example.coroutinepractice.sealed

fun getGreeting(country: Country): String = when (country) {
    // sealed class이므로 else가 필요 없다
    // 만약 Country에 새로운 하위 클래스를 추가하면 여기서 컴파일 에러 발생
    is Country.Korea -> "안녕하세요, ${country.name}!"
    is Country.USA -> "Hello from ${country.state}, ${country.name}!"
    is Country.Japan -> "こんにちは, ${country.name}!"
}
