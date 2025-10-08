package com.project.immersionstudy.kotlin.Type

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class StringTest : FunSpec({
    test("문자열을 for문으로 출력할 수 있다.") {
        val str = "abcd"
        for( s in str ) {
            println(s)
        }
    }

    test("문자열 연산은 새로운 문자열을 반환한다.") {
        val str = "abcd"
        str.uppercase() shouldBe "ABCD"
        str shouldBe "abcd"
    }

    test("문자열과 숫자타입을 더하려면 문자열이 먼저 나와야한다.") {
        val str = "abc" + 1
//        val str2 = 1 + "abc" 얘는 안됨

        str shouldBe "abc1"
    }

    test("문자열의 없는 idx를 참조하면 예외가 터진다.") {
        val str = "123"

        shouldThrow<IndexOutOfBoundsException> { str[3] }
    }

}) {
}