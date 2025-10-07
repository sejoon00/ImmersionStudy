package com.project.immersionstudy.kotest.config

import io.kotest.core.spec.style.DescribeSpec
import kotlinx.coroutines.delay

class TimeoutTest: DescribeSpec({

    describe("Timeout 설정 테스트") {
        it("설정된 timeout을 넘으면 테스트가 실패한다.") {
            delay(5500) // 5초 대기
        }
    }
}) {
}