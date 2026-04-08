package com.example.coroutinepractice.inheritance

abstract class Animal(
    // val: 기본이 final이므로 하위 클래스에서 override 불가
    // -> species는 생성 시 정해지면 변경할 이유가 없으므로 final로 보호
    val species: String,

    // open val: 하위 클래스에서 override 가능
    // -> 하위 클래스마다 다리 수 계산 로직이 다를 수 있으므로 확장 포인트로 열어둠
    // 예) Penguin은 legCount에 날개 수를 더해서 재정의
    open val legCount: Int
) {
    abstract fun move()

    fun breathe() {
        println("${species}이(가) 숨을 쉽니다")
    }
}
