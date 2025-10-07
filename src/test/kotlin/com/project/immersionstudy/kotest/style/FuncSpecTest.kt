package com.project.immersionstudy.kotest.style

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class FuncSpecTest : FunSpec({

    test("func 스타일의 테스트") {
        "func".length shouldBe 4
    }

    xtest("실행되지 않는 테스트") {
        println("실행되지 않는 테스트")
    }
})