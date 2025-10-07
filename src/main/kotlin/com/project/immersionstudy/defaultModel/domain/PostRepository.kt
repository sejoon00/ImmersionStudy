package com.project.immersionstudy.defaultModel.domain

interface PostRepository {

    fun findById(postId: Long): Post?

    fun save(model: Post): Post
}