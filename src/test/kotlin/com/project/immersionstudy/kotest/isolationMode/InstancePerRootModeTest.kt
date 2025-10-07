package com.project.immersionstudy.kotest.isolationMode

import io.kotest.core.spec.IsolationMode
import io.kotest.core.spec.style.DescribeSpec

/**
 * IsolationMode.InstancePerRoot
 *
 * - 각 최상위 루트 블록(describe, context)마다 새로운 인스턴스를 생성
 * - 루트 블록 내의 모든 테스트는 동일한 인스턴스를 공유
 * - 루트 블록 간에는 상태가 공유되지 않음
 */
class InstancePerRootModeTest : DescribeSpec({
    isolationMode = IsolationMode.InstancePerRoot

    val id = java.util.UUID.randomUUID()


    describe("isolationMode - InstancePerRoot - 첫 번째 describe") {

        it("첫 번째 테스트") {
            println("첫 번째 describe - 첫 번째 테스트 id: $id")
        }

        it("두 번째 테스트") {
            println("첫 번째 describe - 두 번째 테스트 id: $id")
        }
    }

    describe("isolationMode - InstancePerRoot - 두 번째 describe") {

        it("첫 번째 테스트") {
            println("두 번째 describe - 첫 번째 테스트 id: $id")
        }

        it("두 번째 테스트") {
            println("두 번째 describe - 두 번째 테스트 id: $id")
        }
    }
}) {
}