package com.example.coroutinepractice.sealed

// sealed class: 하위 클래스의 종류를 컴파일 타임에 제한
// -> when 사용 시 else 없이 모든 경우를 처리할 수 있음
// -> 새로운 하위 클래스가 추가되면 when에서 컴파일 에러 발생 → 누락 방지
sealed class Country(val name: String) {

    // 한국: 추가 정보 없이 단일 인스턴스만 필요 → object (싱글톤)
    object Korea : Country("한국")

    // 미국: 주(state) 정보가 필요 → class (인스턴스마다 다른 값)
    class USA(val state: String) : Country("미국")

    // 일본: 단일 인스턴스만 필요 → object
    object Japan : Country("일본")
}
