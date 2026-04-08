package com.example.coroutinepractice.inheritance

class Duck(
    species: String
) : Animal(species, 2), Swimmable, Flyable {

    // Swimmable 인터페이스의 추상 프로퍼티(val maxSpeed: Int)를 구현
    // 인터페이스의 프로퍼티는 기본이 open이므로 별도의 open 키워드 없이도 override 가능
    // (추상 클래스의 val은 기본이 final이라 open을 붙여야 하는 것과 다름)
    override val maxSpeed: Int = 10

    override fun move() {
        println("${species}이(가) 뒤뚱뒤뚱 걸어갑니다")
    }

    override fun swim() {
        println("${species}이(가) 수영합니다")
    }

    override fun fly() {
        println("${species}이(가) 날아갑니다")
    }
}
