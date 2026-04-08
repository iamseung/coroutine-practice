package com.example.coroutinepractice.inheritance

class Penguin(
    species: String
) : Animal(species, 2), Swimmable {

    private val wingCount: Int = 2

    override val maxSpeed: Int = 20

    override val legCount: Int
        get() = super.legCount + wingCount

    override fun move() {
        println("${species}이(가) 뒤뚱뒤뚱 걸어갑니다")
    }

    override fun swim() {
        println("${species}이(가) 빠르게 수영합니다")
    }
}
