package com.project.immersionstudy.kotlin.forStatement

import io.kotest.core.spec.style.FunSpec

class ForArrayTest: FunSpec({
    test("이중 for 문 출력") {
        val arr = Array(3) { IntArray(3) {it * it} }
        for( i in arr.indices) {
            for( j in arr[i].indices) {
                print(arr[i][j].toString() + " ")
            }
            println()
        }
    }

    test("이중 for 문 입력") {
        val arr = Array(3) { IntArray(3) {it * it} }
        var k = 1;
        for( i in arr.indices) {
            for( j in arr[i].indices) {
                arr[i][j] = k++
                print(arr[i][j].toString() + " ")
            }
            println()
        }
    }

    test("레이블로 break을 할 수 있다.") {
        val arr = Array(3) { IntArray(3) }

        var k = 1;

        for( i in arr.indices) {
            for( j in arr[i].indices) {
                arr[i][j] = k++
                print(arr[i][j].toString() + " ")
            }
            println()
        }

        loop@for( i in arr.indices ) {
            for( j in arr[i].indices ) {
                println(arr[i][j])
                if(arr[i][j] == 4)
                    break@loop
            }
        }
    }

    test("레이블을 통해 람다 식에서도 리턴할 수 있다") {
        listOf(1, 2, 3, 4, 5).forEach {
            if(it == 3) return@forEach //for 문의 continue와 같음
            println(it)
        }
        println("finish")
    }
}) {
}