package com.project.immersionstudy.kotlin.Collections

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class ListTest: FunSpec({
    test("List를 생성할 수 있다.") {
        // read-only List
        val intList = listOf(1, 2, 3)

        // mutable List
        val stringList = mutableListOf(1, 2, 3)
        val complexList= mutableListOf("abc", 1)

        val list = MutableList<Int>(3) { 0 }

    }

    test("중첩 리스트를 생성할 수 있다"){
        // 비어 있는 n개의 리스트를 가진 중첩 리스트
        val list = List(3) { mutableListOf<Int>() }
        println(list)

    }

    test("List를 추가할 수 있다.") {
        // mutable List
        val intList = mutableListOf(1, 2, 3)

        intList.add(4)
        intList shouldBe listOf(1, 2, 3, 4)

//        intList.add("1") 타입이 정해지면 다른 타입 삽입 불가

        val complexList= mutableListOf("abc", 1)

        complexList.add("a")
        complexList.add(1.0)

        complexList shouldBe listOf("abc", 1, "a", 1f)

        val complexList2 = mutableListOf(arrayOf(1,2,3), 1)

        complexList.add("a") // 배열을 추가하려면 기존부터 넣어야한다.

        // read-only List
        val intReadList = listOf(1, 2, 3)
    }

    test("List의 요소를 수정할 수 있다.") {
        val list = mutableListOf(1, 2, 3)
        list[2] = 4
        list shouldBe listOf(1, 2, 4)

        list.remove(1) //요소로 삭제됨
        list shouldBe listOf(2, 4)

        list.removeAt(1) //인덱스로 삭제됨
        list shouldBe listOf(2)

        list.replaceAll{ it * 2}
    }

    test("mapTo를 통해 List의 요소들을 수정해서 옮길 수 있다.") {
        val list = mutableListOf(1, 2, 3)
        val target = mutableListOf<Int>()
        list.mapTo(target) { it * it }

        target shouldBe listOf(1, 4, 9)
    }

    test("replaceAll을 통해 모든 요소를 바꿀 수 있다.") {
        val list = mutableListOf(1, 2, 3, 4)
        list.replaceAll{
            it * it
        }

        list shouldBe listOf(1, 4 , 9, 16)
        println(list)
    }

    test("list의 요소를 가져올 수 있다.") {
        val list = mutableListOf(1, 2, 3)

        // 해당 인덱스가 없으면 임의의 값도 가져올 수 있다.
        val orElseValue = list.getOrElse(4) { 0 }
        orElseValue shouldBe 0
    }

    test("contains를 통해 특정 요소가 존재하는지 확인할 수 있다.") {
        val list = mutableListOf(1, 2, 3)
        list.contains(4) shouldBe false
        list.contains(3) shouldBe true
    }

    /**
     * retainAll은 contains의 시간 복잡도에 영향을 받는다.
     * Set이면 contains가 O(1) 이므로 O(n)에 끝난다.
     * List면 contains가 O(n) 이므로 O(n^2)이다.
     */
    test("retainAll을 통해 원하는 요소만 남길 수 있다.") {
        val list = mutableListOf(1, 2, 3, 4)
        list.retainAll(listOf(1, 5)) // 해당 리스트와 일치하면 남긴다.

        list shouldBe listOf(1)
        println(list)
    }

    test("read-only list를 mutable하게 변환할 수 있다.") {
        val list = listOf(1, 2, 3)
        val toMutableList = list.toMutableList()

        toMutableList.add(4)
        // list.add(4) 새로운 리스트가 생긴다. 기존꺼는 수정 불가능
    }

    test("list와 array간 변환할 수 있다.") {
        val list = mutableListOf(1, 2, 3)
        val toIntArray = list.toIntArray()

        val arr = intArrayOf(1, 2, 3)
        val toMutableList = arr.toMutableList()

        toMutableList.add(4)
    }

    test("list를 순회할 수 있다.") {
        val list = mutableListOf(1, 2, 3)
        for( i in list ) {
            print(i)
        }
        println()

        for( idx in list.indices ) {
            list[idx] += 1
            print(list[idx])
        }

        val list2 = listOf("1", "2")
        for((idx, i) in list2.withIndex())

        list  shouldBe listOf(2, 3, 4)
    }

    test("list를 정렬할 수 있다.") {
        val list = mutableListOf(3, 2, 1)
        list.sort()
        list shouldBe listOf(1, 2, 3)

        list.reverse()
        list shouldBe listOf(3, 2, 1)

        val list2 = mutableListOf(4, 3, 7, 123, 23)

        list2.sortByDescending{ it }
        list2 shouldBe listOf( 123, 23, 7, 4, 3)

        list2.sortWith { x, y -> x - y}
        list2 shouldBe listOf(3, 4, 7, 23, 123)

        list2.add(1)

        val list3 = list2.sorted() //불변을 return 한다.
        // list3.add(1) 불가

    }

    test("최대 최소를 구할 수 있다.") {
        val list = mutableListOf(4, 3, 7, 123, 23)

        list.max() shouldBe 123
        list.min() shouldBe 3

        val userList = mutableListOf(User("park" , 26), User("sim", 22))
        userList.maxBy { it.age } shouldBe User("park", 26)
        userList.maxWith { x, y -> x.age - y.age } shouldBe User("park", 26)
    }

    test("중첩 리스트를 flat할 수 있다.") {
        val list = mutableListOf(mutableListOf(1, 2, 3), mutableListOf(4, 5, 6))

        val flatMap= list.flatMap { it } //새로운 리스트를 반환한다.
        flatMap shouldBe listOf(1, 2, 3, 4, 5, 6)
    }

    test("리스트를 filtering할 수 있다.") {
        val list = listOf(1, 2, 3)
        val filterList = list.filter { it > 1 }
        filterList shouldBe listOf(2, 3)

        val filterNot: List<Int> = list.filterNot { it > 1 }
        filterNot shouldBe listOf(1)

        val list2 = listOf(1, 2, 3, 4, 5, 6, 7)

        val filterIndexed = list2.filterIndexed { idx, ele -> idx % 2 == 0 } //짝수 인덱스만 남김
        filterIndexed shouldBe listOf(1, 3, 5, 7)

        list.filterIndexed { idx, element->
            // 짝수 인덱스에는 짝수만, 홀수 인덱스에는 홀수만
            if(idx % 2 == 0)
                element % 2 == 0
            else
                element % 2 == 1
        } shouldBe emptyList()
    }

    test("reducer과 sum을 통해 누적 합을 구할 수 있다.") {
        val list = listOf(1, 2, 3, 4, 5, 6, 7)
        list.reduce { acc, n ->
            println(acc + n)
            acc + n
        } shouldBe 28

        list.sum() shouldBe 28

        val userList = mutableListOf(User("park" , 26), User("sim", 22))
        userList.sumOf { it.age } shouldBe 48
    }

    test("associate를 통해 Map으로 바꿀 수 있다.") {
        val list = listOf("a", "b", "c", "d")
        val map= list.associate { list.indexOf(it) to it }
        map shouldBe mapOf(0 to "a", 1 to "b", 2 to "c", 3 to "d")


    }
})

data class User(
    val name : String,
    val age : Int
)