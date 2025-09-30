package com.project.immersionstudy.GapLock.service

import com.project.immersionstudy.GapLock.data.ExposedPostQueryRepository

class PostQueryService(
    val exposedPostQueryRepository: ExposedPostQueryRepository
) {
    fun findPostsBy() {

        exposedPostQueryRepository.findBy { query ->
            query.inIds(listOf(1L, 2L))
        }
    }
}