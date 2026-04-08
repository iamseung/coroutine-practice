package com.example.coroutinepractice.inheritance

fun main() {
    val duck = Duck("오리")
    duck.move()
    duck.swim()
    duck.fly()
    duck.dive()
    duck.land()
    duck.breathe()

    println("---")

    val penguin = Penguin("펭귄")
    penguin.move()
    penguin.swim()
    penguin.dive()
    penguin.breathe()
    println("펭귄 다리 수: ${penguin.legCount}")
}
