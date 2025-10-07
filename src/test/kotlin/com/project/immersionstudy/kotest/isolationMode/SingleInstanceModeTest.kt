package com.project.immersionstudy.kotest.isolationMode

import io.kotest.core.spec.IsolationMode
import io.kotest.core.spec.style.DescribeSpec
import java.util.UUID

/**
 * IsolationMode.SingleInstance
 *
 * - 모든 테스트 케이스가 동일한 인스턴스를 공유
 * - 테스트 간에 상태가 공유되므로, 상태 변화에 따른 영향이 있을 수 있음
 * - 테스트 간의 독립성이 보장되지 않음
 */
class SingleInstanceModeTest: DescribeSpec({

    isolationMode = IsolationMode.SingleInstance

    val id = java.util.UUID.randomUUID()


    describe("isolationMode - SingleInstance - 첫 번째 describe") {

        it("첫 번째 테스트") {
            println("첫 번째 describe - 첫 번째 테스트 id: $id")
        }

        it("두 번째 테스트") {
            println("첫 번째 describe - 두 번째 테스트 id: $id")
        }
    }

    describe("isolationMode - SingleInstance - 두 번째 describe") {

        it("첫 번째 테스트") {
            println("두 번째 describe - 첫 번째 테스트 id: $id")
        }

        it("두 번째 테스트") {
            println("두 번째 describe - 두 번째 테스트 id: $id")
        }
    }
})