package com.project.immersionstudy.kotlin.Array

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class ArrayCreateTest: FunSpec({

    test("특정 타입의 배열을 생성할 수 있다.") {
        val intArr = IntArray(3)
        val intArr2 = intArrayOf(1, 2, 3)
        val intArr3 = arrayOf(1, 2, 3)
        val charArr = charArrayOf('1', '/')
        val stringArr = arrayOf("asdf", "aasd")
        val emptyArr = emptyArray<Int>()
    }

    test("null로 초기화된 배열을 생성할 수 있다.") {
        val arrayOfNulls = arrayOfNulls<Int>(3)

        println(arrayOfNulls.joinToString())
        arrayOfNulls shouldBe listOf(null, null, null)
    }

    test("특정 값으로 배열을 초기화해서 생성할 수 있다") {
        val intArray = IntArray(3) { 0 }
        val lambaArray = IntArray(3) { i -> i*i }

        intArray shouldBe listOf(0, 0, 0)
        lambaArray shouldBe listOf(0, 1, 4)

        val booleanArray = BooleanArray(3)
        booleanArray shouldBe arrayOf(false, false, false)
    }

    test("중첩 배열을 만들 수 있다.") {
        val array: Array<IntArray> = Array(3) { IntArray(3) { it * it} }
        array[0][1] = 1
        println(array.joinToString("\n") { it.joinToString() })

        array[0][0] shouldBe 0
        array[0][1] shouldBe 1
        array[0][2] shouldBe 4

        val treeNestedArr = Array(3) { Array(3) { IntArray(3) { i -> i * i} } }
    }
}) {
}