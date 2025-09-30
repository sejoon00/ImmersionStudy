package com.project.immersionstudy.GapLock.domain

interface PostRepository {

    fun findById(postId: Long)

    fun updateBulkTwiceForGapLock(posts: List<Post>)

    fun updateBulkFroGapLock(posts: List<Post>)

}