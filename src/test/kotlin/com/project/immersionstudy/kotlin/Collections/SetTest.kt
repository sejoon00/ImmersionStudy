package com.project.immersionstudy.kotlin.Collections

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class SetTest : FunSpec({

    test("Set을 생성할 수 있다.") {
        val set = setOf(1, 2, 3, 3)

        set shouldBe setOf(1, 2, 3)
    }

    test("두 집합의 교집합, 합집합, 차집합을 구할 수 있다.") {
        val set1 = setOf(1, 2, 3)
        val set2 = setOf(2, 3, 4)

        //교집합
        set1 intersect set2 shouldBe setOf(2, 3)

        //합집합
        set1 union set2 shouldBe setOf(1, 2, 3, 4)
        set1 + set2 shouldBe setOf(1, 2, 3, 4)

        //차집합
        set1 subtract set2 shouldBe setOf(1)
        set1 - set2 shouldBe setOf(1)
    }
}) {
}