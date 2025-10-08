package com.project.immersionstudy.kotlin.Array

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe

class ArrayTest : FunSpec({
    test("Array split test") {
        val arr = intArrayOf(1, 2, 3, 4, 5)

        val sliceArray = arr.slice(1..3)

        sliceArray.size shouldBe 3
        sliceArray shouldBe listOf(2, 3, 4)
    }

    test("배열 뒤에 요소 추가가 가능하다") {
        val arr = intArrayOf(1, 2, 3)
        val newArr = arr + 4

        newArr shouldBe intArrayOf(1, 2, 3, 4)
        newArr shouldNotBe arr //새로운 배열이 생성된다.
    }
}) {
}