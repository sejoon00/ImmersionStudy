package com.project.immersionstudy.kotest.config

import io.kotest.core.config.AbstractProjectConfig
import io.kotest.core.spec.IsolationMode
import kotlin.time.Duration.Companion.milliseconds
import kotlin.time.Duration.Companion.seconds

/**
 * build.gradle.kts에 아래와 같은 설정이 필요합니다.
 *
 * tasks.withType<Test>().configureEach {
 *     useJUnitPlatform()
 *     systemProperty("kotest.framework.config.fqn", "[ProjectConfig 클래스 Path]") // ex: "com.project.immersionstudy.kotest.KotestProjectConfig"
 * }
 */
object KotestProjectConfig : AbstractProjectConfig() {
    // 5초 이상 걸리는 테스트는 실패합니다.
//    override val isolationMode: IsolationMode = IsolationMode.InstancePerTest
    override val timeout = 100.milliseconds
}