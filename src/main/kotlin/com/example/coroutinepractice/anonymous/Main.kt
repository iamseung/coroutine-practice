package com.example.coroutinepractice.anonymous

import com.example.coroutinepractice.inheritance.Animal
import com.example.coroutinepractice.inheritance.Swimmable

fun main() {
    // === 익명 클래스: 변수에 담아서 사용 ===
    // 클래스를 따로 만들기 아까운 일회용 구현체
    // object : 로 인터페이스나 추상 클래스를 그 자리에서 바로 구현
    val mysteryAnimal = object : Animal("미확인 생물", 4), Swimmable {
        override val maxSpeed: Int = 3

        override fun move() {
            println("${species}이(가) 기어갑니다")
        }

        override fun swim() {
            println("${species}이(가) 느리게 수영합니다")
        }
    }

    mysteryAnimal.move()
    mysteryAnimal.swim()
    mysteryAnimal.dive()
    mysteryAnimal.breathe()

    println("---")

    // === 익명 클래스: 함수 파라미터로 넘기기 (콜백 패턴) ===
    // 이벤트 처리, 정렬 기준 등 딱 한 번만 쓸 로직을 넘길 때 유용
    moveThenSwim(object : Animal("수달", 4), Swimmable {
        override val maxSpeed: Int = 15

        override fun move() {
            println("${species}이(가) 뛰어갑니다")
        }

        override fun swim() {
            println("${species}이(가) 빠르게 수영합니다")
        }
    })
}

// 파라미터 타입이 인터페이스/추상클래스이면 익명 클래스를 넘길 수 있다
fun moveThenSwim(animal: Swimmable) {
    if (animal is Animal) {
        animal.move()
    }
    animal.swim()
}
