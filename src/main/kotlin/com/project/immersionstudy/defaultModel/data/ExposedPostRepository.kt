package com.project.immersionstudy.defaultModel.data

import com.project.immersionstudy.defaultModel.domain.Post
import com.project.immersionstudy.defaultModel.domain.PostRepository
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.selectAll
import org.springframework.stereotype.Repository

@Repository
class ExposedPostRepository() : PostRepository {

    override fun findById(postId: Long): Post? {
        return Posts.selectAll()
                .where(Posts.id eq postId)
                .firstOrNull()
                ?.toModel()
    }

    override fun save(model: Post): Post {
        return Posts.save(model)
    }
}