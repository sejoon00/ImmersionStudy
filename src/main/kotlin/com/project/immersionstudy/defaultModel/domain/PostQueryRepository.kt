package com.project.immersionstudy.defaultModel.domain

import com.project.immersionstudy.defaultModel.data.ExposedPostQueryBuilder

interface PostQueryRepository {
    fun findBy(builder: (query: ExposedPostQueryBuilder) -> Unit) : List<Post>
}