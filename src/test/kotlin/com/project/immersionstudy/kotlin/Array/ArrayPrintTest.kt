package com.project.immersionstudy.kotlin.Array

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class ArrayPrintTest: FunSpec({

    test("배열을 문자열로 출력할 수 있다.") {
        val arr = intArrayOf(1, 2, 3)
        val str = arr.joinToString()

        str shouldBe "1, 2, 3"
    }

    test("배열을 문자열로 출력할 때 구분자를 변경할 수 있다.") {
        val arr = intArrayOf(1, 2, 3)
        val str = arr.joinToString(separator = " - ")

        str shouldBe "1 - 2 - 3"
    }

    test("중첩 배열을 출력할 수 있다.") {
        val array: Array<IntArray> = Array(3) { IntArray(3) { it * it} }
        array[0][1] = 1
        println(array.joinToString("\n") { it.joinToString() })
    }
}) {
}