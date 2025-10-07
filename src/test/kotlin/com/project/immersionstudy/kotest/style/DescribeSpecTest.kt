package com.project.immersionstudy.kotest.style

import com.project.immersionstudy.kotest.LengthCalculator
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class DescribeSpecTest : DescribeSpec({

    /**
     * describe : 무엇을 테스트하는가? (대상)
     * context : 어떤 상황/조건에서? (상황, 조건)
     * it : 무엇을 해야하는가? (행동, 기대 결과)
     */
    describe("describe 스타일의 테스트") {
        it("it 블록을 통해서 테스트 케이스를 명시할 수 있다") {
            println("describe 스타일의 테스트")
        }

        describe("중첩 describe 테스트") {
            it("중첩된 describe 안에 it 블록을 명시할 수 있다.") {
                println("중첩된 describe 스타일의 테스트")
            }
        }

        context("context는 조건/상황을 표현할 때 사용한다.") {
            it("it 블럭에는 기대하는 결과를 자연어 문장으로 작성한다.") {
                println("중첩된 context 스타일의 테스트")
            }

            xit("실행되지 않는 it 블록") {
                println("실행되지 않는 it 블록")
            }
        }

        xcontext("실행되지 않는 context") {
            it("실행되지 않는 it 블록") {
                println("실행되지 않는 context 스타일의 테스트")
            }
        }
    }

    describe("LengthCalculator") {
        context("영어 문자열이 들어왔을 때") {
            it("정상적으로 길이를 반환해야 한다.") {
                LengthCalculator.Companion.of("hello").getLength() shouldBe 5
            }
        }

        context("공백이 포함된 문자가 들어왔을 때") {
            it("공백이 중간에 있다면 공백도 길이에 포함되어야 한다.") {
                LengthCalculator.Companion.of("hello world").getLength() shouldBe 11
            }

            it("공백만 있다면 길이가 0이다") {
                LengthCalculator.Companion.of("     ").getLength() shouldBe 0
            }

            it("빈 문자열이라면 길이가 0이다") {
                LengthCalculator.Companion.of("").getLength() shouldBe 0
            }

            it("마지막에 공백이 들어오면 길이에 포함되지 않는다.") {
                LengthCalculator.Companion.of("hello     ").getLength() shouldBe 5
            }
        }
    }
})