package com.project.immersionstudy.GapLock.data

import com.project.immersionstudy.GapLock.domain.Post
import com.project.immersionstudy.GapLock.domain.PostRepository
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.andWhere
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.update

class GapLockPostRepository() : PostRepository {

    override fun findById(postId: Long) {
        Posts.selectAll()
            .where(Posts.id eq postId,
            ).andWhere {
                Posts.type eq "hi"
            }
    }

    override fun updateBulkTwiceForGapLock(posts: List<Post>) {

        Posts.selectAll()
            .where()

        Posts.update {  }
    }

    override fun updateBulkFroGapLock(posts: List<Post>) {
        TODO("Not yet implemented")
    }
}