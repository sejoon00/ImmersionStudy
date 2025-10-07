package com.project.immersionstudy.defaultModel.domain

import java.time.LocalDateTime

data class Post(
    val id: Long? = null,
    val content: String,
    val publisherId: Long,
    val type: String,
    val authorId: Long,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime,
    val deletedAt: LocalDateTime? = null,
) {
    fun update(
        newContent: String,
        newType: String,
        now: LocalDateTime = LocalDateTime.now(),
    ): Post {
        return this.copy(
            content = newContent,
            type = newType,
            updatedAt = now
        )
    }

    fun delete(
        now: LocalDateTime = LocalDateTime.now(),
    ): Post {
        return this.copy(
            deletedAt = now,
        )
    }

    companion object {
        fun create(
            content: String,
            type: String,
            publisherId: Long,
            authorId: Long,
            now : LocalDateTime = LocalDateTime.now()
        ) : Post {
            return Post(
                content = content,
                type = type,
                publisherId = publisherId,
                authorId = authorId,
                createdAt = now,
                updatedAt = now,
            )
        }
    }
}