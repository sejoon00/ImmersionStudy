package com.project.immersionstudy.kotlin.dataStructure

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import java.util.PriorityQueue

class PriorityQueueTest : FunSpec({

    test("우선순위 큐를 만들 수 있다.") {
        val pq = PriorityQueue<Int>()
        pq.addAll(listOf(1, 2, 3, 4))

        pq.poll() shouldBe 1
        pq.size shouldBe 3
        pq.peek() shouldBe 2
        pq.size shouldBe 3

        pq.remove(1) // 없으면 아무 일이 일어나지 않는다.
        pq.remove(4)
        pq.size shouldBe 2

//        pq.toList() shouldBe listOf(2, 3, 4) pq의 내부 list는 순서를 보장하지 않는다.
    }

    test("우선순위 큐는 정렬된 상태로 값을 반환한다.") {
        val pq = PriorityQueue<Int>()
        pq.addAll(listOf(4, 3, 2, 1))

        val list = mutableListOf<Int>()
        while(pq.isNotEmpty()) {
            list.add(pq.poll())
            println(pq.joinToString())
        }
        list shouldBe listOf(1, 2, 3, 4)
    }

    test("우선순위의 정렬 순서를 바꿀 수 있다.") {
        val pq = PriorityQueue<Int>(compareByDescending { it })
        pq.addAll(listOf(1, 2, 3, 4))

        val list = mutableListOf<Int>()
        while(pq.isNotEmpty()) {
            list.add(pq.poll())
        }
        list shouldBe listOf(4, 3, 2, 1)

        val pq2 = PriorityQueue<Task>( compareBy<Task> { it.priority }.thenBy { it.name.length })

        pq2.addAll(listOf(Task("ab", 5), Task("a", 5), Task("b", 10)))
        pq2.poll() shouldBe Task("a", 5)
    }
})

data class Task(val name: String, val priority: Int)