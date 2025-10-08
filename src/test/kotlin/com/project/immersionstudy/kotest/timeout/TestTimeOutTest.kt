package com.project.immersionstudy.kotest.timeout

import io.kotest.core.spec.style.DescribeSpec
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.milliseconds

class TestTimeOutTest: DescribeSpec({

    // 현재 테스트 클래스 전체 포함된 테스트들이 모두 설정된 timeout 안에 끝나야 성공 (밀리초 단위)
    timeout = 5000
    // 각 it 블록이 설정된 invocationTimeout 안에 끝나야 성공 (밀리초 단위)
    invocationTimeout = 200

    describe("Timeout 설정 테스트") {
        xit("blockingTest를 설정하지 않으면 thread sleep 이후에 실패한다.").config(timeout = 200.milliseconds) {
            Thread.sleep(300) // 0.3초 대기
        }

        xit("blockingTest를 설정하면 즉시 실패한다.").config(blockingTest = true, timeout = 200.milliseconds) {
            Thread.sleep(300) // 0.3초 대기
        }

        it("각 테스트는 직접 설정한 invocationTimeout 이내에 끝나야 한다.").config(invocations = 2, invocationTimeout = 200.milliseconds) {
            Thread.sleep(300) // 0.3초 대기
        }

        xit("delay - it 테스트는 invocationTimeout 이내에 끝나야 한다.") {
            delay(300) // 0.3초 대기
        }


        xit("설정된 timeout을 넘으면 테스트가 실패한다.").config(timeout = 100.milliseconds) {
            delay(200) // 0.2초 대기
        }

        xit("설정된 timeout 이내에 끝나면 테스트가 성공한다.").config(timeout = 200.milliseconds) {
            delay(100) // 0.1초 대기
        }

        xit("thread sleep- it 테스트는 invocationTimeout 이내에 끝나야 한다.").config(blockingTest = true, timeout = 200.milliseconds) {
            Thread.sleep(300) // 0.3초 대기
        }


        xit("각 테스트는 직접 설정한 invocationTimeout 이내에 끝나야 한다.").config(invocations = 2, invocationTimeout = 300.milliseconds) {
            delay(200) // 0.2초 대기
        }
    }
})