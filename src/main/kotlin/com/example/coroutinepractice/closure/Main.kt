package com.example.coroutinepractice.closure

// =================================================================
// Closure(클로저): 람다가 자신이 선언된 스코프의 변수를 "캡처"하여
// 해당 스코프가 끝난 이후에도 변수를 기억하고 접근/수정할 수 있는 기능
//
// Java에서는 람다가 외부 변수를 참조하려면 effectively final이어야 하고
// 수정이 불가능하지만, Kotlin은 캡처한 변수를 자유롭게 수정할 수 있다.
//
// 이것이 가능한 이유:
// Kotlin 컴파일러가 캡처되는 변수를 Ref 객체(IntRef, ObjectRef 등)로 감싸서
// 힙(Heap)에 저장하기 때문이다.
// 즉, 람다는 변수 자체가 아니라 Ref 객체의 참조를 캡처하므로
// 원본 변수와 람다가 같은 Ref 객체를 바라보게 된다.
//
// 예) var count = 0 → 컴파일 시 IntRef count = new IntRef(0) 으로 변환
//     람다 안의 count++ → count.element++ 으로 변환
// =================================================================

fun main() {
    println("=== 1. 기본 Closure: 외부 변수 캡처 및 수정 ===")
    basicClosure()

    println()
    println("=== 2. 함수를 반환하는 Closure: 변수가 스코프 밖에서도 생존 ===")
    counterClosure()

    println()
    println("=== 3. 실전 활용: 콜백에서 외부 상태 캡처 ===")
    callbackClosure()

    println()
    println("=== 4. 실전 활용: 필터 조건을 외부에서 캡처 ===")
    filterClosure()
}

// 1. 기본 Closure
// 람다가 외부 변수 count를 캡처하여 수정한다.
// Java였다면 count가 effectively final이 아니므로 컴파일 에러가 난다.
// Kotlin은 count를 IntRef로 감싸서 힙에 올리기 때문에 수정이 가능하다.
fun basicClosure() {
    var count = 0
    val increment = { count++ }

    increment()
    increment()
    increment()
    println("count = $count") // 3
}

// 2. 함수를 반환하는 Closure
// makeCounter()가 끝나면 지역변수 count는 스택에서 사라져야 하지만,
// 반환된 람다가 count의 Ref 객체를 캡처하고 있으므로
// GC 대상이 되지 않고 힙에서 계속 유지된다.
// → 호출할 때마다 같은 count를 기억하고 증가시킨다.
fun makeCounter(): () -> Int {
    var count = 0
    return { ++count }
}

fun counterClosure() {
    val counterA = makeCounter()
    val counterB = makeCounter() // counterA와 독립적인 별도의 count를 가짐

    println("counterA: ${counterA()}") // 1
    println("counterA: ${counterA()}") // 2
    println("counterA: ${counterA()}") // 3
    println("counterB: ${counterB()}") // 1 (counterA와 별개)
    println("counterB: ${counterB()}") // 2
}

// 3. 콜백에서 외부 상태 캡처
// 비동기 처리나 이벤트 핸들러에서 결과를 외부 변수에 저장할 때 흔히 사용된다.
// onSuccess 람다가 results 리스트를 캡처하여 직접 수정한다.
fun fetchData(onSuccess: (String) -> Unit) {
    val data = listOf("사과", "바나나", "포도")
    for (item in data) {
        onSuccess(item)
    }
}

fun callbackClosure() {
    val results = mutableListOf<String>()

    // 람다가 외부의 results를 캡처 → 콜백 안에서 직접 추가
    fetchData { item ->
        results.add("과일: $item")
    }

    results.forEach { println(it) }
}

// 4. 필터 조건을 외부에서 캡처
// filter의 람다가 minAge, maxAge를 캡처하여 조건으로 사용한다.
// 파라미터로 넘기지 않았는데도 접근 가능한 이유가 바로 Closure이다.
data class Person(val name: String, val age: Int)

fun filterClosure() {
    val people = listOf(
        Person("철수", 15),
        Person("영희", 25),
        Person("민수", 35),
        Person("지수", 20)
    )

    val minAge = 18
    val maxAge = 30

    // 람다 안에서 minAge, maxAge를 직접 참조 → Closure
    // 별도의 파라미터 없이도 외부 변수에 접근 가능
    val adults = people.filter { it.age in minAge..maxAge }

    adults.forEach { println("${it.name} (${it.age}세)") }
}
