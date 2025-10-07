package com.project.immersionstudy.defaultModel.service

import com.project.immersionstudy.defaultModel.data.ExposedPostQueryRepository
import org.springframework.stereotype.Service

@Service
class PostQueryService(
    val exposedPostQueryRepository: ExposedPostQueryRepository
) {
    fun findPostsBy() {

        exposedPostQueryRepository.findBy { query ->
            query.inIds(listOf(1L, 2L))
        }
    }
}