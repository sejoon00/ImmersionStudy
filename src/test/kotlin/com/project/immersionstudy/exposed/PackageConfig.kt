package com.project.immersionstudy.exposed

import io.kotest.core.config.AbstractPackageConfig
import kotlin.time.Duration.Companion.milliseconds

/**
 * build.gradle.kts에 아래와 같은 설정이 필요합니다.
 *
 * tasks.withType<Test>().configureEach {
 *     useJUnitPlatform()
 *     systemProperty("kotest.framework.config.fqn", "[ProjectConfig 클래스 Path]") // ex: "com.project.immersionstudy.kotest.KotestProjectConfig"
 * }
 */
object PackageConfig : AbstractPackageConfig() {
    // 5초 이상 걸리는 테스트는 실패합니다.
    override val timeout = 5000.milliseconds

//    override val isolationMode: IsolationMode = IsolationMode.InstancePerTest
}