package com.project.immersionstudy.kotlin.Collections

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import java.util.SortedMap

class MapTest : FunSpec({

    test("Map을 생성할 수 있다.") {
        val map = mutableMapOf(1 to "a", 2 to "b")
        map[1] shouldBe "a"
        map.get(2) shouldBe "b"

        // key가 존재하지 않을 때 기본 값을 지정할 수 있다.
        map.getOrDefault(3, "none") shouldBe "none"
    }

    test("Map에 요소를 추가할 수 있다.") {
        val map = mutableMapOf(1 to "a", 2 to "b", 3 to "c")
        map.put(4, "d")
        map[5] = "e"
        map shouldBe mapOf(1 to "a", 2 to "b", 3 to "c", 4 to "d", 5 to "e")

        map.putAll(listOf(1 to "b", 2 to "a"))
        map shouldBe mapOf(1 to "b", 2 to "a", 3 to "c", 4 to "d", 5 to "e")
    }

    test("Map의 요소를 수정할 수 있다.") {
        val map = mutableMapOf(1 to "a", 2 to "b", 3 to "c")

        map.replace(2, "B")
        map[2] shouldBe "B"

        map.remove(2)
        map[2] shouldBe null

        map -= 1
        map[1] shouldBe null
    }

    test("Map의 key, value Set으로 가져올 수 있다.") {
        val map = mutableMapOf(1 to "a", 2 to "b", 3 to "c")

        map.keys shouldBe setOf(1, 2, 3)
        map.values shouldBe setOf("a", "b", "c")
    }

    test("Map을 filtering할 수 있다.") {
        val map = mutableMapOf(1 to "a", 2 to "b", 3 to "c")

        val filter = map.filter { (key, value) ->
            key % 2 == 0
        }

        map shouldBe mapOf(1 to "a", 2 to "b", 3 to "c")
        filter shouldBe mapOf(2 to "b")

        map.filterKeys { it % 2 == 0 } shouldBe mapOf(2 to "b")
    }

    test("compute 연산을 통해 value들을 수정할 수 있다.") {
        val map = mutableMapOf(1 to "a", 2 to "b", 3 to "c")

        map.computeIfPresent(1) { key, value ->
            value.uppercase()
        }

        map[1] shouldBe "A"

        map.compute(2) { _, value ->
            value?.uppercase()
        }

        map[2] shouldBe "B"

        map.compute(10) { _, _ ->
            "j"
        }
        map[10] shouldBe "j"

        // computeIfPresent는 존재하지않으면 실행하지 않는다.
        map.computeIfPresent(11) { _, _ ->
            "k"
        }
        map[11] shouldBe null

        map.computeIfAbsent(12) { _ -> "a"}
        map[12] shouldBe "a"
    }

    test("Map을 순회할 수 있다") {
        val map = mutableMapOf(1 to "a", 2 to "b", 3 to "c")

        for( e in map) {
            println(e.key to e.value)
        }
    }

    test("Map을 정렬할 수 있다.") {
        val map = mutableMapOf(2 to "b", 1 to "a", 3 to "c")

        val toSortedMap: SortedMap<Int, String> = map.toSortedMap()
        toSortedMap shouldBe mapOf(1 to "a", 2 to "b", 3 to "c")
        toSortedMap shouldBe mapOf(2 to "b", 1 to "a", 3 to "c")
    }

})