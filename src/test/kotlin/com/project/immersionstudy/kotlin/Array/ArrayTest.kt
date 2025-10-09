package com.project.immersionstudy.kotlin.Array

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.equals.shouldBeEqual
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import io.kotest.matchers.types.shouldBeSameInstanceAs

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

    test("배열을 자를 수 있다.") {
        val arr = intArrayOf(1, 2, 3)
        arr.slice(1..2) shouldBe listOf(2, 3)
        arr.sliceArray(1 .. 2) shouldBe  intArrayOf(2, 3)

        val startIdx = 1
        val endIx = 2
        arr.slice(startIdx .. endIx) shouldBe listOf(2, 3)

        // slice한 배열은 불변이다.
        val arr2 = intArrayOf(1, 3, 2, 1)
        val sorted = arr2.slice(1..3).sorted()
    }

    test("배열을 정렬할 수 있다.") {
        var arr = intArrayOf(3, 30, 34, 5, 9)

        val list = arr.sortedWith { x, y ->
            var a = x.toString()
            var b = y.toString()
            if(a.length == 1)
                a = "$a$b"
            if(b.length == 1)
                b += "$b$a"
            b.compareTo(a)
        }
        println(list.joinToString(""))

        val arr2 = arrayOf(intArrayOf(1, 2), intArrayOf(2, 3), intArrayOf(1, 3))
        val sortedWith: List<IntArray> = arr2.sortedWith(compareBy<IntArray> { it[0] }.thenByDescending { it[1] })
        sortedWith[0] shouldBe listOf(1, 3)

    }

    test("배열을 filtering할 수 있다.") {
        val arr = intArrayOf(3, 0, 6, 1, 5)
        arr.filter{ it > 3}

    }
}) {
}