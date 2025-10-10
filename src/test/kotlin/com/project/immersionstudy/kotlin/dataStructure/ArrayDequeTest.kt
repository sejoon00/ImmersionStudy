package com.project.immersionstudy.kotlin.dataStructure

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class ArrayDequeTest: FunSpec({

    test("deque를 생성할 수 있다.") {
        val deque = ArrayDeque<Int>()

        deque.addAll(listOf(1, 2, 3, 4))
        deque.removeFirst() shouldBe 1

        deque.addFirst(1)
        deque.removeFirst() shouldBe 1

        deque.addLast(5)
        deque.removeLast() shouldBe 5

    }
}) {
}